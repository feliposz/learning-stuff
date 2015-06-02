#include <stdio.h>
#include <cs50.h>
#include "helpers.h"

void test(int values[], int n);

int main(int argc, char *argv[])
{
	int t1[8] = { 1, 2, 3, 4, 5, 6, 7, 8 };
	int t2[8] = { 8, 7, 6, 5, 4, 3, 2, 1 };
	int t3[8] = { -1, 2, -3, 4, -5, 6, -7, 8 };
	int t4[1] = { 0 };
	int t5[2] = { 2, 1 };
	int t6[2] = { 1, 2 };
	int t7[10] = { 100, 5, 40, 33, 42, 13, -10, 8, 0, 18 };

	test(t1, 8);
	test(t2, 8);
	test(t3, 8);
	test(t4, 1);
	test(t5, 2);
	test(t6, 2);
	test(t7, 10);

	printf("Test 1: %s\n",  search(1, t1, 8) ? "OK" : "FAILED");
	printf("Test 2: %s\n", !search(0, t1, 8) ? "OK" : "FAILED");
	printf("Test 3: %s\n",  search(8, t2, 8) ? "OK" : "FAILED");
	printf("Test 4: %s\n", !search(9, t2, 8) ? "OK" : "FAILED");
	printf("Test 5: %s\n",  search(0, t4, 1) ? "OK" : "FAILED");
	printf("Test 6: %s\n", !search(1, t4, 1) ? "OK" : "FAILED");
	printf("Test 7: %s\n",  search(1, t5, 2) ? "OK" : "FAILED");
	printf("Test 8: %s\n", !search(99,t7, 8) ? "OK" : "FAILED");
}

void test(int values[], int n)
{
	sort(values, n);
	for (int i = 0; i < n; i++)
		printf("%4d ", values[i]);
	printf("\n");
}
