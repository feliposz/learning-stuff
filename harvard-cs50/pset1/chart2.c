#include <cs50.h>
#include <stdio.h>

#define NUMBARS 4

int main(int argc, char *argv[])
{
	char *label[NUMBARS] = {
		"M spotting F",
		"F spotting M",
		"F spotting F",
		"M spotting F",
	};
	char *mini[NUMBARS] = {
		"M4F",
		"F4M",
		"F4F",
		"M4M",
	};

	int spottings[4];
	int total = 0;

	for (int i = 0; i < NUMBARS; i++) {
		do {
			printf("%s: ", label[i]);
			spottings[i] = GetInt();
		} while (spottings[i] < 0);
		total += spottings[i];
	}

	printf("\nWho is Spotting Whom\n\n");

	int maxLines = 0;
	for (int i = 0; i < NUMBARS; i++) {
		spottings[i] = spottings[i] * 20 / total;
		maxLines = spottings[i] > maxLines ? spottings[i] : maxLines;
	}

	for (int line = 0; line < maxLines; line++) {
		for (int i = 0; i < NUMBARS; i++) {
			if (line >= (maxLines - spottings[i])) {
				printf("###  ");
			} else {
				printf("     ");
			}
		}
		printf("\n");
	}

	for (int i = 0; i < NUMBARS; i++) {
		printf("%-5.5s", mini[i]);
	}
	printf("\n");
}
