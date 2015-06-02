-- the countdown problem
import Data.List

data Op = Add | Sub | Mul | Div deriving (Show)

data Expr = Val Int | App Op Expr Expr deriving (Show)

-- Example: 1+2 is represeted as...
onePlusTwo = App Add (Val 1) (Val 2)

apply :: Op -> Int -> Int -> Int
apply Add x y = x + y
apply Sub x y = x - y
apply Mul x y = x * y
apply Div x y = x `div` y

-- Check below for method 3
-- valid :: Op -> Int -> Int -> Bool
-- valid Add _ _ = True
-- valid Sub x y = x > y
-- valid Mul _ _ = True
-- valid Div x y = x `mod` y == 0

eval :: Expr -> [Int] -- Could have been made with the Maybe syntax (try it as an exercise?)
eval (Val n)     = [ n | n > 0]
eval (App o l r) = [ apply o x y | x <- eval l
                                 , y <- eval r
                                 , valid o x y ]

choices :: Eq a => [a] -> [[a]]
--choices xs = nub . concat . map subsequences $ permutations xs
choices xs = concat (map permutations (subsequences xs))

values :: Expr -> [Int]
values (Val n) = [n]
values (App _ l r) = values l ++ values r

-- Just a specification, not really an efficient solution
solution0 :: Expr -> [Int] -> Int -> Bool
solution0 e ns n = elem (values e) (choices ns) && eval e == [n]

-- Method 1: Brute Force Solution

split :: [a] -> [([a], [a])]
split xs = [ (take i xs, drop i xs) | i <- [1..length xs - 1] ]
-- split [1,2,3,4] --> [([1],[2,3,4]),([1,2],[3,4]),...]

exprs :: [Int] -> [Expr]
exprs [] = []
exprs [n] = [Val n]
exprs ns = [ e | (ls,rs) <- split ns
               , l       <- exprs ls
               , r       <- exprs rs
               , e       <- combine l r ]

combine :: Expr -> Expr -> [Expr]
combine l r = [ App o l r | o <- [Add, Sub, Mul, Div] ]

solutions1 :: [Int] -> Int -> [Expr]
solutions1 ns n = [ e | ns' <- choices ns 
                      , e   <- exprs ns'
                      , eval e == [n] ]

-- Method 2: Fusing two functions

type Result = (Expr, Int)


results :: [Int] -> [Result]
-- Specification (the wanted behavior, not the actual implementation)
-- results ns = [ (e,n) | e <- exprs ns, n <- eval e ]

results [] = []
results [n] = [(Val n, n) | n > 0]
results ns = 
	[ res | (ls, rs) <- split ns
	      , lx       <- results ls
	      , ry       <- results rs
	      , res      <- combine' lx ry ]

combine' :: Result -> Result -> [Result]	      
combine' (l, x) (r, y) = 
	[ (App o l r, apply o x y) | o <- [Add, Sub, Mul, Div]
	                           , valid o x y ]

solutions2 :: [Int] -> Int -> [Expr]
solutions2 ns n = [ e | ns'    <- choices ns 
                      , (e, m) <- results ns'
                      , m == n ]

-- Method 3: Improving the valid function

-- Small improvements to eliminate duplication of commutative cases
-- for example 1+2 is the same as 2+1, so with small changes we can avoid evaluating this cases
valid :: Op -> Int -> Int -> Bool
valid Add x y = x <= y
valid Sub x y = x > y
valid Mul x y = x <= y && x /= 1 && y /= 1
valid Div x y = x `mod` y == 0 && y /= 1

--------

numbers :: [Int]
numbers = [1,3,7,10,25,50]
ops = ['+', '-', '*', '/']

answer :: Int
answer = 765

--main = putStrLn . show . head $ solutions1 numbers answer
main = putStrLn . show $ solutions2 numbers answer
