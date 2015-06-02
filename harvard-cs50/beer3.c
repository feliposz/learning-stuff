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
		string s1 = (n == 1) ? "bottle" : "bottles";
		string s2 = (n == 2) ? "bottle" : "bottles";

		printf("%d %s of beer on the wall.\n", n, s1);
		printf("%d %s of beer.\n", n, s1);
		printf("Take one down, pass it around, \n");
		printf("%d %s of beer on the wall.\n\n", n - 1, s2);
		n--;
	}
	return 0;
}
