-- ================================================================================================
-- http://elemarjr.net/2012/06/09/haskell-para-programadores-c-parte-1-primeiros-passos/

-- H:
sum [1..100]
-- C#:
Enumerable.Range(1, 100).Sum()

-- H:
[1..10]
-- C#:
Enumberable.Range(1, 100)

-- H:
[1,2,3,4,5]
-- C#:
new[] {1,2,3,4,5}

-- H:
head [1,2,3,4,5]
-- C#:
new[] {1, 2, 3, 4, 5}.First()

-- H:
tail [1,2,3,4,5]
-- C#:
new[] {1, 2, 3, 4, 5}.Skip(1)

-- H:
drop 3 [1,2,3,4,5]
-- C#:
new[] {1, 2, 3, 4, 5}.Skip(3)

-- H:
length [1,2,3,4,5]
-- C#:
new[] {1, 2, 3, 4, 5}.Count()

-- H:
sum [1,2,3,4,5]
-- C#:
new[] {1, 2, 3, 4, 5}.Sum()

-- H:
product [1,2,3,4,5] -- or -- foldl' (\a b -> a * b) 1 [1,2,3,4,5] (foldl' is tail optimized, so in this case works for arbitrarily large lists)
-- C#:
new[] {1, 2, 3, 4, 5}.Aggregate((a, b) => a*b)

-- H:
[1,2,3] ++ [4,5] 
-- C#:
new[] {1,2,3}.Union(new[] {4,5});

-- H:
reverse [1,2,3,4,5]
-- C#:
{1,2,3,4,5}.Reverse()

-- ================================================================================================
-- http://elemarjr.net/2012/06/10/haskell-para-programadores-c-parte-2-aplicao-e-escrita-de-funes/

-- H:
foo x
C#  foo(x)

-- H:
foo a b
-- C#:
foo(a,b)

-- H:
foo (fee x) -- or -- foo $ fee x
-- C#:
foo(fee(x))

-- H:
foo a (fee b)
-- C#:
foo(a,fee(b))

-- H:
add a b = a + b
-- C#:
Func<int, int, int> add = (a, b) => a + b;

-- H:
inc a = add a 1
-- C#:
Func<int, int> inc = a => add(a, 1);

-- H:
double x = x + x
-- C#:
Func<int, int> @double = x => x + x;

-- H:
quadruple x = double (double x)
-- C#:
Func<int, int> quadruple = x => @double(@double(x));

-- ================================================================================================
-- http://elemarjr.net/2012/06/12/haskell-para-programadores-c-parte-3-tipos-tipos-tipos-e-classes/

-- H:
5 :: Int
-- C#:
(int) 5

-- H:
"Elemar" :: String
-- C#:
(string) "Elemar"

-- H:
True :: Bool
-- C#:
(bool) true

-- H:
:type <expr> -- or -- :t <expr>
-- C#:
<expr>.GetType()

-- H:
:type 5
-- C#:
5.GetType()

-- H:
[1,2,3,4,5] :: [Int]
-- C#:
new int[] {1,2,3,4,5}

-- H:
[[1,2,3],[4,5]] :: [[Int]]
-- C#:
new int[][] {new int[] {1,2,3}, new int[] {4,5}}

-- H:
(1, "Elemar Jr", True) -- type (Int, String, Bool)
-- C#:
Tuple.Create(1, "Elemar Jr", true) -- type Tuple<int, string, bool>

-- H:

inc :: Int -> Int
inc n = n + 1 -- or -- inc = \n -> n + 1

add :: Int -> Int -> Int
add a b = a + b -- or -- add = \a -> \b -> a + b

-- C#:

Func<int, int> inc = n => n + 1;
Func<int, Func<int, int>> add = a => b => a + b;

-- H:
--(0)   (1)   (2)
last :: [a] -> a
last = \vs -> head (reverse vs)
--     (3)    \_____(4)______/

-- C#:
--           (2) (0)              (1)        (3)
public static T Last<T>(this IEnumerable<T> source)
{
	return source.Reverse().First();
} --       \_________(4)__________/

-- ================================================================================================
-- http://elemarjr.net/2012/06/13/haskell-para-programadores-c-parte-4-respostas-condicionais/

-- H

isDigit :: Char -> Bool
isDigit c = c >= '0' && c <= '9'

-- C#
public static bool IsDigit(char c)
{
    return c >= '0' && c <= '9';
}

