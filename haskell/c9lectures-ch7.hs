
{-

Haskell:
map :: (a -> b) -> [a] -> [b]
map _ [] = []
map f (x:xs) = f x : map f xs
-or-
map f xs = [ f x | x <- xs ]

"Any" language:
var ys = new List<T>();
for (var x in xs) {
  ys.Append(f(x));
}

C# LINQ:
var ys = from x in xs select f(x);

Python:
var ys = [ f(x) for x in xs ]

Haskell:
filter p xs = [x | x <- xs, p x]

LINQ:
var ys = from x in xs where p(x) select x;

C#:
ys = [ x for x in xs if p(x) ]

-}

-- Functions with the same pattern/scheme

fssum [] = 0
fssum (x:xs) = x + fssum xs

fsproduct [] = 1
fsproduct (x:xs) = x * fsproduct xs

fsor [] = False
fsor (x:xs) = x || fsor xs

fsand [] = True
fsand (x:xs) = x && fsand xs

fslength [] = 0
fslength (_:xs) = 1 + fslength xs

-- All use the "foldr" pattern

fssum2 = foldr (+) 0
fsproduct2 = foldr (*) 0
fsor2 = foldr (||) False
fsand2 = foldr (&&) True
fslenght2 = foldr (\_ n -> 1 + n) 0
fsreverse = foldr (\x xs -> xs ++ [x]) []

-- actually, even ++ is a fold 
-- 1) xs ++ ys = foldr (:) ys xs
-- 2) (++) ys xs = foldr (:) ys xs
-- 3) (++) ys = foldr (:) ys
-- 4) (++) = foldr (:)

fsfoldr :: (a -> b -> b) -> b -> [a] -> b
fsfoldr _ v [] = v 
fsfoldr f v (x:xs) = f x (fsfoldr f v xs)

{-

-- Interesting (although inneficient) implementation of length

length = sum . map (\_ -> 1)

length [1,2,3..n]
sum (map (\_ -> 1) [1,2,3..n])
sum [1,1,1..1]
n

-}

-- the same as . (as used in the length example)
compose :: (b -> c) -> (a -> b) -> (a -> c)
compose f g = \x -> f (g x)

-- Exercise 1
-- Function generators? Meta function? Closures? Lambdas?

-- Exercise 2
-- [f x | x <- xs, p x]
-- filter p (map f xs)

-- Exercise 3
-- fdmap f xs = foldr (\x ys -> f x : ys) [] xs
fdmap f xs = foldr g [] xs
    where g x ys = f x : ys

fdfilter p xs = foldr (\x ys -> if p x then x:ys else ys) [] xs

-- fdmap (*2) [1,2,3,4]
-- fdfilter (/=' ') "Felipo Soranz Programa Em Haskell"

-- Exercise 4
-- How many functions of the standard library can be defined as a fold

