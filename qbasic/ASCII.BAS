DEFINT A-Z

CLS
FOR i = 0 TO 255
  PRINT RIGHT$("    " + STR$(i), 4); " = "; CHR$(i);
NEXT i

