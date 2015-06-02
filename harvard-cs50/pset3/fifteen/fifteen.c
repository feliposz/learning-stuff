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


// constants
#define DIM_MIN 3
#define DIM_MAX 9


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
            printf("ftw!\n");
            break;
        }

        // prompt for move
        printf("Tile to move: ");
        int tile = GetInt();

        // move if possible, else report illegality
        if (!move(tile))
        {
            printf("\nIllegal move.\n");
            usleep(500000);
        }

        // sleep thread for animation's sake
        usleep(500000);
    }

    // that's all folks
    return 0;
}


/*
 * Clears screen using ANSI escape sequences.
 */

void
clear(void)
{
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
    	for (int x = 0; x < d; x++) {
			if (board[y][x] == 0) {
				printf("%5s", "_");
	    	} else {
	    		printf("%5d", board[y][x]);
	    	}
		}
		printf("\n");
	}
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