-- H

abs :: Int -> Int
abs n = if n < 0 then -n else n
--or--
abs n
    | n < 0 = -n
    | otherwise = n

-- C#

public static int Abs(int n)
{
	return n < 0 ? -n : n;
}


-- H

sign :: Int -> Int
sign n = if n < 0 then -1 else if (n > 0) then 1 else 0

-- or --

sign n
    | n < 0 = -1
    | n > 0 = 1
    | otherwise = 0
	
-- C#

public static int Sign(int input)
{
    if (input < 0) return -1;
	if (input > 0) return 1;
    return 0;
}

-- H

not :: Bool -> Bool
not a = if a then False else True

not a
    | a == True = False
    | otherwise = True
	
-- C#

not True = False
not False = True

-- C# 
public static bool And(bool a, bool b)
{
    if (a) return b;
    return false;
}

-- H
and :: Bool -> Bool -> Bool
and True True = True
and True False = False
and False True = True
and False False = False

and True True = True
and _ _ = False

and True b = b
and False _ = False

and a b
    | a == b = a
    | otherwise = False

True `and` True = True
_ `and` _ = False

-- ================================================================================================
-- http://elemarjr.net/2012/06/14/haskell-para-programadores-c-parte-5-list-comprehensions/

-- H
[1,2,3,4,5] -- or -- 1 : (2 : (3 : (4 : (5 : []))))


-- C#
public static IEnumerable<int> OneToFive()
{
    yield return 1;
    yield return 2;
    yield return 3;
    yield return 4;
    yield return 5;
}

-- H
[1..5]

-- C#
Enumerable.Range(1,5)

-- H
[1..]
take 5 [1..]

-- C#
public static IEnumerable<int> Infinite()
{
    var result = 1;
	while (true)
		yield return result++;
}

Infinite().Take(5);

-- H
[x^2 | x <- [1..5]]

-- C#
Enumerable.Range(1,5).Select(x => x * x)
--or--
from x in Enumerable.Range(1,5) select x * x

-- H
[(x, y) | x <- [1..3], y <- [4..5]]

-- C#
from x Enumerable.Range(1,3)
from y Enumerable.Range(4,5)
select new {x,y}
--or--
Enumerable.Range(1,3).SelectMany(x => Enumerable.Range(4,5), (x, y) = new {x,y})
--or--
public static IEnumerable<Tuple<int, int>> Pairs()
{
    for (var x = 1; x <= 3; x++)
        for (var y = 1; y <= 3; y++)
            yield return new Tuple<int, int>(x, y);
}


-- H
[x | x <- [1..50], even x]
--or--
[x | x <- [1..50], x `mod` 2 == 0]

-- C#
Enumerable.Range(1, 50).Where(x => x%2 == 0)
--or--
from x in Enumerate.Range(1,50)
where x % 2 == 0
select x

-- H
factors n = [x | x <- [1..n], n `mod` x == 0]
isPrime n = (factors n) == [1, n]
primesUntil n = [x | x <- [1..n], isPrime x]

-- C#
public static IEnumerable<int> Factors(int n)
{
    return from x in Enumerable.Range(1, n)
            where n%x == 0
            select x;
}

public static bool IsPrime(int n)
{
    return Factors(n).SequenceEqual(new [] {1, n});
}

public static IEnumerable<int> PrimesUntil(int n)
{
    return from x in Enumerable.Range(1, n)
            where IsPrime(x)
            select x;
}

-- H

isLower c = c >= 'a' && c <= 'z'
lowerCount s = sum [ 1 | c <- s, isLower c]

-- C#

(from c in s where Char.IsLower(c) select 1).Count()

-- ================================================================================================
-- http://elemarjr.net/2012/06/15/haskell-para-programadores-c-parte-6-recurso/

factorial' :: Int -> Int
factorial' 0 = 1
factorial' (n+1) = (n+1) * factorial' n

public static int Factorial(int n)
{
    if (n == 0) return 1;
    return Factorial(n - 1)*Factorial(n);
}         

product' :: Num a => [a] -> a
product' [] = 1
product' (n:ns) = n * product' ns

public static int Product(this IEnumerable source)
{
    if (!source.GetEnumerator().MoveNext()) return 1;
    return source.First() + source.Skip(1).Product();
}

length' :: [a] -> Int
length' [] = 0
length' (_:xs) = 1 + length' xs

