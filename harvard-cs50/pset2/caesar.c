#include <cs50.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>

int main(int argc, char *argv[])
{
	if (argc != 2) {
		printf("Usage %s <key>\n", argv[0]);
		return 1;
	}
	int key = atoi(argv[1]);

	string s = GetString();	

	for (int i = 0, n = strlen(s); i < n; i++) {
		char c = s[i];
		if (c >= 'A' && c <= 'Z') {
			c = ((c - 'A') + key) % 26 + 'A';
		} else if (c >= 'a' && c <= 'z') {
			c = ((c - 'a') + key) % 26 + 'a';
		}
		printf("%c", c);
	}
	printf("\n");

	free(s);
}
