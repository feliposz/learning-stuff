/***************************************************************************
 * fifteen.c
 *
 * Computer Science 50
 * Problem Set 3
 *
 * Implements The Game of Fifteen (generalized to d x d).
 *
 * Usage: fifteen d
 *
 * whereby the board's dimensions are to be d x d,
 * where d must be in [DIM_MIN,DIM_MAX]
 *
 * Note that usleep is obsolete, but it offers more granularity than
 * sleep and is simpler to use than nanosleep; `man usleep` for more.
 ***************************************************************************/
 
#define _XOPEN_SOURCE 500

#include <cs50.h>
#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <string.h>
#include "escansi.h"

// constants
#define DIM_MIN 3
#define DIM_MAX 9
//special return values for gettile
#define GOD_MODE (INT_MAX)
#define MIX_MODE (INT_MAX-1)

// board
int board[DIM_MAX][DIM_MAX];

// dimensions
int d;


// prototypes
void clear(void);
void greet(void);
void init(void);
void draw(void);
bool move(int tile);
bool won(void);
int gettile(void);

void god(void);
void mix(void);
bool find(int tile, int *py, int *px);

int
main(int argc, char *argv[])
{
    // greet user with instructions
    greet();

    // ensure proper usage
    if (argc != 2)
    {
        printf("Usage: %s d\n", argv[0]);
        return 1;
    }

    // ensure valid dimensions
    d = atoi(argv[1]);
    if (d < DIM_MIN || d > DIM_MAX)
    {
        printf("Board must be between %d x %d and %d x %d, inclusive.\n",
         DIM_MIN, DIM_MIN, DIM_MAX, DIM_MAX);
        return 2;
    }

    // initialize the board
    init();

    // accept moves until game is won
    while (true)
    {
        // clear the screen
        clear();

        // draw the current state of the board
        draw();

        // check for win
        if (won())
        {
        	position(21, 30);
        	textcolor(WHITE);
        	backcolor(GREEN);
            printf("ftw!\n");
            break;
        }

        // prompt for move
        position(20, 30);
        textcolor(BLACK);
        backcolor(WHITE);
        printf("Tile to move: ");
        //int tile = GetInt();

        int tile = gettile();
        if (tile == GOD_MODE)
        	continue; //return 0; //puzzle solved, nothing to do
        else if (tile == MIX_MODE)
        	continue; //no move to process, skip it

        // move if possible, else report illegality
        if (!move(tile))
        {
        	position(21, 30);
        	textcolor(RED);
        	backcolor(WHITE);
            printf("Illegal move.\n");
            usleep(500000);
        }

        // sleep thread for animation's sake
        usleep(200000);
    }

	textcolor(DEFAULT);
	backcolor(DEFAULT);
	texteffect(NORMAL);

    // that's all folks
    return 0;
}

/*
 * Receibe input from the user processing special keywords
 */

int gettile(void)
{
		while (true) {
	        char *s = GetString();
    	    if (s == NULL) {
    	    	return 0;
    	    } else if (strcmp(s, "GOD") == 0) {
       			god();
        		free(s);
   	    		return GOD_MODE;
       		} else if (strcmp(s, "MIX") == 0) {
       			mix();
       			free(s);
        		return MIX_MODE;
        	} else {
        		int n; char c;
        		if (sscanf(s, " %d %c", &n, &c) == 1) {
        			free(s);
 					return n;
        		} else {
        			free(s);
        			printf("Retry: ");
        		}
        	}
	    }
}

/*
 * Clears screen using ANSI escape sequences.
 */

void
clear(void)
{
	backcolor(BLUE);
    printf("\033[2J");
    printf("\033[%d;%dH", 0, 0);
}


/*
 * Greets player.
 */

void
greet(void)
{
    clear();
    position(2,25);
    backcolor(CYAN);
    textcolor(WHITE);
    texteffect(BOLD);
    printf("WELCOME TO THE GAME OF FIFTEEN\n");
    usleep(2000000);
}


/*
 * Initializes the game's board with tiles numbered 1 through d*d - 1
 * (i.e., fills 2D array with values but does not actually print them).  
 */

void
init(void)
{
    // TODO
    for (int y = 0; y < d; y++) {
    	for (int x = 0; x < d; x++) {
			//populate board from largest to lowest number
    		board[y][x] = d*d - y*d - x - 1;

			//if dimension is even, reverse 1 and 2 to make the game solvable
    		if (d%2 == 0) {
    			if (board[y][x] == 2) {
    				board[y][x] = 1;
    			} else if (board[y][x] == 1) {
    				board[y][x] = 2;
    			}
			}

    		//DEBUG: uncomment to test won() without playing the game
    		//board[y][x] = (y*d + x + 1) % (d*d);
    	}
	}
}


/* 
 * Prints the board in its current state.
 */

void
draw(void)
{
    // draw board, assuming 0 is the empty space
    // x = column (vertical coord), y = line (horizontal coord)
    for (int y = 0; y < d; y++) {
		texteffect(BOLD);
   		textcolor(YELLOW);
		backcolor(CYAN);
    	position(y*2 + (22-d*2)/2 - 1, (79-d*5)/2);
    	for (int x = 0; x < d; x++) {
    		printf("+----");
    	}
    	printf("+\n");
    	for (int x = 0; x < d; x++) {
    		position(y*2 + (22-d*2)/2, x*5 + (79-d*5)/2);
			if (board[y][x] == 0) {
				textcolor(YELLOW);
				printf("| ");
				backcolor(GREEN);
				textcolor(WHITE);
				printf("  ");
	    		backcolor(CYAN);
	    		printf(" ");
	    	} else if (board[y][x] == 99) { //DEBUG
	    		textcolor(RED);
	    		printf("| %2d ", board[y][x]);
	    	} else {
	    		textcolor(YELLOW);
	    		printf("| %2d ", board[y][x]);
	    	}
		}
		printf("|\n");
	}
	texteffect(BOLD);
	textcolor(YELLOW);
	backcolor(CYAN);
   	position(d*2 + (22-d*2)/2 - 1, (79-d*5)/2);
   	for (int x = 0; x < d; x++) {
   		printf("+----");
   	}
   	printf("+\n");
}


