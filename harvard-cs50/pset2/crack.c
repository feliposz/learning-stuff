#define _XOPEN_SOURCE
#include <unistd.h>
#include <stdio.h>
#include <cs50.h>
#include <time.h>
#include <string.h>

int try_dict(string dict, string password);
int bruteforce(string password);
void clearlf(string s);
int check(string guess, string password);

int main(int argc, char *argv[])
{
	if (argc != 2) {
		printf("Usage: %s <encrypted_password>\n", argv[0]);
	}

	string password = argv[1];

	/*
	printf("English dictionary\n");
	if (try_dict("/usr/share/dict/words", password))
		return 0;
	printf("List\n");
	if (try_dict("words", password))
		return 0;
	*/
	printf("Bruteforce\n");
	if (bruteforce(password))
		return 0;
	return 1;

}

int try_dict(string dict, string password)
{
	FILE *fp = fopen(dict, "r");

	char guess[20];
	while (fgets(guess, 20, fp) != NULL ) {
		clearlf(guess);
		if (check(guess, password)) {
			fclose(fp);
			return 1;
		}
	}
	fclose(fp);
	return 0;
}

void clearlf(string s)
{
	for (int i = 0; s[i] != '\0'; i++) {
		if (s[i] == '\n') {
			s[i] = '\0';
			break;
		}
	}
}

int check(string guess, string password)
{
	string coded = crypt(guess, password);
	//printf("\r%-20.20s", guess);
	//fflush(stdout);
	if (!strcmp(coded, password)) {
		printf("\nFOUND: %s\n", guess);
		return 1;
	} else {
		return 0;
	}
}

int bruteforce(string password)
{
	char guess[9];

	time_t t = time(NULL);
	guess[1] = '\0';
	for (guess[0] = ' '; guess[0] <= '~'; guess[0]++)
		if (check(guess, password))
			return 1;
	printf("1: %d\n", time(NULL) - t);
	fflush(stdout);


	t = time(NULL);
	guess[2] = '\0';
	for (guess[1] = ' '; guess[1] <= '~'; guess[1]++)
		for (guess[0] = ' '; guess[0] <= '~'; guess[0]++)
			if (check(guess, password))
				return 1;
	printf("2: %d\n", time(NULL) - t);
	fflush(stdout);

	t = time(NULL);
	guess[3] = '\0';
	for (guess[2] = ' '; guess[2] <= '~'; guess[2]++)
		for (guess[1] = ' '; guess[1] <= '~'; guess[1]++)
			for (guess[0] = ' '; guess[0] <= '~'; guess[0]++)
				if (check(guess, password))
					return 1;
	printf("3: %d\n", time(NULL) - t);
	fflush(stdout);

	t = time(NULL);
	guess[4] = '\0';
	for (guess[3] = ' '; guess[3] <= '~'; guess[3]++)
		for (guess[2] = ' '; guess[2] <= '~'; guess[2]++)
			for (guess[1] = ' '; guess[1] <= '~'; guess[1]++)
				for (guess[0] = ' '; guess[0] <= '~'; guess[0]++)
					if (check(guess, password))
						return 1;
	printf("4: %d\n", time(NULL) - t);
	fflush(stdout);

/*	guess[5] = '\0';
	for (guess[4] = ' '; guess[4] <= '~'; guess[4]++) 
	for (guess[3] = ' '; guess[3] <= '~'; guess[3]++) 
	for (guess[2] = ' '; guess[2] <= '~'; guess[2]++) 
		for (guess[1] = ' '; guess[1] <= '~'; guess[1]++) 
			for (guess[0] = ' '; guess[0] <= '~'; guess[0]++) 
				if (check(guess, password))
					return 1;
*/
	return 0;
}
