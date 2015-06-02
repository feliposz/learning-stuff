SCREEN 12
CLS

CONST stars = 30

TYPE TStar
  x AS DOUBLE
  y AS DOUBLE
  z AS DOUBLE
END TYPE

DIM starfield(1 TO stars) AS TStar

'Initialize starfield
FOR i% = 1 TO stars
  starfield(i%).x = RND - .5
  starfield(i%).y = RND - .5
  starfield(i%).z = RND
NEXT i%

FOR c% = 0 TO 15
  r% = c% * 4
  g% = c% * 4
  b% = c% * 4
  PALETTE c%, r% + g% * 256 + b% * 65536
NEXT c%

DO

  'Draw starfield

  FOR i% = 1 TO stars
    'Erase current star at position
    x = starfield(i%).x * 640 * starfield(i%).z + 320
    y = starfield(i%).y * 480 * starfield(i%).z + 240
    PSET (x, y), 0
 
    'Move star forward
    starfield(i%).z = starfield(i%).z + .02

    'Move it back an choose new position if reached screen
    IF starfield(i%).z >= 1 THEN
      starfield(i%).x = RND - .5
      starfield(i%).y = RND - .5
      starfield(i%).z = 0
    END IF
 
    'Draw star
    x = starfield(i%).x * 640 * starfield(i%).z + 320
    y = starfield(i%).y * 480 * starfield(i%).z + 240
    c = starfield(i%).z * 15 'Different shades of gray
    PSET (x, y), c
  NEXT i%

LOOP WHILE INKEY$ = ""

