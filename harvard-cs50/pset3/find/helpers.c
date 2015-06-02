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

bool recursive_search(int value, int array[], int min, int max);

bool
search(int value, int array[], int n)
{
    // TODO: re-implement as binary search
	return recursive_search(value, array, 0, n-1);
}

bool
recursive_search(int value, int array[], int min, int max)
{
	if (min > max) {
		return false;
	} else {
		int middle = (min + max) / 2;
		if (value == array[middle])
			return true;
		else if (value < array[middle])
			return recursive_search(value, array, min, middle-1);
		else
			return recursive_search(value, array, middle+1, max);
	}
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
