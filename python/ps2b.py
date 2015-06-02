# Problem set 2 - Problem 4
# Author: Felipo Soranz
# Time: ~25 minutes

###
### template of code for Problem 4 of Problem Set 2, Fall 2008
###

bestSoFar = 0     # variable that keeps track of largest number
                  # of McNuggets that cannot be bought in exact quantity
packages = (6,9,20)   # variable that contains package sizes

count_possibles = 0
for n in range(1, 200):   # only search for solutions up to size 200
    ## complete code here to find largest size that cannot be bought
    ## when done, your answer should be bound to bestSoFar
    impossible = True
    c = 0
    while c * packages[2] <= n and impossible:
        b = 0
        while b * packages[1] <= n and impossible:
            a = 0
            while a * packages[0] <= n and impossible:
                total = packages[0] * a + packages[1] * b + packages[2] * c
                if total == n:
                    impossible = False
                a += 1
            b += 1
        c += 1
    if impossible:
        bestSoFar = n

print(("Given packages sizes %d, %d and %d, the largest number of McNuggets "
       "that cannot be bough in exact quantity is:") % packages, bestSoFar)

