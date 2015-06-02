'Description: A small program to demonstrate how to change palettes
'drawing some rectangles and slightly changing palette at the same time
'Author: Felipo Soranz
'Date: 2014-04-12

DEFINT A-Z
'Array for storing palette
DIM p(16) AS LONG

'Initialize random number generator with current seconds
RANDOMIZE TIMER

SCREEN 12 'Graphical mode 640x480 16 attr (256k colors)
CLS

'Randomize the palette
FOR c = 0 TO 15
  r = INT(RND * 64)
  g = INT(RND * 64)
  B = INT(RND * 64)
  p(c) = r + g * 256 + B * 65536
NEXT c

'Change the whole palette at one
PALETTE USING p

'Draw some vertical bars on the screen
'FOR i = 0 TO 100
'NEXT i

DO

  FOR c = 0 TO 15
    'Get color in palette
    r = p(c) MOD 256
    g = (p(c) \ 256) MOD 256
    B = p(c) \ 65536

    'Change a little bit
    r = r + INT(RND * 3) - 1
    g = g + INT(RND * 3) - 1
    B = B + INT(RND * 3) - 1

    'Keeps values within boundaries
    IF r < 0 THEN r = 0
    IF g < 0 THEN g = 0
    IF B < 0 THEN B = 0
    IF r > 63 THEN r = 63
    IF g > 63 THEN g = 63
    IF B > 63 THEN B = 63

    'Set color
    p(c) = r + g * 256 + B * 65536
  NEXT c

  'Set whole palette at once
  PALETTE USING p

  'Set random coordinates for a new rectangle
  w = INT(RND * 200) + 20
  h = INT(RND * 200) + 20
  x = INT(RND * (640 - w))
  y = INT(RND * (480 - h))
  c = INT(RND * 16)
  
  LINE (x, y)-(x + w, y + h), c, BF

  'IF w > h THEN r = w \ 2 ELSE r = h \ 2
  'CIRCLE (x + w \ 2, y + h \ 2), r, c, , , h / w

  'SLEEP 1
 
  'If ESC key pressed, exit the program
  IF INKEY$ <> "" THEN EXIT DO

LOOP

