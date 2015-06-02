##f [] = []
##f (x:xs) = f ys ++ [x] ++ f zs
##    where
##      ys = [a | a <- xs, a <= x]
##      zs = [b | b <- xs, b > x]

# weird functional quicksort, conceptually simple to understand but kind of inefficient
# actual qsort is implemented with inplace sorting
def weirdQsort(xl):
    if len(xl) == 0:
        return xl
    x = xl[0]
    xs = xl[1:]
    ys = [a for a in xs if a <= x]
    zs = [b for b in xs if b > x]
    return weirdQsort(ys) + [x] + weirdQsort(zs)

weirdQsort([10, 2, 23, 223, 43, 2, 23, 11, 9, -1, 0, 12])
