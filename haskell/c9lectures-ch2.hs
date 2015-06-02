-- C#: var myList = new int[] {1,2,3,4,5};
-- Python: myList = [1,2,3,4,5]
myList = [1,2,3,4,5]

-- C#: var t1 = myList.First();
-- Python: t1 = myList[0]
t1 = head myList

-- C#: var t2 = myList.Skip(1);
-- Python: t2 = myList[1:]
t2 = tail myList

-- C#: var t3 = myList[2];
-- Python: t3 = myList[2]
t3 = myList !! 2

-- C#: var t4 = myList.Take(3);
-- Python: t4 = myList[:3]
t4 = take 3 myList

-- C#: var t5 = myList.Skip(3);
-- Python: t4 = myList[3:]
t5 = drop 3 myList

-- C#: myList.Skip(3).Union(myList.Take(3)) == myList (conceptually)
-- Python: myList == myList[:3] + myList[3:]
prove = take 3 myList ++ drop 3 myList == myList

-- C#: var t6 = myList.Length;
-- Python: t6 = len(myList)
t6 = length myList

-- C#: var t6 = myList.Sum();
-- Python: t6 = sum(myList)
t7 = sum myList

-- Creating syntatic sugar to "emulate" the order of "object method" instead of "method object"
-- |> is a new operator that taxes any object x and applies to it the function f
(|>) x f = f x

-- Looks more like myList.Skip(3) in C#
t8 = myList |>  drop 3

-- C#: var t9 = myList.Aggregate((a, b) => a * b);
-- Python 2: t9 = reduce(lambda a, b: a * b, myList)
-- Python 3: impor functools ; t9 = functools.reduce(lambda a, b: a * b, myList)
t9 = product myList

-- C#: var t10 = new int[] {1,2,3}.Union(new int[] {4,5});
-- Python: t10 = [1,2,3] + [4,5]
t10 = [1,2,3] ++ [4,5]

-- C#: var t10 = myList.
-- Python: t11 = reversed(myList) # in place would be: myList.reverse()
t11 = reverse myList

-- : is a list constructor operator. [] is the empty list
-- [1,2,3,4,5] is syntatic sugar for:
myOtherList = 1:2:3:4:5:[]

{-

Function calls in haskell vs other languages

Math:
f(a,b) + cd      f(x)    f(x,y)    f(g(x))    f(x, g(y))  f(x)g(y)

C#, Java, C, etc:
f(a,b) + c*d     f(x)    f(x,y)    f(g(x)     f(x, g(y))  f(x)*g(y)

Haskell:
f a b + c * d    f x     f x y     f (g x)    f x (g y)   f x * g y

-}

f x = x * 3
g x = x - 2
h x = f (g x)

-- Shortcuts...
-- f ( g 5 )  or  f $ g 5       ($ = right association?)
-- h = f . g                    (. = composition)

double x = x + x
quadruple x = double (double x) -- or quadruple = double . double

{- 
Compare:

// Actually, doesn't work 
static T myQuadruple <T> (T x) where T:INumerable<T>
{
    return myDouble(myDouble(x));
}

-}

factorial n = product [1..n]
average xs = sum xs `div` length xs


-- Curiosidade:
-- [1..n] == take n [1..]

-- Naming conventions:
--   functions start with lowercase, factorial, sumOfAllStuff
--   n   -> number
--   xs  -> Lists end with s
--   xss -> List of lists

a = b + c
  where
    b = 1
    c = 2
d = a * 2

-- Exercise 1 (done all above)

-- Exercise 2
n = a `div` length xs
  where
    a = 10
    xs = [1,
          2,3,4,
          5]

-- Exercise 3 and 4 - define last using other functions presented here
last1 = head . reverse 
last1' xs = head (reverse xs)

last2 xs = head $ drop (length xs - 1) xs

last3 (x:[]) = x
last3 (_:xs) = last3 xs

last4 (x:xs) = if xs == [] then x else last4 xs

-- Exercise 5 - Define init
init1 = reverse . tail . reverse
init1' xs = reverse (tail (reverse xs))

init2 xs = take (length xs - 1) xs

init3 (x:y:[]) = x:[]
init3 (x:xs) = x:init2 xs

init4 (x:xs) = if xs == [] then [] else x : init3 xs
