/****************************************************************************
 * sudoku.h
 *
 * Computer Science 50
 * Problem Set 4
 *
 * Compile-time options for the game of Sudoku.
 ***************************************************************************/

// game's author
#define AUTHOR "Felipo Soranz"

// game's title
#define TITLE "Sudoku"

// banner's colors
#define FG_BANNER COLOR_CYAN
#define BG_BANNER COLOR_BLACK

// grid's colors
#define FG_GRID COLOR_WHITE
#define BG_GRID COLOR_BLACK

// border's colors
#define FG_BORDER COLOR_YELLOW
#define BG_BORDER COLOR_BLUE

// logo's colors
#define FG_LOGO COLOR_GREEN
#define BG_LOGO COLOR_BLACK

// color of numbers when game is won
#define FG_NUMBER_WON COLOR_GREEN
#define BG_NUMBER_WON COLOR_BLACK

// color of numbers when number is invalid (repeated)
#define FG_NUMBER_INVALID COLOR_RED
#define BG_NUMBER_INVALID COLOR_BLACK

// color of user numbers to differentiate from puzzle numbers (same as grid)
#define FG_NUMBER_USER COLOR_YELLOW
#define BG_NUMBER_USER COLOR_BLACK

// nicknames for pairs of colors
enum { PAIR_BANNER = 1, PAIR_GRID, PAIR_BORDER, PAIR_LOGO,
       PAIR_NUMBER_WON, PAIR_NUMBER_INVALID, PAIR_NUMBER_USER };
