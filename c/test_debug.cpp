#include <stdio.h>
#include <stdlib.h>

int main()
{
	int i;

	for (i = 0; i < 10; i++) {
		if (i%2 == 0) {
			printf("%d eh par.\n", i);
		} else {
			printf("%d eh impar.\n", i);
		}
	}

	system("pause");
}
