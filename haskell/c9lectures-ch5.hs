import GHC.Unicode

squares1 = [ x^2 | x <- [1..5] ]
{-                 |    \____/
                   |      |
        generated [T]  generator [T]

Math:
{ x^2 | x ? [1,5] }

C#:
var squares1 = from x in Enumerable.Range(1,5) select x*x;

Python:
squares1 = [x*x for x in range(1,6)]

-}

products1 = [ (x, y, x*y) | x <- [1..3], y <- [4..5] ]
{-                          \_________/  \_________/
                                 |            |
                               slower       faster
C#:
var products1 = 
    from x in Enumerable.Range(1,3) 
        from y in Enumerable.Range(4,5) 
        select new {a = x, b = y, c = x*y};

Python:
products1 = [ (a, b, a*b) for a in range(1,4) for b in range(4, 6) ]

-}

products2 = [ (x, y, x*y) | x <- [1..3], y <- [x..5] ]
--                                             \ correlated!

fsconcat :: [[a]] -> [a]
fsconcat xss = [ x | xs <- xss, x <- xs ]

evens = [ x | x <- [1..10], even x ]
{-                          \____/
                              |
                          predicates/guards
C#:
var evens = 
    from x in Enumerable.Range(1,10) 
    where x % 2 == 0
    select x;	

Python:
evens = [ x for x in range(1,11) if x % 2 == 0 ]
-}

factors n = [ f | f <- [1..n], n `mod` f == 0 ]

isPrime :: Integer -> Bool
isPrime n = factors n == [1,n]

-- A very brute-force way to generate primes! Not very efficient, just an example
primes :: Integer -> [Integer]
primes n = [x | x <- [2..n], isPrime x]

fszip :: [a] -> [b] -> [(a,b)]
fszip _ [] = []
fszip [] _ = []
fszip (x:xs) (y:ys) = (x,y):fszip xs ys

fspairs :: [a] -> [(a,a)]
fspairs xs = fszip xs (tail xs)

isSorted xs = and [ x <= y | (x, y) <- fspairs xs ]

-- A function to check the positions where x occurs in a list
positions :: Eq a => a -> [a] -> [Integer]
positions x xs = [ i | (x', i) <- zip xs [0..], x == x' ]
{-                     \_____________________/
                                 |
                   a clever way to index elements

An "aproximate" version in C# for ===> positions 0 [1,0,0,1,0,1,1,0]

var xs = new int[] {1,0,0,1,0,1,1,0};
var positions = 
    from i in Enumerable.Range(0, xs.Length)
    where xs[i] == 0
    select i;
-}



uhello = [ toUpper c | c <- "hello" ]
lowers xs = length [ x | x <- xs, isLower x ]

{-

C#:
var uhello = new string((from c in "hello" select Char.ToUpper(c)).ToArray<char>());

var lowers = (from x in "Haskell" where Char.IsLower(x) select 1).Count();

-}


-- Exercise 1

pyths :: Int -> [(Int, Int, Int)]
pyths n = [(x, y, z) | x <- [1..n], y <- [1..n], z <- [1..n], x*x + y*y == z*z]

pythsTest = pyths 5 == [(3,4,5), (4,3,5)]

-- Exercise 2

perfects :: Int -> [Int]
perfects n = [x | x <- [1..n], x == sum (factors x) - x]

perfectsTest = perfects 500 == [6,28,496]

-- Exercise 3

scalarProduct :: Num a => [a] -> [a] -> a
scalarProduct _ [] = 0
scalarProduct [] _ = 0
scalarProduct xs ys = sum [ x * y | (x, y) <- zip xs ys ]
--scalarProduct (x:xs) (y:ys) = x*y + scalarProduct xs ys

