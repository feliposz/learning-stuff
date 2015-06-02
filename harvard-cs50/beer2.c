#include <stdio.h>
#include <cs50.h>

int main(int argc, char *argv[])
{
	printf("How many bottles of beer? ");
	int n = GetInt();
	
	if (n < 1) {
		printf("Sorry, that makes no sense.\n");
		return 1;
	}
	
	while (n > 0) {
		printf("%d bottles of beer on the wall.\n", n);
		printf("%d bottles of beer.\n", n);
		printf("Take one down, pass it around, \n");
		printf("%d bottles of beer on the wall.\n\n", n - 1);
		n--;
	}
	return 0;
}
