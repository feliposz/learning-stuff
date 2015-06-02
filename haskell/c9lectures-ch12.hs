inc :: Int -> Int
inc n = n + 1

{-
strict evaluation:
inc (2*3)
inc 6
6+1
7

lazy evaluation:
inc (2*3)
(2*3)+1
6+1
7
-}

mult = \x -> \y -> x * y

inf = 1 + inf

{-
Strict:
fst (0, inf)
fst (0, 1+inf)
fst (0, 1+(1+inf))
fst (0, 1+(1+(1+inf)))
... (never terminates)

Lazy:
fst (0, inf)
0
-}

ones = 1:ones

-- head ones
-- head (tail ones)
-- take 3 ones

-- WARNING!!!

-- filter (<=5) [1..] --> Never returns
-- takeWhile (<=5) [1..] --> [1,2,3,4,5]

prime = sieve [2..]

sieve (p:xs) = p : sieve (filter (\x -> x `mod` p /= 0) xs) 
--[ x | x <- xs, x `mod` p /= 0 ]

-- Lazy (the accumulator variable v doesn't work as intended because of lazy evaluation)
sumWith :: Int -> [Int] -> Int
sumWith v [] = v
sumWith v (x:xs) = sumWith (v+x) xs

-- sumWith 0 [1,2,3]
-- (((0+1)+2)+3)

-- Strict (force the evaluation of (v+x) with $! before it)
sumWith' :: Int -> [Int] -> Int
sumWith' v [] = v
sumWith' v (x:xs) = (sumWith' $! (v+x)) xs