/* 
 * If tile borders empty space, moves tile and returns true, else
 * returns false. 
 */

bool
move(int tile)
{
	//find the tile in the board
    for (int y = 0; y < d; y++) {
    	for (int x = 0; x < d; x++) {
    		if (board[y][x] == tile) {
				//tile found, check if move is possible
    			if (y > 0 && board[y-1][x] == 0) {          //check above
    				board[y-1][x] = tile;
    				board[y][x] = 0;
    				return true;
    			} else if (y < d-1 && board[y+1][x] == 0) { //check below
    				board[y+1][x] = tile;
    				board[y][x] = 0;
    				return true;
    			} else if (x > 0 && board[y][x-1] == 0) {   //check left
    				board[y][x-1] = tile;
    				board[y][x] = 0;
    				return true;
    			} else if (x < d-1 && board[y][x+1] == 0) { //check right
    				board[y][x+1] = tile;
    				board[y][x] = 0;
    				return true;
    			} else { //can't move!
    				return false;
    			}
    		}
		}
	}

    return false;
}


/*
 * Returns true if game is won (i.e., board is in winning configuration),
 * else false.
 */

bool
won(void)
{
    /* check if the elements are in the proper position
     * n is the current element position
     * since n starts in 0 but the data starts with 1:
     *   n + 1 = expected element value
     * max is the number of elements - 1 (the empty space)
     */
    for (int n = 0, max = d * d - 1; n < max; n++) {
    	// calculate coordinates for the current element
    	int x = n % d;
    	int y = n / d;
    	// if the current board element is not the expected return
		if (board[y][x] != n + 1)
			return false;
    }
    // if all elements are in order, return true
    return true;
}

typedef struct {
	int y, x;
} point;

bool god_move(int n, point dest);

void god()
{
	//TODO: general case for 1, improve to consider others
	for (int n = 1; n < 2; n++) {
		point dest;
		dest.x = (n - 1) % d;
		dest.y = (n - 1) / d;
		if (god_move(n, dest) == false) {
			printf("Could not complete move\n");
            usleep(500000);
		}
	}
}

void animove(int n)
{
	move(n);
	clear();
	draw();
    usleep(500000);
}

char moveboard[DIM_MAX][DIM_MAX];

int findpath(point src, point dest, point path[])
{
	point dist, mov, cur = src;
	
	// movement direction
	if (src.y < dest.y)
		mov.y = 1;
	else if (src.y > dest.y)
		mov.y = -1;
	else
		mov.y = 0;

	if (src.x < dest.x)
		mov.x = 1;
	else if (src.x > dest.x)
		mov.x = -1;
	else
		mov.x = 0;

	// calculate absolute distance
	dist.y = abs(src.y - dest.y);
	dist.x = abs(src.x - dest.x);

	// start moving from bigges axis 
	bool axisflag = dist.y > dist.x; // true ->  y, false -> x

	// while destination not reached
	int size = 0;
	while (cur.y != dest.y || cur.x != dest.y) {
		if (axisflag) {	
			cur.y += mov.y;
			if (cur.y == dest.y) { mov.y = 0; }
		} else {
			cur.x += mov.x;
			if (cur.x == dest.x) { mov.x = 0; }
		}
		path[size++] = cur;
		axisflag = !axisflag; //alternate axis to move diagonally
	}

	return size;
}

bool god_move(int n, point dest)
{
	point cur, empty;
	find(n, &cur.y, &cur.x);
	find(0, &empty.y, &empty.x);
	
	// n is at destination, nothing to do
	if (dest.y == cur.y && dest.x == cur.x) {
		return true;
	}

	// empty is at destination, move(n) and return
	if (empty.y == dest.y && empty.x == dest.x) {
		animove(n);
		return true;
	}

	point path[DIM_MAX * DIM_MAX];
//	point path2[DIM_MAX * DIM_MAX];

	int size = findpath(cur, dest, path);
	
	for (int i = 0; i < size; i++) {
		
		int y = path[i].y;
		int x = path[i].x;
		board[y][x] = 99-i;
		
		/*
		int size2 = findpath(empty, path[i], path2);
		
		for (int j = 0; j < size2; j++) {
			int y = path2[j].y;
			int x = path2[j].x;
			board[y][x] = 99;
			//animove(board[y][x]);
		}
		break; //DEBUG
		//move(n); */
	}
	draw();

	return false;
}

bool find(int tile, int *py, int *px)
{
    for (int y = 0; y < d; y++) {
  		for (int x = 0; x < d; x++) {
   			if (board[y][x] == tile) {
   				*py = y;
   				*px = x;
   				return true;
   			}
   		}
   	}
   	return false;
}

void mix()
{
	//TODO: implement a method to scramble the numbers
	for (int i = 0; i < 100; i++)
	{
		int y, x;
		// find empty tile
		find(0, &y, &x);
		// pick a random direction
		int direction = rand() % 4;
		// try to move an adjacent tile (up, down, left, right)
		switch (direction) {
			case 0: if (y > 0)   move(board[y-1][x]); break;
			case 1: if (y < d-1) move(board[y+1][x]); break;
			case 2: if (x > 0)   move(board[y][x-1]); break;
			case 3: if (x < d-1) move(board[y][x+1]); break;
		}
		clear();
		draw();
        usleep(50000);
	}
}
