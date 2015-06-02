
-- VB? Function myAbs (n as Int) as Int
myAbs :: (Num a, Ord a) => a -> a
myAbs x = if x >= 0 then x else (-x);

signum :: Int -> Int
signum n = if n < 0 then -1 else
             if n > 0 then 1 else 0

--       Guards        Evaluation
myAbs2 n | n >= 0    = n
         | otherwise = (-n)

signum2 n | n < 0     = -1
          | n == 0    = 0
          | otherwise = 1

not' :: Bool -> Bool
not' False = True
not' True  = False

{-
Similarly in C#:

abstract class Booly { public abstract  Booly Not(); }
class Truey : Booly { public override Booly Not() { return new Falsey(); } }
class Falsey : Booly { public override Booly Not() { return new Truey(); } }
-}

-- Non purist
and1 :: Bool -> Bool -> Bool
and1 True True = True
and1 True False = False
and1 False True = False
and1 False False = False

-- "Purist"
and2 :: Bool -> (Bool -> Bool)
and2 True x | x == True = True
            | x == False = False
and2 False _ = False

-- Using _
and3 :: Bool -> Bool -> Bool
and3 True True = True
and3 _ _ = False

-- Using _
and4 :: Bool -> Bool -> Bool
and4 True b = b
and4 False _ = False

someList = 1:2:3:4:5:6:7:[]


head' :: [a] -> a
head' (x:_) = x

tail' :: [a] -> [a]
tail' (_:xs) = xs

some' :: [a] -> [a]
some' (x:y:xs) = x:some' xs
some' (x:xs) = x:[]
some' [] = []

foo x = 4711

-- This is actually syntatic sugar for...
add1 x y = x + y
-- This! (That's why currying works!)
add2 = \x -> (\y -> x + y)

odds n = map (\x -> x*2 + 1) [0..n-1]

{-

Sections:

1 + 2
(+) 1 2

(1+)
\x -> 1 + x

-}

-- Some useful functions writen as sections
successor = (1+)
inverse = (1/)
double = (*2)
half = (/2)

-- Exercises

-- 1) Define safetail...

-- Conditional
safetailC :: [a] -> [a]
safetailC xs = if null xs then [] else tail xs

-- Guards
safetailG :: [a] -> [a]
safetailG xs
    | null xs = []
    | otherwise = tail xs

-- Pattern Matching
safetailP :: [a] -> [a]
safetailP (x:xs) = xs
safetailP [] = []

-- 2) Or definition

or1 :: Bool -> Bool -> Bool
or1 True True = True
or1 True False = True
or1 False True = True
or1 False False = False

or2 :: Bool -> Bool -> Bool
or2 False False = False
or2 _ _ = True

or3 :: Bool -> Bool -> Bool
or3 False b | b == False = False
            | otherwise  = True
or3 True _ = True

or4 :: Bool -> Bool -> Bool
or4 False b = b
or4 True _ = True

or5 a b = if a then a else b

testTableOr =
  [(True,  True,  True), 
   (True,  False, True), 
   (False, True,  True), 
   (False, False, False)]

testOr f = map (\(a, b, c) -> f a b == c) testTableOr

-- 3/4) Redefine &&

and5 a b = if a then b else a
and6 a b = if a == True then if b == True then True else False else False
and7 a b = if a then b else False

testTableAnd = 
  [(True,  True,  True), 
   (True,  False, False), 
   (False, True,  False), 
   (False, False, False)]

testAnd f = map (\(a, b, c) -> f a b == c) testTableAnd

not2 a = if a then False else True
