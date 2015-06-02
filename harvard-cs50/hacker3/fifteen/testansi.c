#include "escansi.h"
#include <stdio.h>

int main(void)
{
	clearscreen();

	position(5, 20);
	textcolor(RED);
	backcolor(BLUE);
	texteffect(BOLD);
	printf(" Hello ");

	position(15, 55);
	backcolor(RED);
	textcolor(WHITE);
	texteffect(FAINT);
	printf(" World ");

	textcolor(DEFAULT);
	texteffect(NORMAL);
	printf("\n\n\n");
}
