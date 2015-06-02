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
    if (argc != 2)
    {
        printf("Usage: bmphead infile\n");
        return 1;
    }

    // remember filenames
    char *infile = argv[1];

    // open input file 
    FILE *inptr = fopen(infile, "r");
    if (inptr == NULL)
    {
        printf("Could not open %s.\n", infile);
        return 2;
    }

    // read infile's BITMAPFILEHEADER
    BITMAPFILEHEADER bf;
    fread(&bf, sizeof(BITMAPFILEHEADER), 1, inptr);

    // read infile's BITMAPINFOHEADER
    BITMAPINFOHEADER bi;
    fread(&bi, sizeof(BITMAPINFOHEADER), 1, inptr);

    printf("BITMAPFILEHEADER:\n");
    printf("bfType = %x\n", bf.bfType);
    printf("bfSize = %u\n", bf.bfSize);
    printf("bfReserved1 = %u\n", bf.bfReserved1);
    printf("bfReserved2 = %u\n", bf.bfReserved2);
    printf("bfOffBits = %u\n", bf.bfOffBits);

    printf("\nBITMAPINFOHEADER:\n");
    printf("biSize = %u\n", bi.biSize);
    printf("biWidth = %d\n", bi.biWidth);
    printf("biHeight = %d\n", bi.biHeight);
    printf("biPlanes = %u\n", bi.biPlanes);
    printf("biBitCount = %u\n", bi.biBitCount);
    printf("biCompression = %u\n", bi.biCompression);
    printf("biSizeImage = %u\n", bi.biSizeImage);
    printf("biXPelsPerMeter = %u\n", bi.biXPelsPerMeter);
    printf("biYPelsPerMeter = %u\n", bi.biYPelsPerMeter);
    printf("biClrUsed = %u\n", bi.biClrUsed);
    printf("biClrImportant = %u\n", bi.biClrImportant);
    
    // determine padding for scanlines
    //int padding =  (4 - (bi.biWidth * sizeof(RGBTRIPLE)) % 4) % 4;

    // close infile
    fclose(inptr);

    // that's all folks
    return 0;
}
