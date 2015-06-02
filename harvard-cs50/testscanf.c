#include <stdio.h>

int main(void)
{
	char buffer[20];
	
	while (1) {
		scanf("%s", buffer);
		printf("WORD: [ %s ]\n", buffer);
	}
}
