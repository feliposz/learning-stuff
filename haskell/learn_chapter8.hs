-- :l C:\Local\Dev\haskell\learn_chapter8.hs

-- === Algebraic data types ===

-- C:\Local\Dev\haskell\Shape.hs
import Shape
import qualified Data.Map as Map
import Data.List

c1 = Circle (Point 1 1) 10
c2 = Circle (Point 2 3) 3
r1 = Rectangle (Point 0 0) (Point 2 2)
r2 = Rectangle (Point 1 1) (Point 11 21)

shapes = [c1, c2, r1, r2]

c3 = nudge c1 5 15
r3 = nudge r1 5 15

-- === Record syntax ===

data Person = Person { firstName :: String
                     , lastName :: String
                     , age :: Int
                     , height :: Float
                     , phoneNumber :: String
                     , flavor :: String
                     } deriving (Show)

f = Person "Fulano" "de Tal" 33 1.84 "234-5678" "KitKat"

data Car = Car {company :: String, model :: String, year :: Int} deriving (Show)

fox = Car {company="Volkswagen", model="Fox", year=2014}
stang = Car {company="Ford", model="Mustang", year=1967}

tellCar :: Car -> String
tellCar (Car {company = c, model = m, year = y}) = "This " ++ c ++ " " ++ m ++ " was made in " ++ show y

-- === Type parameters ===

data Vector a = Vector a a a deriving (Show)

vplus :: (Num t) => Vector t -> Vector t -> Vector t
(Vector i j k) `vplus` (Vector l m n) = Vector (i+l) (j+m) (k+n)

vectMult :: (Num t) => Vector t -> t -> Vector t
(Vector i j k) `vectMult` m = Vector (i*m) (j*m) (k*m)

scalarMult :: (Num t) => Vector t -> Vector t -> t
(Vector i j k) `scalarMult` (Vector l m n) = i*l + j*m + k*n

-- === Derived instances ===

data Person2 = Person2 { firstName2 :: String
                       , lastName2 :: String
                       , age2 :: Int
                       } deriving (Eq, Show, Read)

mikeD = Person2 {firstName2 = "Michael", lastName2 = "Diamond", age2 = 43}
adRock = Person2 {firstName2 = "Adam", lastName2 = "Horovitz", age2 = 41}
mca = Person2 {firstName2 = "Adam", lastName2 = "Yauch", age2 = 44}

-- mca == adRock
-- mikeD == adRock
-- mikeD == mikeD
-- mikeD == Person2 {firstName2 = "Michael", lastName2 = "Diamond", age2 = 43}

beastieBoys = [mca, adRock, mikeD]

-- mikeD `elem` beastieBoys

-- read (show mca) :: Person2

-- read "Just 't'" :: Maybe Char
-- True `compare` False
-- Just 3 `compare` Just 2

data Day = Monday | Tuesday | Wednesday | Thursday | Friday | Saturday | Sunday
  deriving ( Eq      -- == /=
           , Ord     -- > < >= <=
           , Show    -- show Wednesday
           , Read    -- read "Thursday" :: Day
           , Bounded -- minBound :: Day --> Monday, maxBound :: Day --> Sunday
           , Enum    -- has predecessor and successors and can make lists. Ex: succ Monday, pred Saturday, [Monday .. Sunday] (spaces needed)
           )

-- === Type synonyms ===

data LockerState = Taken | Free deriving (Show, Eq)
type Code = String
type LockerMap = Map.Map Int (LockerState, Code)

lockerLookup :: Int -> LockerMap -> Either String Code
lockerLookup lockerNumber map =
  case Map.lookup lockerNumber map of
    Nothing -> Left $ "Locker number " ++ show lockerNumber ++ " doesn't exist!"
    Just (state, code) -> if state /= Taken
                            then Right code
                            else Left $ "Locker " ++ show lockerNumber ++ " is already taken!"

lockers :: LockerMap
lockers = Map.fromList
    [(100,(Taken,"ZD39I"))
    ,(101,(Free,"JAH3I"))
    ,(103,(Free,"IQSA9"))
    ,(105,(Free,"QOTSA"))
    ,(109,(Taken,"893JJ"))
    ,(110,(Taken,"99292"))
    ]

