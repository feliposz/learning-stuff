/****************************************************************************
 * sudoku.c
 *
 * Computer Science 50
 * Problem Set 4
 *
 * Implements the game of Sudoku.
 ***************************************************************************/

#include "sudoku.h"

#include <ctype.h>
#include <ncurses.h>
#include <signal.h>
#include <stdbool.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <time.h>


// macro for processing control characters
#define CTRL(x) ((x) & ~0140)

// size of each int (in bytes) in *.bin files
#define INTSIZE 4


// wrapper for our game's globals
struct
{
    // the current level
    char *level;

    // the game's board
    int board[9][9];

    // a table indicating witch cells are blocked for change
    bool blocked[9][9];
    
    // a table indicating witch cells are invalid in the board
    bool valid[9][9];

    // the board's number
    int number;

    // the board's top-left coordinates
    int top, left;

    // the cursor's current location between (0,0) and (8,8)
    int y, x;
    
    // indicate wether the game has been won
    bool is_won;
    
    // if user has made changes
    bool has_undo;
    
    // position of last change and it's value;
    int undo_value, undo_y, undo_x;
    
    // time game has started
    time_t start_time;
    time_t end_time;
    
    bool show_time;
} g;


// prototypes
void draw_grid(void);
void draw_borders(void);
void draw_logo(void);
void draw_numbers(void);
void hide_banner(void);
bool load_board(void);
void handle_signal(int signum);
void log_move(int ch);
void redraw_all(void);
bool restart_game(void);
void show_banner(char *b);
void show_cursor(void);
void shutdown(void);
bool startup(void);
void change_number(int n);
void clear_number(void);
void check_win(void);
void check_valid(int n);
void undo_last(void);
void show_time(void);

/*
 * Main driver for the game.
 */

int
main(int argc, char *argv[])
{
    // define usage
    const char *usage = "Usage: sudoku n00b|l33t [#]\n";

    // ensure that number of arguments is as expected
    if (argc != 2 && argc != 3)
    {
        fprintf(stderr, usage);
        return 1;
    }

    // ensure that level is valid
    if (strcmp(argv[1], "debug") == 0)
        g.level = "debug";
    else if (strcmp(argv[1], "n00b") == 0)
        g.level = "n00b";
    else if (strcmp(argv[1], "l33t") == 0)
        g.level = "l33t";
    else
    {
        fprintf(stderr, usage);
        return 2;
    }

    // n00b and l33t levels have 1024 boards; debug level has 9
    int max = (strcmp(g.level, "debug") == 0) ? 9 : 1024;

    // ensure that #, if provided, is in [1, max]
    if (argc == 3)
    {
        // ensure n is integral
        char c;
        if (sscanf(argv[2], " %d %c", &g.number, &c) != 1)
        {
            fprintf(stderr, usage);
            return 3;
        }

        // ensure n is in [1, max]
        if (g.number < 1 || g.number > max)
        {
            fprintf(stderr, "That board # does not exist!\n");
            return 4;
        }

        // seed PRNG with # so that we get same sequence of boards
        srand(g.number);
    }
    else
    {
        // seed PRNG with current time so that we get any sequence of boards
        srand(time(NULL));

        // choose a random n in [1, max]
        g.number = rand() % max + 1;
    }

    // start up ncurses
    if (!startup())
    {
        fprintf(stderr, "Error starting up ncurses!\n");
        return 5;
    }

    // register handler for SIGWINCH (SIGnal WINdow CHanged)
    signal(SIGWINCH, (void (*)(int)) handle_signal);

    // start the first game
    if (!restart_game())
    {
        shutdown();
        fprintf(stderr, "Could not load board from disk!\n");
        return 6;
    }
    redraw_all();

    g.show_time = false;
    
    // let the user play!
    int ch;
    do
    {
        // refresh the screen
        refresh();

        // get user's input
        ch = getch();

        // capitalize input to simplify cases
        ch = toupper(ch);

        show_time();
        show_cursor();

        // process user's input
        switch (ch)
        {
            // start a new game
            case 'N': 
                g.number = rand() % max + 1;
                if (!restart_game())
                {
                    shutdown();
                    fprintf(stderr, "Could not load board from disk!\n");
                    return 6;
                }
                break;

            // restart current game
            case 'R': 
                if (!restart_game())
                {
                    shutdown();
                    fprintf(stderr, "Could not load board from disk!\n");
                    return 6;
                }
                break;
            // let user manually redraw screen with ctrl-L
            case CTRL('l'):
                redraw_all();
                break;
            case KEY_DOWN:
                g.y = (g.y + 1 + 9) % 9;
                show_cursor();
                break;
            case KEY_UP:
                g.y = (g.y - 1 + 9) % 9;
                show_cursor();
                break;
            case KEY_LEFT:
                g.x = (g.x - 1 + 9) % 9;
                show_cursor();
                break;
            case KEY_RIGHT:
                g.x = (g.x + 1 + 9) % 9;
                show_cursor();
                break;
            case '1':
            case '2':
            case '3':
            case '4':
            case '5':
            case '6':
            case '7':
            case '8':
            case '9':
                change_number(ch - '0');
                break;
            case '0':
            case '.':
            case KEY_DC:
            case KEY_BACKSPACE:
                clear_number();
                break;
            case 'U':
            case CTRL('z'):
                undo_last();
                break;
            case 'T':
                g.show_time = !g.show_time;
                show_time();
                show_cursor();
                break;
        }

        // log input (and board's state) if any was received this iteration
        if (ch != ERR)
            log_move(ch);
    }
    while (ch != 'Q');

    // shut down ncurses
    shutdown();

    // tidy up the screen (using ANSI escape sequences)
    printf("\033[2J");
    printf("\033[%d;%dH", 0, 0);

    // that's all folks
    printf("\nkthxbai!\n\n");
    return 0;
}

