-- :l C:\Local\Dev\haskell\learn_chapter6.hs

-- Higher Order Function
-- É uma função que pode receber como parâmetro uma outra função ou cujo retorno é uma outra função.

-- Curried function
-- Ao "chamar" uma função com menos parêmetros, uma nova função é criada com os parâmetros passados já "preenchidos" e os parâmetros restantes são os parâmetros da nova função.

{-

1) max 4 5
2) (max 4) 5
3) f = max 4; f 5

-}

multThree :: (Num a) => a -> a -> a -> a
-- or also: a -> (a -> (a -> a))
multThree x y z = x * y * z

{-

multThree 3 5 9
or also: ((multThree 3) 5) 9

-}

multTwoWithNine = multThree 9
multWithEighteen = multTwoWithNine 2

compareWithHundred :: (Num a, Ord a) => a -> Ordering
compareWithHundred x = compare 100 x

compareWithHundred2 :: (Num a, Ord a) => a -> Ordering
compareWithHundred2 = compare 100

-- Section

subtractTen = subtract 10 -- Since (-10) is not what you want, also: flip (-) 10
subtractTen' = flip (-) 10
subtractFromTen = (10-)
divideByTen = (/10)
tenDividedBy = (10/)
plusTen = (10+)
timesTen = (10*)
shout = (++ "!!!")
polite = ("Would you kindly " ++)

isUpperAlphanum = (`elem` ['A'..'Z'])
-- Something like: isUpperAlphanum x = elem x ['A'..'Z']

-- Functions as parameters

applyTwice :: (a -> a) -> a -> a -- In this case parenthesis are mandatory because -> is naturally right associative
applyTwice f x = f (f x)

-- applyTwice (*2) 10  ----> 40
-- applyTwice (3:) [1] ----> [3,3,1] (Weird stuff!)

fszipWith :: (a -> b -> c) -> [a] -> [b] -> [c]
fszipWith _ [] _ = []
fszipWith _ _ [] = []
fszipWith f (x:xs) (y:ys) = f x y : fszipWith f xs ys

-- fszipWith (+) [1,2,3,4] [5,6,7,8]
-- fszipWith (fszipWith (*)) [[1,2,3],[3,5,6],[2,3,4]] [[3,2,2],[3,4,5],[5,4,3]]

-- With this, zip could be defined as:
fszip2 = fszipWith (\x y -> (x, y))

fsflip :: (a -> b -> c) -> (b -> a -> c)
fsflip f = g
    where g x y = f y x

-- Or: fsflip f = \x y -> f y x
-- Or even: fsflip f x y = f y x

-- flip zip [1,2,3,4,5] "hello"  ----> zip "hello" [1,2,3,4,5]

fsmap :: (a -> b) -> [a] -> [b]
fsmap _ [] = []
fsmap f (x:xs) = f x : fsmap f xs

-- fsmap (*2) [1..5]
-- fsmap (\x -> replicate x x) [1..5]

-- Apply to a list of lists:
-- fsmap (fsmap (^2)) [[1..5],[6..10],[11..15]]

-- map is really a kind of list comprehension, but it can be more readable in some contexts:
-- map (*2) [1..5]
-- [ x*2 | x <- [1..5] ]

fsfilter :: (a -> Bool) -> [a] -> [a]
fsfilter _ [] = []
fsfilter p (x:xs)
    | p x = x:filter p xs
    | otherwise = filter p xs

-- filter is also a kind of list comprehension:
-- filter (>3) [3,7,8,1,2,0]
-- [ x | x <- [3,7,8,1,2,0], x > 3]

fsqsort :: Ord a => [a] -> [a]
fsqsort [] = []
fsqsort (x:xs) = fsqsort (filter (<=x) xs) ++ [x] ++ fsqsort (filter (>x) xs)

{-

For our next problem, we'll be dealing with Collatz sequences. We take a natural number. If that number is even, we divide it by two. If it's odd, we multiply it by 3 and then add 1 to that. We take the resulting number and apply the same thing to it, which produces a new number and so on. In essence, we get a chain of numbers. It is thought that for all starting numbers, the chains finish at the number 1. So if we take the starting number 13, we get this sequence: 13, 40, 20, 10, 5, 16, 8, 4, 2, 1. 13*3 + 1 equals 40. 40 divided by 2 is 20, etc. We see that the chain has 10 terms.

Now what we want to know is this: for all starting numbers between 1 and 100, how many chains have a length greater than 15? First off, we'll write a function that produces a chain:

-}

chain :: (Eq a, Integral a) => a -> [a]
chain 1 = [1]
chain n
  | even n = n : chain (n `div` 2)
  | odd  n = n : chain (n * 3 + 1)

chainTest = length (filter (>15) (map (length . chain) [1..100]))
numLongChains = length (filter isLong (map chain [1..100]))
    where isLong xs = length xs > 15

-- Only folds and horses

sum1 xs = foldl (\acc x -> acc + x) 0 xs
--or--
sum2 = foldl (+) 0


elem' :: (Eq a) => a -> [a] -> Bool
elem' y ys = foldl (\acc x -> if x == y then True else acc) False ys
--                   ^
--                   |
-- foldl - acc is on the left


map1 f xs = foldr (\x acc -> f x : acc) [] xs
--                    ^
--                    |
-- foldr - acc is on the right

-- foldr: usually better when building a new list from a list. an inefficient version of map
map2 f xs = foldl (\acc x -> acc ++ [f x]) [] xs

-- 'foldr f acc xs' is like 'foldl f acc (reverse xs) --and--
-- 'foldl f acc xs' is like 'foldr f acc (reverse xs)

{-
-- Visualizing how foldl and foldr are applied:

foldr f 0 [3,4,5,6] = f 3 (f 4 (f 5 (f 6 0)))
foldl g 0 [3,4,5,6] = g (g (g (g 0 3) 4) 5) 6

foldr (-) 0 [3,4,5,6] = 3-(4-(5-(6-0))) = -2
foldl (-) 0 [3,4,5,6] = (((0-3)-4)-5)-6 = -18

scanl/scanr operam como foldl/foldr mas trazendo os resultados intermediários como uma lista. (assim como scanl1/scanr1)

-}

sqrtSums = length (takeWhile (<1000) (scanl1 (+) (map sqrt [1..]))) + 1

op10 = map ($ 10) [(1+), (1-), (subtract 1), (2*), (1/), (/2)]


-- Composition

-- parenthesis
oddSquareSum1 :: Integer  
oddSquareSum1 = sum (takeWhile (<10000) (filter odd (map (^2) [1..])))     

-- composition
oddSquareSum2 :: Integer  
oddSquareSum2 = sum . takeWhile (<10000) . filter odd . map (^2) $ [1..]  

-- sometimes, it's clearer to write like this:
oddSquareSum3 :: Integer  
oddSquareSum3 =   
    let oddSquares = filter odd $ map (^2) [1..]  
        belowLimit = takeWhile (<10000) oddSquares  
    in  sum belowLimit  
