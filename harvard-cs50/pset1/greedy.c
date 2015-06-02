#include <cs50.h>
#include <stdio.h>
#include <math.h>

int main(int argc, char *argv[])
{
	float change;
	do {
		printf("O hai, how much change is owed?\n");
		change = GetFloat();
		if (change < 0)
			printf("Er, how much change is owed?.\n");	
	} while (change < 0);
	int cents = round(change * 100.0);
	printf("As I see, you need %d cents\n", cents);
	int coins = 0;
	while (cents >= 25) {
		cents -= 25;
		coins++;
	}
	while (cents >= 10) {
		cents -= 10;
		coins++;
	}
	while (cents >= 5) {
		cents -= 5;
		coins++;
	}
	while (cents >= 1) {
		cents -= 1;
		coins++;
	}
	printf("%d coins needed for the change.\n", coins);
}
