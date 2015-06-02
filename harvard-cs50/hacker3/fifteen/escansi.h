#ifndef _ESCANSI_H
#define _ESCANSI_H

#define BLACK   0
#define RED     1
#define GREEN   2
#define YELLOW  3
#define BLUE    4
#define MAGENTA 5
#define CYAN    6
#define WHITE   7
#define DEFAULT 9

#define NORMAL    0
#define BOLD      1
#define FAINT     2
#define STANDOUT  3
#define UNDERLINE 4
#define BLINK     5
#define REVERSE   7
#define INVISIBLE 8
#define NORMALDISP  22
#define NOSTANDOUT  23
#define NOUNDERLINE 24
#define NOBLINK     25
#define NOREVERSE   27

void clearscreen(void);

void textcolor(int color);

void backcolor(int color);

void texteffect(int effect);

void position(int line, int col);

#endif
