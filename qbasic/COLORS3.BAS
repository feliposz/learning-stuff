DECLARE SUB DrawColors (Label$, Row%, Column%, Low%, High%)
DEFINT A-Z
SCREEN 13
CLS

DIM SHARED W, H

W = 13
H = 8

DrawColors "Default", 1, 1, 0, 15
DrawColors "Gray", 3, 1, 16, 31
DrawColors "Rainbow 1", 5, 1, 32, 55
DrawColors "Rainbow 2", 7, 1, 56, 79
DrawColors "Rainbow 3", 9, 1, 80, 103
DrawColors "Rainbow 4", 11, 1, 104, 127
DrawColors "Rainbow 5", 13, 1, 128, 151
DrawColors "Rainbow 6", 15, 1, 152, 175
DrawColors "Rainbow 7", 17, 1, 176, 199
DrawColors "Rainbow 8", 19, 1, 200, 223
DrawColors "Rainbow 9", 21, 1, 224, 247
DrawColors "Rainbow 10", 23, 1, 248, 255

'WaitKeyPress
LOCATE 25: PRINT "Press ESC to quit.";

DIM oldx AS INTEGER, oldy AS INTEGER, speed AS INTEGER

x = 0
y = 0
oldx = 0
oldy = 0
speed = 4

DO

PSET (oldx, oldy), c

  c = POINT(x, y)

  c = c MOD 256

  IF oldx <> x OR oldy <> y THEN
    ' Get palette (not working properly, why?)
    OUT &H3C8, c
    r = INP(&H3C9)
    g = INP(&H3C9)
    b = INP(&H3C9)
    LOCATE 24, 10: PRINT "R:"; r; "G:"; g; "B:"; b;
  END IF


  PSET (x, y), 15
  oldx = x
  oldy = y

  LOCATE 1, 30: PRINT "Color: "; RIGHT$("   " + STR$(c), 3);

  k$ = INKEY$

  SELECT CASE k$
    CASE CHR$(27)
      EXIT DO
    CASE CHR$(0) + "H"
      LOCATE 25, 20: PRINT CHR$(24);
      y = y - speed
    CASE CHR$(0) + "P"
      LOCATE 25, 20: PRINT CHR$(25);
      y = y + speed
    CASE CHR$(0) + "K"
      LOCATE 25, 20: PRINT CHR$(27);
      x = x - speed
    CASE CHR$(0) + "M"
      LOCATE 25, 20: PRINT CHR$(26);
      x = x + speed
    CASE ELSE
      LOCATE 25, 20: PRINT k$;
  END SELECT

  x = (x + 320) MOD 320
  y = (y + 200) MOD 200

  PSET (oldx, oldy), c


LOOP

SUB DrawColors (Label$, Row%, Column%, Low%, High%)

LOCATE Row%, Column%
PRINT Label$; ": "; LTRIM$(STR$(Low%)); " - "; LTRIM$(STR$(High%))

FOR c = Low% TO High%
  x = (c - Low%) * W
  y = H * Row% - 1
  LINE (x, y)-(x + W, y + H), c, BF
NEXT c

END SUB

SUB WaitKeyPress
WHILE INKEY$ = "": WEND
END SUB

