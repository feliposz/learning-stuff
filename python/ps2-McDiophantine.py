# Problem Set 2 - Problem 1
# Author: Felipo Soranz
# Time: 20 Minutes

# Solution range for n
MIN, MAX = 50, 55

# Try to find a solution for the equation:
#  6*a + 9*b + 20*c = n
#  for MIN < n < MAX
#  where a, b, c are positive integers

c = 0
while 20 * c <= MAX:
    b = 0
    while 9 * b <= MAX:
        a = 0
        while 6 * a <= MAX:
            total = 6 * a + 9 * b + 20 * c
            if total >= MIN and total <= MAX:
                print(("a = %d, b = %d, c = %d, total = %d") % (a, b, c, total))
            a += 1
        b += 1
    c += 1
 
# Problem Set 2 - Problem 2
# Demonstration that 56-65 is solvable considering 50-55 as solvable
# Time: 20 Minutes (with interruptions ~ 10 min)

# Demonstration...
##56 = 50 + 6
##57 = 51 + 6
##58 = 52 + 6
##59 = 53 + 6, 50 + 9
##60 = 54 + 6, 51 + 9
##61 = 55 + 6, 52 + 9
##62 = 53 + 9
##63 = 54 + 9
##64 = 55 + 9
##65 = 50 + 6 + 9
##66 = 51 + 6 + 9
##...

