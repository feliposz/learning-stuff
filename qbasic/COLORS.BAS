DEFINT A-Z
SCREEN 13
CLS

w = 8
h = 8
m = 320 \ w

FOR x = 0 TO 319 STEP w
  FOR y = 0 TO 199 STEP h
    c = x \ w + m * (y \ h)
    LINE (x, y)-(x + w, y + h), c, BF
    'LOCATE y \ h + 1, x \ w * 2 + 1
    'PRINT LTRIM$(STR$(c));
  NEXT y
NEXT x

