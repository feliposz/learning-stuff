#include <cs50.h>
#include <stdio.h>
#include <stdlib.h>
#include <time.h>

int main(int argc, char *argv[])
{
	srand(time(NULL));
	int skittles = rand() % 1024;

	//printf("skittles = %d\n", skittles);

	printf("O hai! I'm thinking of a number between 0 and 1023. ");
	printf("What is it?\n>");
	int guess;
	do { 
		guess = GetInt();
		if (guess < 0)
			printf("Nope! Don't be difficult. Guess again.\n>");
		else if (guess < skittles)
			printf("Nope! There are more. Guess again.!\n>");
		else if (guess > skittles)
			printf("Nope! There are less. Guess again.!\n>");
		else
			printf("That's right! Nom nom nom.\n");
	} while (guess != skittles);
}