/*
 * Insert the given number in the board at the cursor's position.
 */

void change_number(int n)
{
    if (g.is_won)
        return;

    if (!g.blocked[g.y][g.x]) {
        //set undo data before change
        g.has_undo = true;
        g.undo_value = g.board[g.y][g.x];
        g.undo_y = g.y;
        g.undo_x = g.x;
        
        // change board according to user input
        g.board[g.y][g.x] = n;
       
        check_valid(n);
        check_win();
        draw_numbers();
    } else {
        show_banner("You cannot change this number.");
    }
    show_cursor();
}

/*
 * Clear the number at the cursor's position;
 */

void clear_number(void)
{
    if (g.is_won)
        return;

    if (!g.blocked[g.y][g.x]) {
        // set undo data before change
        g.has_undo = true;
        g.undo_value = g.board[g.y][g.x];
        g.undo_y = g.y;
        g.undo_x = g.x;

        // clear value and make cell valid
        g.board[g.y][g.x] = 0;
        g.valid[g.y][g.x] = true;
 
        hide_banner();
        draw_numbers();
    } else {
        show_banner("You cannot erase this number.");
    }
    show_cursor();
}

/*
 * Check if the user has won the game
 */
void check_win(void)
{
    // start assuming the game is won, if not, 
    g.is_won = true;

    // check if there are unfilled cells
    for (int y = 0; y < 9; y++) {
        for (int x = 0; x < 9; x++) {            
            if (g.board[y][x] == 0) {
                g.is_won = false;
                return;
            }
        }
    }
    
    // check for repeats in a row
    for (int y = 0; y < 9; y++) {
        for (int x1 = 0; x1 < 9; x1++) {
            for (int x2 = x1 + 1; x2 < 9; x2++) {
                if (g.board[y][x1] == g.board[y][x2]) {
                    g.is_won = false;
                    return;
                }
            }
        }
    }

    // check for repeats in a column
    for (int x = 0; x < 9; x++) {
        for (int y1 = 0; y1 < 9; y1++) {
            for (int y2 = y1 + 1; y2 < 9; y2++) {
                if (g.board[y1][x] == g.board[y2][x]) {
                    g.is_won = false;
                    return;
                }
            }
        }
    }

    // check for repeats inside a square
    for (int cell = 0; cell < 9; cell++) {
        //  cell is treated as linear [0..8] and converted to y,x coordinates
        int cellx = (cell % 3) * 3;
        int celly = (cell / 3) * 3;
        for (int pos1 = 0; pos1 < 9; pos1++) {
            //  do the same for pos1 and pos2 (below) and add cell coordinates
            int pos1x = pos1 % 3 + cellx;
            int pos1y = pos1 / 3 + celly;
            for (int pos2 = pos1 + 1; pos2 < 9; pos2++) {
                int pos2x = pos2 % 3 + cellx;
                int pos2y = pos2 / 3 + celly;
                if (g.board[pos1y][pos1x] == g.board[pos2y][pos2x]) {
                    g.is_won = false;
                    return;
                }
            }
        }
    }
    
    // if code hasn't returned, game is won: congratulate the user
    show_banner("CONGRATULATIONS! You have won!");
    g.end_time = time(NULL);
    show_cursor();
}

