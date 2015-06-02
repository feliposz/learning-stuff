/****************************************************************************
 * copy.c
 *
 * Computer Science 50
 * Problem Set 5
 *
 * Copies a BMP piece by piece, just because.
 ***************************************************************************/
       
#include <stdio.h>
#include <stdlib.h>

#include "bmp.h"


int
main(int argc, char *argv[])
{
    // ensure proper usage
    if (argc != 4)
    {
        printf("Usage: resize n infile outfile\n");
        return 1;
    }

    // remember filenames
    int resize; char dummy;
    if (sscanf(argv[1], " %d %c", &resize, &dummy) != 1 || resize <= 0 || resize > 100) {
        printf("Resize factor must be an integer between 1 and 100.\n");
        return 1;
    }
    char *infile = argv[2];
    char *outfile = argv[3];

    // open input file 
    FILE *inptr = fopen(infile, "r");
    if (inptr == NULL)
    {
        printf("Could not open %s.\n", infile);
        return 2;
    }

    // open output file
    FILE *outptr = fopen(outfile, "w");
    if (outptr == NULL)
    {
        fclose(inptr);
        fprintf(stderr, "Could not create %s.\n", outfile);
        return 3;
    }

    // read infile's BITMAPFILEHEADER
    BITMAPFILEHEADER bf;
    fread(&bf, sizeof(BITMAPFILEHEADER), 1, inptr);

    // read infile's BITMAPINFOHEADER
    BITMAPINFOHEADER bi;
    fread(&bi, sizeof(BITMAPINFOHEADER), 1, inptr);

    // ensure infile is (likely) a 24-bit uncompressed BMP 4.0
    if (bf.bfType != 0x4d42 || bf.bfOffBits != 54 || bi.biSize != 40 || 
        bi.biBitCount != 24 || bi.biCompression != 0)
    {
        fclose(outptr);
        fclose(inptr);
        fprintf(stderr, "Unsupported file format.\n");
        return 4;
    }

    // determine padding for scanlines
    int padding =  (4 - (bi.biWidth * sizeof(RGBTRIPLE)) % 4) % 4;

    // allocate memory to store the image to be resized
    RGBTRIPLE *image = malloc(sizeof(RGBTRIPLE) * abs(bi.biHeight) * bi.biWidth);
    if (image == NULL) {
        fclose(outptr);
        fclose(inptr);
        fprintf(stderr, "Not enough memory.\n");
        return 5;
    }   
    
    // iterate over infile's scanlines
    for (int i = 0, biHeight = abs(bi.biHeight); i < biHeight; i++)
    {
        RGBTRIPLE *tripleline = image + i * bi.biWidth;
        
        // read an entire line into memory
        fread(tripleline, sizeof(RGBTRIPLE), bi.biWidth, inptr);

        // skip over padding, if any
        fseek(inptr, padding, SEEK_CUR);
    }
    
    // Change the values for the file header based on resize factor
    bi.biHeight = bi.biHeight * resize;
    bi.biWidth = bi.biWidth * resize;
    int newpadding =  (4 - (bi.biWidth * sizeof(RGBTRIPLE)) % 4) % 4;
    bi.biSizeImage = abs(bi.biHeight) * (bi.biWidth * sizeof(RGBTRIPLE) + newpadding);
    bf.bfSize = sizeof(BITMAPFILEHEADER) + sizeof(BITMAPINFOHEADER) + bi.biSizeImage;

    // write outfile's BITMAPFILEHEADER with modified values
    fwrite(&bf, sizeof(BITMAPFILEHEADER), 1, outptr);

    // write outfile's BITMAPINFOHEADER with modified values
    fwrite(&bi, sizeof(BITMAPINFOHEADER), 1, outptr);

    // write output to outfile
    for (int i = 0, biHeight = abs(bi.biHeight); i < biHeight; i++)
    {
        // iterate over pixels in scanline
        for (int j = 0; j < bi.biWidth; j++)
        {
            // adjust i, j and biWidth to the original size and get the triple for the original image
            RGBTRIPLE *triple = image + (i / resize) * (bi.biWidth / resize) + (j / resize);
            fwrite(triple, sizeof(RGBTRIPLE), 1, outptr);
        }

        // write padding to outfile
        for (int k = 0; k < newpadding; k++)
            fputc(0x00, outptr);
    }
    
    // free allocated memory for image
    free(image);
    
    // close infile
    fclose(inptr);

    // close outfile
    fclose(outptr);

    // that's all folks
    return 0;
}
