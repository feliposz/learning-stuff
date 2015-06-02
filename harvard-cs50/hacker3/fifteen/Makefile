#
# Makefile
#
# Computer Science 50
# Problem Set 3
#

fifteen: fifteen.c escansi.c escansi.h
	gcc -ggdb -std=c99 -Wall -Werror -Wformat=0 -o fifteen fifteen.c escansi.c -lcs50 -lm

testansi: testansi.c escansi.c escansi.h
	gcc -ggdb -std=c99 -Wall -Werror -Wformat=0 -o testansi testansi.c escansi.c -lcs50 -lm

clean:
	rm -f *.o a.out core fifteen testansi
