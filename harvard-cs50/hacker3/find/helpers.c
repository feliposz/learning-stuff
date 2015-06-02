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
    // TODO: implement an O(n) sort

	//define a big array with the size of the largest possible number
	//the index will be the actual value of the element in the unsorted array
	//the value is a count for each time an element of the same value is found
	int sorted[65536];

	//initialize the counters
	for (int i = 0; i < 65536; i++) {
		sorted[i] = 0;
	}

	//iterate through the elements of the unsorted array
	//increment its counter
	for (int i = 0; i < n; i++)
	{
		sorted[values[i]]++;
	}

	//check the counters
	for (int i = 0, j = 0; i < 65536; i++) {
		//place the correspondig element in the original array
		while (sorted[i]-- > 0) {
			values[j++] = i;
		}
	}
	
    return;
}