-- lockerLookup 101 lockers
-- lockerLookup 100 lockers
-- lockerLookup 102 lockers
-- lockerLookup 110 lockers
-- lockerLookup 105 lockers

-- === Recursive data structures ===

-- data List a = Empty | Cons a (List a) deriving (Show, Read, Eq, Ord)
-- :t Cons 1 (Cons 2 (Cons 3 Empty))

-- data List a = Empty | Cons { listHead :: a, listTail :: List a } deriving (Show, Read, Eq, Ord)
-- Cons {listHead = 1, listTail = Cons {listHead = 2, listTail = Cons {listHead = 3, listTail = Empty}}}
-- 5 `Cons` (4 `Cons` (3 `Cons` Empty)) --> Cons {listHead = 5, listTail = Cons {listHead = 4, listTail = Cons {listHead = 3, listTail = Empty}}}

infixr 5 :-: -- Tells the compiler to consider :-: right associative and with precedence of 5
data List a = Empty | a :-: (List a) deriving (Show, Read, Eq, Ord)

infixr 5 .++
(.++) :: List a -> List a -> List a
Empty .++ ys = ys
(x :-: xs) .++ ys = x :-: (xs .++ ys)

a = 3 :-: 4 :-: 5 :-: Empty
b = 6 :-: 7 :-: Empty

-- a .++ b  --> 3 :-: (4 :-: (5 :-: (6 :-: (7 :-: Empty))))

-- Implementing a binary search tree

data Tree a = EmptyTree | Node a (Tree a) (Tree a) deriving (Show, Read, Eq)

singleton :: a -> Tree a
singleton x = Node x EmptyTree EmptyTree

treeInsert :: (Ord a) => a -> Tree a -> Tree a
treeInsert x EmptyTree = singleton x
treeInsert x (Node y left right)
    | x == y = Node x left right
    | x >  y = Node y left (treeInsert x right)
    | x <  y = Node y (treeInsert x left) right

treeElem :: (Ord a) => a -> Tree a -> Bool
treeElem x EmptyTree = False
treeElem x (Node y left right)
    | x == y = True
    | x >  y = treeElem x right
    | x <  y = treeElem x left

treeBalanced :: (Ord a) => [a] -> Tree a
treeBalanced xs = treeBalancedH (sort xs)
  where
    treeBalancedH [] = EmptyTree
    treeBalancedH xs = Node mid left right
      where
        mid   = xs !! (length xs `div` 2)
        left  = treeBalancedH (filter (<mid) xs)
        right = treeBalancedH (filter (>mid) xs)

treeList :: (Ord a) => Tree a -> [a]
treeList EmptyTree = []
treeList (Node y left right) = treeList left ++ [y] ++ treeList right

nums = [7,3,5,1,6,2,4]

t1 = treeInsert 7 (treeInsert 3 (treeInsert 5 (treeInsert 1 (treeInsert 6 (treeInsert 2 (treeInsert 4 EmptyTree))))))
t2 = foldr treeInsert EmptyTree nums
t3 = foldl (flip treeInsert) EmptyTree nums
t4 = foldl (flip treeInsert) EmptyTree (reverse nums)
t5 = treeBalanced [1..7]
t6 = treeBalanced nums
l6 = treeList t6

-- 3 `treeElem` t1 --> True
-- 14 `treeElem` t1 --> False

-- === Typeclasses 102 ===

{- class defines a TYPEclass actually, which is more like an "interface" in other languages

  Class  Type
      |  |
      V  V
class Eq a where
    (==) :: a -> a -> Bool
    (/=) :: a -> a -> Bool
    x == y = not (x /= y)  -- Since one function is defined in terms of the other
    x /= y = not (x == y)  -- it's only needed to implement one of them. (1)
-}

--      Type
--       |
--       V
data TrafficLight = Red | Yellow | Green

-- instance says that a type is an instance of a certain typeclass

--     Class      Type
--       |         |
--       V         V
instance Eq TrafficLight where
  Red == Red = True  -- (1)
  Green == Green = True
  Yellow == Yellow = True
  _ == _ = False

