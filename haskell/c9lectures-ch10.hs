type FsString = [Char]

name1 :: FsString
name1 = "Felipo"

name2 :: FsString
name2 = ['S', 'o', 'r', 'a', 'n', 'z']

type Pair a = (a, a)

mult :: Pair Int -> Int
mult (x, y) = x*y

data FsBool = FsFalse | FsTrue

b1 = FsFalse
b2 = FsTrue

data Answer = Yes | No | Unknown

answers :: [Answer]
answers = [Yes, No, Unknown]

ansFlip :: Answer -> Answer
ansFlip Yes = No
ansFlip No = Yes
ansFlip Unknown = Unknown

data Shape = Circle Float | Rect Float Float

square :: Float -> Shape
square n = Rect n n

area :: Shape -> Float
area (Circle r) = pi * r^2
area (Rect x y) = x * y

-- data Maybe a = Nothing | Just a

safediv :: Int -> Int -> Maybe Int
safediv _ 0 = Nothing
safediv m n = Just (m `div` n)

safehead :: [a] -> Maybe a
safehead [] = Nothing
safehead xs = Just (head xs)

data Nat = Zero | Succ Nat

zero = Zero
one = Succ Zero
two = Succ (Succ Zero)
three = Succ (Succ (Succ Zero))
infinite = Succ infinite

nat2int :: Nat -> Int
nat2int Zero = 0
nat2int (Succ n) = 1 + nat2int n

int2nat :: Int -> Nat
int2nat 0 = Zero
int2nat n = Succ (int2nat (n-1))

add' :: Nat -> Nat -> Nat
add' m n = int2nat (nat2int m + nat2int n)

add :: Nat -> Nat -> Nat
add Zero n = n
add (Succ m) n = Succ (add m n)

{- Compare with:
(++) [] ys = ys
(++) (x:xs) ys = x:(++) xs ys
-}

data Expr = Val Int
          | Add Expr Expr
          | Mul Expr Expr

expr1 = Add (Val 1) (Val 2)
expr2 = Mul (Val 3) (Val 4)
expr3 = Add (Val 1) (Mul (Val 2) (Val 3))
expr4 = Add (Mul (Val 5) (Val 10)) (Mul (Val 3) (Val 7))

size :: Expr -> Int
size (Val n) = 1
size (Add x y) = size x + size y
size (Mul x y) = size x + size y

eval :: Expr -> Int
eval (Val n) = n
eval (Add x y) = eval x + eval y
eval (Mul x y) = eval x * eval y

data Tree = Leaf Int | Node Tree Int Tree

tree1 = Node (Node (Leaf 1) 
                   3 
                   (Leaf 4)) 
             5 
             (Node (Leaf 6) 
                   7 
                   (Leaf 9))

inTree :: Int -> Tree -> Bool
inTree m (Leaf n) = m==n
inTree m (Node l n r) = m == n || inTree m l || inTree m r
-- if element is not in the tree, the whole tree must be traversed (check binary below)

flatten :: Tree -> [Int]
flatten (Leaf n) = [n]
flatten (Node l n r) = flatten l ++ [n] ++ flatten r

-- if tree is ordered, binary search can be used
binaryInTree :: Int -> Tree -> Bool
binaryInTree m (Leaf n) = m == n
binaryInTree m (Node l n r) -- can be redefined with 'case compare m n of' for efficiency
    | m == n = True
    | m < n = binaryInTree m l
    | m > n = binaryInTree m r

-- Exercise 1

mul :: Nat -> Nat -> Nat
mul Zero _ = Zero
mul (Succ Zero) n = n -- redundant?
mul (Succ m) n = add n (mul m n)

-- Exercise 2

{-
fold :: Expr -> Int
fold expr base addOp mulOp = foldH expr
    where
        foldH (Val n) = base
        foldH (Add x y) = foldH x `addOp` foldH y
        foldH (Mul x y) = foldH x `mulOp` foldH y

size' expr = fold expr 1 (+) (+)
eval' expr = fold expr  (+) (*)  -- base ???
-}

-- Exercise 3

tree2 = Node (Node (Leaf 1) 
                   3 
                   (Leaf 4)) 
             5 
             (Leaf 6)

completeTree :: Tree -> Bool
completeTree (Leaf n) = True
completeTree (Node l n r) = sizeTree l == sizeTree r
    where sizeTree (Leaf n) = 1
          sizeTree (Node l n r) = sizeTree l + sizeTree r

--

main = return ()
