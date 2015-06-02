#include "escansi.h"
#include <stdio.h>

void clearscreen(void)
{
	printf("\033[2J");
	position(0, 0);
}

void textcolor(int color)
{
	printf("\033[%02dm", color + 30);
}

void backcolor(int color)
{
	printf("\033[%02dm", color + 40);
}

void texteffect(int effect)
{
	printf("\033[%02dm", effect);
}

void position(int line, int col)
{
	printf("\033[%d;%dH", line, col);
}
