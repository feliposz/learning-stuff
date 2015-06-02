-- Some bult-in types: Bool, Char, String, Int, Integer, Float, Double, Fractional

-- C#: int x = 10;
x0 :: Int
x0 = 10

-- C#: int[] xs1 = new [] { 1, 2, 3, 4, 5 };
xs1 :: [Int]
xs1 = [1,2,3,4,5]

-- C#: int[][] xss2 = new [][] { new [] {1, 2, 3}, new [] {4, 5} };
xss2 :: [[Int]]
xss2 = [[1,2,3],[4,5]]

-- C# 4.0: var t1 = Tuple.Create(True, False);
t1 :: (Bool, Bool)
t1 = (True, False)

-- C# 4.0: var t2 = Tuple.Create(1, false, 'x');
t2 :: (Int, Bool, Char)
t2 = (1, False, 'x')

{-
Haskell ----> C#
fst t1  ----> t1.Item1
snd t1  ----> t1.Item2

-}


-- Lambda
-- C#: Func<int, int, int> add = (a, b) => a + b;	
add :: Int -> Int -> Int
add = \a b -> a + b

add2 :: Int -> (Int -> Int)
add2 a b = a + b

add3 x = \y -> x + y

add4 = \x -> \y -> x + y

-- Currying
-- C#: Func<int, Func<int, Func<int,int>>>
-- Equivalent to mult :: Int -> (Int -> (Int -> Int))   ----> Associates to the RIGHT
mult :: Int -> Int -> Int -> Int
mult x y z = x * y * z

-- Equivalent to (((mult 3) 2) 1)   ----> Associates to the LEFT 
multX = mult 3 2 1 

-- C#: static int myLength<T> (T[] xs) { return xs.Length; }
myLength :: [a] -> Int
myLength xs = length xs

{-
zip :: [a] [b] -> [(a,b)]

C#:
IEnumerable<R> zip<T,S,R> (IEnumerable<T> xs, IEnumerable<T> ys, Func<T,S,R> f)
-}


-- Exercise 1

{-

[Char]
(Char,Char,Char)
[(Bool,Char)]
([Bool],[Char])
[[a]->[a]]

-}

-- Exercise 2

second xs = head (tail xs)
swap (x, y) = (y, x)
pair x y = (x, y)
double x = x*2
palindrome xs = reverse xs == xs
twice f x = f (f x) -- twice :: (a -> a) -> a -> a

