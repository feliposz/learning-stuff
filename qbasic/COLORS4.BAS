'Description: A small program to demonstrate how to set palettes
'Author: Felipo Soranz
'Date: 2014-04-12

DEFINT A-Z
'Array for storing palette
DIM p(16) AS LONG

'Initialize random number generator with current seconds
RANDOMIZE TIMER

SCREEN 12 'Graphical mode 640x480 16 attr (256k colors)
CLS

'Draw some vertical bars on the screen
FOR c = 0 TO 15
  LINE (c * 40, 0)-((c + 1) * 40, 479), c, BF
NEXT c

'Draw some horizontal bars over the vertical ones
FOR c = 0 TO 15
  LINE (0, 100 + c * 5)-(639, 102 + c * 5), c, BF
  LINE (0, 200 + c * 10)-(639, 205 + c * 10), c, BF
NEXT c

'Draw some random dots in the lower part of the screen
FOR x = 0 TO 639 STEP 3
  FOR y = 400 TO 479 STEP 3
    PSET (x, y), INT(RND * 16)
  NEXT y
NEXT x

DO

  'Randomize the palette
  FOR c = 0 TO 15
    r = INT(RND * 64)
    g = INT(RND * 64)
    b = INT(RND * 64)
    p(c) = r + g * 256 + b * 65536
   
    'To avoid printing 0 text in 0 background
    IF c = 0 THEN COLOR 15 ELSE COLOR c

    'Write the Red Green Blue value for each color in the corresponding bar
    LOCATE 1, c * 5 + 1: PRINT "R"; RIGHT$(" " + STR$(r), 2);
    LOCATE 2, c * 5 + 1: PRINT "G"; RIGHT$(" " + STR$(g), 2);
    LOCATE 3, c * 5 + 1: PRINT "B"; RIGHT$(" " + STR$(b), 2);
  NEXT c

  'Change the whole palette at one
  PALETTE USING p

  'Pause until key is pressed
  DO
    k$ = INKEY$
    IF k$ <> "" THEN EXIT DO
  LOOP

  SOUND 220, 2

  'If ESC key pressed, exit the program
  IF k$ = CHR$(27) THEN EXIT DO

LOOP

