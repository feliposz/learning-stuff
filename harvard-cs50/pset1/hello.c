/* printf */
#include <stdio.h>

/* system */
#include <stdlib.h>

/* strlen */
#include <string.h>

/* sleep */
#include <unistd.h>

int main(int argc, char *argv[])
{
	/* define a string to be printed */
	char *hi = "Howdy, World!";

	/* clear screen before starting */
	system("clear");

	/* iterate for each character in the string */
	for (int i = 0; i < strlen(hi); i++) {

		/* a simple way to center the printed string */
		for (int j = 0; j < (80-i)/2; j++) {
			printf(" ");
		}

		/* print only a portion of the string each loop */
		for (int j = 0; j <= i; j++) {
			printf("%c", hi[j]);
		}

		/* force the buffer to the standard output */
		fflush(stdout);

		/* wait 1 second */
		sleep(1);

		/* return to the beginning of the line */
		printf("\r");
	}

	/* go to the next line */
	printf("\n");
}
