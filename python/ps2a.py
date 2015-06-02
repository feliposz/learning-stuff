# Problem Set 2 - Problem 3
# Author: Felipo Soranz
# Time: ~ 30 Minutes

# For the Diophantine equation (i.e. a, b, c, n are all positive integers):
#  a * 6 + b * 9 + c * 20 = n
# Try to find the larges possible value for n where the equation is false

count_possibles = 0
max_impossible = 0

for i in range(1,150):
    c = 0
    impossible = True
    while c * 20 <= i and impossible:
        b = 0
        while b * 9 <= i and impossible:
            a = 0
            while a * 6 <= i and impossible:
                total = a * 6 + b * 9 + c * 20
                if total == i:
                    impossible = False
                a += 1
            b += 1
        c += 1
    if impossible:
        max_impossible = i
        count_possibles = 0
    else:
        count_possibles += 1
        # After 6 possible consecutive solutions,
        # the equation is always solvable, since x + 5 + 1 = x + 6
        # Ex: x = 50, x + 5 = 55, x + 6 = x + 5 + 1 = 56
        if count_possibles == 6:
            break
print("Largest number of McNuggest that cannot be bought in exact quantity:",
      max_impossible)