-- Red == Red --> True
-- Green == Blue --> False

instance Show TrafficLight where
  show Red = "Red light"
  show Yellow = "Yellow light"
  show Green = "Green light"

{-
instance (Eq m) => Eq (Maybe m) where
    Just x == Just y = x == y
    Nothing == Nothing = True
    _ == _ = False
-}

class YesNo a where
  yesno :: a -> Bool

instance YesNo Int where
  yesno 0 = False
  yesno _ = True

instance YesNo [a] where
  yesno [] = False
  yesno _ = True

instance YesNo Bool where
  yesno = id

instance YesNo (Maybe a) where
  yesno Nothing = False
  yesno (Just _) = True

instance YesNo (Tree a) where
  yesno EmptyTree = False
  yesno _ = True

instance YesNo TrafficLight where
  yesno Red = False
  yesno _ = True

-- yesno $ length []
-- yesno "haha"
-- yesno ""
-- yesno $ Just 0
-- yesno True
-- yesno EmptyTree
-- yesno []
-- yesno [0,0,0]

yesnoIf :: YesNo a => a -> t -> t -> t
yesnoIf yesnoVal yesResult noResult = if yesno yesnoVal then yesResult else noResult

-- yesnoIf [] "YEAH!" "NO!"
-- yesnoIf [2,3,4] "YEAH!" "NO!"
-- yesnoIf True "YEAH!" "NO!"
-- yesnoIf (Just 500) "YEAH!" "NO!"
-- yesnoIf Nothing "YEAH!" "NO!"

-- === The Functor typeclass ===

--               +--- a type constructor that takes one parameter (ex: Maybe)
--               V
-- class Functor f where
--    fmap :: (a -> b) -> f a -> f b   --> Compare it with: map :: (a -> b) -> [a] -> [b]

-- instance Functor [] where   --> actualy, map is an fmap that only works with lists [] is a "list-type constructor" here
--     fmap = map

-- instance Functor Maybe where
--    fmap f (Just x) = Just (f x)
--    fmap f Nothing = Nothing

-- fmap (++ " HEY GUYS IM INSIDE THE JUST") (Just "Something serious.") -->  Just "Something serious. HEY GUYS IM INSIDE THE JUST"
-- fmap (++ " HEY GUYS IM INSIDE THE JUST") Nothing  --> Nothing
-- fmap (*2) (Just 200)  --> Just 400
-- fmap (*2) Nothing   --> Nothing

instance Functor Tree where
    fmap f EmptyTree = EmptyTree
    fmap f (Node x l r) = Node (f x) (fmap f l) (fmap f r)

data Foo x = Bar x | Baz x deriving (Show)

-- :k Foo

class Tofu t where
  tofu :: j a -> t a j

data Frank a b = Frank {frankField :: b a} deriving (Show)
-- data Frank concreteType typeConstructor = Frank {frankField :: typeConstructor concreteType}

-- :t Frank {frankField = Just "HAHA"} --> Frank {frankField = Just "HAHA"} :: Frank [Char] Maybe
-- :t Frank {frankField = Node 'a' EmptyTree EmptyTree} --> Frank {frankField = Node 'a' EmptyTree EmptyTree} :: Frank Char Tree
-- :t Frank {frankField = "YES"}  --> Frank {frankField = "YES"} :: Frank Char []

instance Tofu Frank where
  tofu x = Frank x

-- tofu (Just 'a') :: Frank Char Maybe  --> Frank {frankField = Just 'a'}
-- tofu ["HELLO"] :: Frank [Char] []  --> Frank {frankField = ["HELLO"]}

data Barry t k p = Barry { yabba :: p, dabba :: t k }
-- data Barry typeConstructor concreteType1 concreteType2 = Barry { yabba :: concreteType2, dabba :: typeConstructor concreteType1 }

instance Functor (Barry a b) where
  fmap f (Barry {yabba = x, dabba = y}) = Barry {yabba = f x, dabba = y} -- ?!?!?!

-- TODO: Need to try some examples of this working !
