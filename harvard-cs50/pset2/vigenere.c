#include <cs50.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <ctype.h>

int main(int argc, char *argv[])
{
	if (argc != 2) {
		printf("Usage %s <key>\n", argv[0]);
		return 1;
	}
	string key = argv[1];
	int keysize = strlen(key);

	for (int i = 0, n = strlen(key); i < n; i++) {
		if (isupper(key[i])) {
			key[i] = key[i] - 'A';
		} else if (islower(key[i])) {
			key[i] = key[i] - 'a';
		} else {
			printf("Invalid key provided. Please, use only letters.");
			return 1;
		}
	}

	string s = GetString();	

	for (int i = 0, j = 0, n = strlen(s); i < n; i++) {
		if (isupper(s[i])) {
			s[i] = ( (s[i] - 'A') + key[j] ) % 26 + 'A';
			j = (j + 1) % keysize;
		} else if (islower(s[i])) {
			s[i] = ( (s[i] - 'a') + key[j] ) % 26 + 'a';
			j = (j + 1) % keysize;
		}
	}
	printf("%s\n", s);
	
	free(s);
}