public static int Length(this IEnumerable source)
{
    if (!source.GetEnumerator().MoveNext()) return 0;
    return 1 + source.Skip(1).Length();
}

reverse' :: [a] -> [a]
reverse' [] = []
reverse' (x:xs) = reverse' xs ++ [x]

public static IEnumerable Reverse(this IEnumerable source)
{
    if (!source.GetEnumerator().MoveNext()) 
        return Enumerable.Empty();
 
    return source.Skip(1).Reverse().Union(source.Take(1));
}

-- ================================================================================================
-- http://elemarjr.net/2012/06/16/haskell-para-programadores-c-parte-7-higher-order-functions/

twice :: (a -> a) -> a -> a
twice f x = f (f x)

static T Twice<T>(Func<T, T> f, T x)
{
    return f(f(x));
}

-- map => Select

map (\a -> a + 1) [1..5]

Enumerable.Range(1, 5).Select(a => a + 1);

length' :: [a] -> Int
length' xs = sum (map (\_ -> 1) xs)

static int Length<T>(IEnumerable<T> source)
{
    return source.Select(a => 1).Sum();
}

-- filter => Where

filter :: (a -> Bool) -> [a] -> a 
filter p xs = [x | x <- xs, p x]

Enumerable.Range(1, 10).Where(x => x%2 == 0);
Enumerable.Range(1, 10).Where(x => x > 5);
new string("String With Spaces".Where(c => c != ' ').ToArray());

-- foldr => Aggregate

product' :: Num a => [a] -> a
product'= foldr (*) 1
 
sum' :: Num a => [a] -> a
sum'= foldr (+) 0
 
and' :: [Bool] -> Bool
and'= foldr (&&) True
 
or' :: [Bool] -> Bool
or'= foldr (||) False

Func<IEnumerable<int>, int> sum = xs => xs.Aggregate(0, (a, b) => a + b);
Func<IEnumerable<int>, int> product = xs => xs.Aggregate(1, (a, b) => a * b);
Func<IEnumerable<bool>, bool> and = xs => xs.Aggregate(true, (a, b) => a && b);
Func<IEnumerable<bool>, bool> or = xs => xs.Aggregate(false, (a, b) => a || b);

-- all, any, takeWhile, dropWhile => All, Any, TakeWhile, SkipWhile

-- ================================================================================================
-- http://elemarjr.net/2012/06/18/haskell-para-programadores-c-parte-8-composition/

combine (a->b) -> (c->a) -> (c->b)
combine fa fb = \arg -> fa (fb arg)
-- or --
-- Just use the . operator

not x = if x then False else True
even x = x `mod` 2 == 0
odd = combine not even --or-- odd = not . even

public static Func<TC, TB> Combine<TA, TB, TC>(this Func<TA, TB> fa, Func<TC, TA> fb)
{
	return arg => fa(fb(arg));
}

Func<bool, bool> not = a => !a;
Func<int, bool> even = x => x % 2 == 0;
var odd = Combine(not, even);

-- ================================================================================================
-- http://elemarjr.net/2012/06/30/haskell-para-programadores-c-parte-9-modules/

module Geometry
    (
    cubeVolume,
    cubeArea,
    cuboidVolume,
    cuboidArea
    )
    where
        cubeVolume :: Float -> Float
        cubeVolume side = cuboidVolume side side side
         
        cubeArea :: Float -> Float
        cubeArea side = cuboidArea side side side
 
        cuboidVolume :: Float -> Float -> Float -> Float
        cuboidVolume a b c = rectArea a b * c
 
        cuboidArea :: Float -> Float -> Float -> Float
        cuboidArea a b c = rectArea a b * 2 + rectArea a c * 2 + rectArea c b * 2
 
        rectArea :: Float -> Float -> Float
        rectArea a b = a * b


		
public static class Geometry
{
    public static float CubeVolume(float side)
    {
        return CuboidVolume(side, side, side);
    }
 
    public static float CubeArea(float side)
    {
        return CuboidArea(side, side, side);
    }
 
    public static float CuboidVolume(float a, float b, float c)
    {
        return RectArea(a, b)*c;
    }
 
    public static float CuboidArea(float a, float b, float c)
    {
        return RectArea(a, b)*2 + RectArea(a, c)*2 + RectArea(c, b)*2;
    }
 
    private static float RectArea(float a, float b)
    {
        return a*b;
    }
}
		