/*
 * Verifies if number entered is valid, i.e.,
 * it's not repeated in the column, row or square.
 */

void check_valid(int n)
{
    //TODO: Implement check for valid number change
    bool is_valid = true;
    
    // check if n is repeated in cursor row
    for (int x = 0; x < 9; x++) {            
        if (x != g.x && g.board[g.y][x] == n) { // skip cursor position
            is_valid = false;
            //g.valid[g.y][x] = false;
        }
    }

    // check if n is repeated in cursor column
    for (int y = 0; y < 9; y++) {            
        if (y != g.y && g.board[y][g.x] == n) { // skip cursor position
            is_valid = false;
            //g.valid[y][g.x] = false;
        }
    }

    // check for repeats inside a square

    //  cell is treated as linear [0..8] and converted to y,x coordinates
    int celly = (g.y / 3) * 3;
    int cellx = (g.x / 3) * 3;
    for (int pos = 0; pos < 9; pos++) {
        //  convert pos to coordinate inside square
        int posx = pos % 3 + cellx;
        int posy = pos / 3 + celly;
        if (posy != g.y && posx != g.x && g.board[posy][posx] == n) {
            is_valid = false;
            //g.valid[posy][posx] = false;
        }
    }
    
    if (is_valid) {
        hide_banner();
        g.valid[g.y][g.x] = true;
    } else {
        g.valid[g.y][g.x] = false;
        show_banner("Invalid entry. Repeated number");
    }
    show_cursor();
    
    //TODO: Remake check so that all row, column or square turns red and is cleared accordingly
}

/*
 * Undo last user's change
 */

void undo_last(void)
{
    if (g.has_undo && !g.is_won) {
        g.y = g.undo_y;
        g.x = g.undo_x;
        g.has_undo = false;
        if (g.undo_value == 0)
            clear_number();
        else
            change_number(g.undo_value);
    }
}

/*
 * Display a clock with time elapsed since current game started
 */

void show_time(void)
{
    if (g.show_time) {
        char s[20];
        if (!g.is_won)
            g.end_time = time(NULL);
        int elapsed = g.end_time - g.start_time;
        int seconds = elapsed % 60;
        elapsed /= 60;
        int minutes = elapsed % 60;
        elapsed /= 60;
        int hours = elapsed;
       
        sprintf(s, "%d:%02d:%02d", hours, minutes, seconds);
        mvaddstr(g.top - 2, g.left, s);
    } else {
        mvaddstr(g.top - 2, g.left, "                   ");
    }
}

/*
 * Draw's the game's board.
 */

void
draw_grid(void)
{
    // get window's dimensions
    int maxy, maxx;
    getmaxyx(stdscr, maxy, maxx);

    // determine where top-left corner of board belongs 
    g.top = maxy/2 - 7;
    g.left = maxx/2 - 30;

    // enable color if possible
    if (has_colors())
        attron(COLOR_PAIR(PAIR_GRID));

    // print grid
    for (int i = 0 ; i < 3 ; ++i )
    {
        mvaddstr(g.top + 0 + 4 * i, g.left, "+-------+-------+-------+");
        mvaddstr(g.top + 1 + 4 * i, g.left, "|       |       |       |");
        mvaddstr(g.top + 2 + 4 * i, g.left, "|       |       |       |");
        mvaddstr(g.top + 3 + 4 * i, g.left, "|       |       |       |");
    }
    mvaddstr(g.top + 4 * 3, g.left, "+-------+-------+-------+" );

    // remind user of level and #
    char reminder[maxx+1];
    sprintf(reminder, "   playing %s #%d", g.level, g.number);
    mvaddstr(g.top + 14, g.left + 25 - strlen(reminder), reminder);

    // disable color if possible
    if (has_colors())
        attroff(COLOR_PAIR(PAIR_GRID));
}


/*
 * Draws game's borders.
 */

