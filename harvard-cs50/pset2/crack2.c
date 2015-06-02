#define _XOPEN_SOURCE
#include <unistd.h>
#include <stdio.h>
#include <cs50.h>
#include <time.h>
#include <string.h>

int try_dict(string dict, string password);
int try_numbers(string password);
void clearlf(string s);
int check(string guess, string password);

int main(int argc, char *argv[])
{
	if (argc != 2) {
		printf("Usage: %s <encrypted_password>\n", argv[0]);
	}

	string password = argv[1];


	printf("English dictionary\n");
	if (try_dict("/usr/share/dict/words", password))
		return 0;
	printf("Sequential numbers\n");
	if (try_numbers(password))
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

int try_numbers(string password)
{
	char guess[20];
	for (int i = 0; i <= 100000; i++) {
		sprintf(guess, "%d", i);
		if (check(guess, password)) {
			return 1;
		}
	}
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
	if (!strcmp(coded, password)) {
		printf("\nFOUND: %s\n", guess);
		return 1;
	} else {
		return 0;
	}
}

