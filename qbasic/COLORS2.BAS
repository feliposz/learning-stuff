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