void
draw_borders(void)
{
    // get window's dimensions
    int maxy, maxx;
    getmaxyx(stdscr, maxy, maxx);

    // enable color if possible (else b&w highlighting)
    if (has_colors())
    {
        attron(A_PROTECT);
        attron(COLOR_PAIR(PAIR_BORDER));
    }
    else
        attron(A_REVERSE);

    // draw borders
    for (int i = 0; i < maxx; i++)
    {
        mvaddch(0, i, ' ');
        mvaddch(maxy-1, i, ' ');
    }

    // draw header
    char header[maxx+1];
    sprintf(header, "%s by %s", TITLE, AUTHOR);
    mvaddstr(0, (maxx - strlen(header)) / 2, header);

    // draw footer
    mvaddstr(maxy-1, 1, "[N]ew Game   [R]estart Game");
    mvaddstr(maxy-1, maxx-13, "[Q]uit Game");

    // disable color if possible (else b&w highlighting)
    if (has_colors())
        attroff(COLOR_PAIR(PAIR_BORDER));
    else
        attroff(A_REVERSE);
}


/*
 * Draws game's logo.  Must be called after draw_grid has been
 * called at least once.
 */

void
draw_logo(void)
{
    // determine top-left coordinates of logo
    int top = g.top + 2;
    int left = g.left + 30;

    // enable color if possible
    if (has_colors())
        attron(COLOR_PAIR(PAIR_LOGO));

    // draw logo
    mvaddstr(top + 0, left, "                 .___      __          ");
    mvaddstr(top + 1, left, "  ________ __  __| _/____ |  | ____ __ ");
    mvaddstr(top + 2, left, " /  ___/  |  \\/ __ |/  _ \\|  |/ /  |  \\");
    mvaddstr(top + 3, left, " \\___ \\|  |  / /_/ (  <_> )    <|  |  /");
    mvaddstr(top + 4, left, "/____  >____/\\____ |\\____/|__|_ \\____/ ");
    mvaddstr(top + 5, left, "     \\/           \\/           \\/      ");

    // sign logo
    char signature[3+strlen(AUTHOR)+1];
    sprintf(signature, "by %s", AUTHOR);
    mvaddstr(top + 7, left + 35 - strlen(signature) - 1, signature);

    // disable color if possible
    if (has_colors())
        attroff(COLOR_PAIR(PAIR_LOGO));
}


/*
 * Draw's game's numbers.  Must be called after draw_grid has been
 * called at least once.
 */

void
draw_numbers(void)
{
    // iterate over board's numbers
    for (int i = 0; i < 9; i++)
    {
        for (int j = 0; j < 9; j++)
        {
            short attrpair = 0;
            if (has_colors()) {
                if (g.is_won)
                    attrpair = COLOR_PAIR(PAIR_NUMBER_WON);
                else if (g.valid[i][j] == false)
                    attrpair = COLOR_PAIR(PAIR_NUMBER_INVALID);
                else if (g.blocked[i][j] == false)
                    attrpair = COLOR_PAIR(PAIR_NUMBER_USER);
                else
                    attrpair = COLOR_PAIR(PAIR_GRID);
                
                attron(attrpair);
            }
            
            // determine char
            char c = (g.board[i][j] == 0) ? '.' : g.board[i][j] + '0';
            mvaddch(g.top + i + 1 + i/3, g.left + 2 + 2*(j + j/3), c);
            refresh();

            if (has_colors() && attrpair != 0) {
                attroff(attrpair);
            }
        }
    }
}


/*
 * Designed to handles signals (e.g., SIGWINCH).
 */

void
handle_signal(int signum)
{
    // handle a change in the window (i.e., a resizing)
    if (signum == SIGWINCH)
        redraw_all();

    // re-register myself so this signal gets handled in future too
    signal(signum, (void (*)(int)) handle_signal);
}


/*
 * Hides banner.
 */

void
hide_banner(void)
{
    // get window's dimensions
    int maxy, maxx;
    getmaxyx(stdscr, maxy, maxx);

    // overwrite banner with spaces
    for (int i = 0; i < maxx; i++)
        mvaddch(g.top + 16, i, ' ');
}


/*
 * Loads current board from disk, returning true iff successful.
 */

bool
load_board(void)
{
    // open file with boards of specified level
    char filename[strlen(g.level) + 5];
    sprintf(filename, "%s.bin", g.level);
    FILE *fp = fopen(filename, "rb");
    if (fp == NULL)
        return false;

    // determine file's size
    fseek(fp, 0, SEEK_END);
    int size = ftell(fp);

    // ensure file is of expected size
    if (size % (81 * INTSIZE) != 0)
    {
        fclose(fp);
        return false;
    }

    // compute offset of specified board
    int offset = ((g.number - 1) * 81 * INTSIZE);

    // seek to specified board
    fseek(fp, offset, SEEK_SET);

    // read board into memory
    if (fread(g.board, 81 * INTSIZE, 1, fp) != 1)
    {
        fclose(fp);
        return false;
    }

    // w00t
    fclose(fp);
    
    for (int i = 0; i < 9; i++) {
        for (int j = 0; j < 9; j++) {
            g.blocked[i][j] = ( g.board[i][j] != 0 );
            g.valid[i][j] = true;
        }
    }
    return true;
}


