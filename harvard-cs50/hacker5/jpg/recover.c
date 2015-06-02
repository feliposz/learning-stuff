#include <stdio.h>

int main(int argc, char *argv[])
{
    // user must not provide command-line arguments
    if (argc != 1) {
        printf("Usage: recover\n");
        return 1;
    }
    
    // open raw data to be read from file card.raw as a binary read-only file
    FILE *fp = fopen("card.raw", "rb");
    if (fp == NULL) {
        fprintf(stderr, "Could not open card.raw\n");
        return 2;
    }
    
    // temporary storage for data read fom disk
    unsigned char buffer[512];
    
    FILE *fpjpg = NULL;
    int count = 0;
    
    while (1) {
    
        // read 512 bytes (1 sector) of data from the file-system image file
        if (fread(buffer, sizeof(char) * 512, 1, fp) != 1) {
            if (feof(fp)) { // reached end of image data
                break;
            } else if (ferror(fp)) {
                fprintf(stderr, "Error reading from file card.raw\n");
                return 3;
            }            
        }

        char jpgname[50];
        
        // since beginning of files are aligned with sector, only need to check first bytes from buffer
        // check for a JPEG signature in the beginning of the file
        if (buffer[0] == 0xff && buffer[1] == 0xd8 && buffer[2] == 0xff && (buffer[3] == 0xe0 || buffer[3] == 0xe1))
        {
            // close previous file if open
            if (fpjpg != NULL) {
                fclose(fpjpg);
            }
            
            // create a new name for file
            sprintf(jpgname, "%03d.jpg", count);
            
            printf ("Found a file, writing it to %s\n", jpgname);
            
            // open file for writing
            fpjpg = fopen(jpgname, "wb");
            if (fpjpg == NULL) {
                fprintf(stderr, "Could not open file %s for writing\n", jpgname);
                return 4;
            }
            
            // increment counter
            count++;
        }

        // if a file is open for writing, write current buffer into it
        if (fpjpg != NULL) {
            if (fwrite(buffer, 512, 1, fpjpg) != 1) {
                fprintf(stderr, "Error writing to file %s\n", jpgname);
                return 5;
            }
        }
    }
    
    // close open files
    if (fpjpg != NULL) {
        fclose(fpjpg); 
    }
    fclose(fp);
}