all: recover

recover: recover.c
	gcc -ggdb -std=c99 -Wall -Werror -Wformat=0 -o recover recover.c

clear:
	rm -f *.o core a.out recover