/*
 * Logs input and board's state to log.txt to facilitate automated tests.
 */

void
log_move(int ch)
{
    // open log
    FILE *fp = fopen("log.txt", "a");
    if (fp == NULL)
        return;

    // log input
    fprintf(fp, "%d\n", ch);

    // log board
    for (int i = 0; i < 9; i++)
    {
        for (int j = 0; j < 9; j++)
            fprintf(fp, "%d", g.board[i][j]);
        fprintf(fp, "\n");
    }

    // that's it
    fclose(fp);
}


/*
 * (Re)draws everything on the screen.
 */

void
redraw_all(void)
{
    // reset ncurses
    endwin();
    refresh();

    // clear screen
    clear();

    // re-draw everything
    draw_borders();
    draw_grid();
    draw_logo();
    draw_numbers();

    // show cursor
    show_cursor();
}


/*
 * (Re)starts current game, returning true iff succesful.
 */

bool
restart_game(void)
{
    // reload current game
    if (!load_board())
        return false;

    // game has just started, not won
    g.is_won = false;

    // keep track of the time the game has been started
    g.start_time = time(NULL);

    // redraw board
    draw_grid();
    draw_numbers();
    show_time();

    // get window's dimensions
    int maxy, maxx;
    getmaxyx(stdscr, maxy, maxx);

    // hide preview messages
    hide_banner();
    
    // move cursor to board's center
    g.y = g.x = 4;
    show_cursor();

    // remove log, if any
    remove("log.txt");
    
   
    // initialize undo data
    g.has_undo = false;
    g.undo_value = -1;
    g.undo_y = -1;
    g.undo_x = -1;
    
    // w00t
    return true;
}


/*
 * Shows cursor at (g.y, g.x).
 */

void
show_cursor(void)
{
    // restore cursor's location
    move(g.top + g.y + 1 + g.y/3, g.left + 2 + 2*(g.x + g.x/3));
}


/*
 * Shows a banner.  Must be called after show_grid has been
 * called at least once.
 */

void
show_banner(char *b)
{
    // enable color if possible
    if (has_colors())
        attron(COLOR_PAIR(PAIR_BANNER));

    // determine where top-left corner of board belongs 
    mvaddstr(g.top + 16, g.left + 64 - strlen(b), b);

    // disable color if possible
    if (has_colors())
        attroff(COLOR_PAIR(PAIR_BANNER));
}


/*
 * Shuts down ncurses.
 */

void
shutdown(void)
{
    endwin();
}


/*
 * Starts up ncurses.  Returns true iff successful.
 */

bool
startup(void)
{
    // initialize ncurses
    if (initscr() == NULL)
        return false;

    // prepare for color if possible
    if (has_colors())
    {
        // enable color
        if (start_color() == ERR || attron(A_PROTECT) == ERR)
        {
            endwin();
            return false;
        }

        // initialize pairs of colors
        if (init_pair(PAIR_BANNER, FG_BANNER, BG_BANNER) == ERR ||
            init_pair(PAIR_GRID, FG_GRID, BG_GRID) == ERR ||
            init_pair(PAIR_BORDER, FG_BORDER, BG_BORDER) == ERR ||
            init_pair(PAIR_LOGO, FG_LOGO, BG_LOGO) == ERR ||
            init_pair(PAIR_NUMBER_WON, FG_NUMBER_WON, BG_NUMBER_WON) == ERR ||
            init_pair(PAIR_NUMBER_INVALID, FG_NUMBER_INVALID, BG_NUMBER_INVALID) == ERR ||
            init_pair(PAIR_NUMBER_USER, FG_NUMBER_USER, BG_NUMBER_USER) == ERR)
        {
            endwin();
            return false;
        }
    }

    // don't echo keyboard input
    if (noecho() == ERR)
    {
        endwin();
        return false;
    }

    // disable line buffering and certain signals
    if (raw() == ERR)
    {
        endwin();
        return false;
    }

    // enable arrow keys
    if (keypad(stdscr, true) == ERR)
    {
        endwin();
        return false;
    }

    // wait 1000 ms at a time for input
    timeout(1000);

    // w00t
    return true;
}
