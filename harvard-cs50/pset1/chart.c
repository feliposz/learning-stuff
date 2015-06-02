#include <cs50.h>
#include <stdio.h>

int main(int argc, char *argv[])
{
	char *label[4];

	label[0] = "M spotting F";
	label[1] = "F spotting M";
	label[2] = "F spotting F";
	label[3] = "M spotting M";

	int spottings[4];
	int total = 0;

	for (int i = 0; i < 4; i++) {
		do {
			printf("%s: ", label[i]);
			spottings[i] = GetInt();
		} while (spottings[i] < 0);
		total += spottings[i];
	}

	printf("\nWho is Spotting Whom\n\n");

	for (int i = 0; i < 4; i++) {
		printf("%s\n", label[i]);
		int pounds = spottings[i] * 80 / total;
		while (pounds--)
			printf("#");
		printf("\n");
	}
}
