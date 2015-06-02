/****************************************************************************
 * helpers.c
 *
 * Computer Science 50
 * Problem Set 3
 *
 * Helper functions for Problem Set 3.
 ***************************************************************************/

#include <cs50.h>

#include "helpers.h"


/*
 * Returns true if value is in array of n values, else false.
 */

bool
search(int value, int array[], int n)
{
    // TODO: re-implement as binary search
    int min = 0, max = n - 1, middle = (min + max) / 2;

	do {
		if (value == array[middle]) {
			return true;
		} else if (value < array[middle]) {
			max = middle - 1;
			middle = (min + max) / 2;
		} else {
			min = middle + 1;
			middle = (min + max) / 2;
		}
	} while (min <= max);
    return false;
}


/*
 * Sorts array of n values.
 */

void 
sort(int values[], int n)
{
    // TODO: implement an O(n^2) sort
	for (int i = n; i > 0; i--)
	{
		for (int j = 0; j < i - 1; j++) {
			if (values[j] > values[j+1]) {
				int tmp = values[j];
				values[j] = values[j+1];
				values[j+1] = tmp;
			}
		}
	}
    return;
}
