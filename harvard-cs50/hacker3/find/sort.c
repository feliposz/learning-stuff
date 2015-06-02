/****************************************************************************
 * find.c
 *
 * Computer Science 50
 * Problem Set 3
 *
 * Prompts user for as many as HAY_MAX values until EOF is reached, 
 * then proceeds to search that "haystack" of values for given needle.
 *
 * Usage: find needle
 *
 * where needle is the value to find in a haystack of values
 ***************************************************************************/

#include <cs50.h>
#include <stdio.h>
#include <stdlib.h>

#include "helpers.h"


// maximum amount of hay
const int HAY_MAX = 65536;


int
main(int argc, char *argv[])
{
    // fill haystack
    int size;
    int haystack[HAY_MAX];
    for (size = 0; size < HAY_MAX; size++)
    {
        int straw = GetInt();
        if (straw == INT_MAX)
            break;

        // add hay to stack
        haystack[size] = straw;
    }

    // sort the haystack
    sort(haystack, size);

	for (int i = 0; i < size; i++)
	{
		printf("%d\n", haystack[i]);
	}

    // that's all folks
    return 0;
}
