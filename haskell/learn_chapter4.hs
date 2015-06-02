-- :l C:\Local\Dev\haskell\learn_chapter4.hs
-- Syntax in Functions (Sintaxe em funções)

-- Pattern matching (correspondência de padrão)
lucky :: (Integral a) => a -> String
lucky 7 = "LUCKY NUMBER SEVEN!"
lucky x = "Sorry, you're out of luck, pal!"
-- A função lucky aceita um argumento que pertence à Typeclass Integral (Int, Integer) e retorna uma String
-- Se lucky for chamada com o argumento 7, esta versão é executada devido ao padrão
-- Nos demais casos esta é a versão chamada

sayMe :: (Integral a) => a -> String
sayMe 1 = "One!"
sayMe 2 = "Two!"
sayMe 3 = "Three!"
sayMe 4 = "Four!"
sayMe 5 = "Five!"
sayMe x = "Not between 1 and 5"
-- A ordem dos padrões é importante. Os mais genéricos devem vir por último.

-- Uma definição recursiva de fatorial
factorial :: (Integral a) => a -> a
factorial 0 = 1
factorial n = if n > 0 then n * factorial (n - 1) else error "No factorial for negative numbers"
-- No tutorial não eram verificados valores negativos

charName :: Char -> String
charName 'a' = "Alice"
charName 'b' = "Bob"
charName 'c' = "Charlie"
-- Por não ter um caso genérico, ao chamar charName 'd' ocorre uma exceção:
-- *** Exception: ... Non-exhaustive patterns in function charName

-- Duas formas de fazer a correspondência de padrão com tuplas

addVectors1 :: (Num a) => (a, a) -> (a, a) -> (a, a)
addVectors1 a b = (fst a + fst b, snd a + snd b)

addVectors :: (Num a) => (a, a) -> (a, a) -> (a, a)
addVectors (x1, y1) (x2, y2) = (x1 + x2, y1 + y2)

first :: (a, b, c) -> a
first (x,_,_) = x

second :: (a, b, c) -> b
second (_,y,_) = y

third :: (a, b, c) -> c
third (_,_,z) = z

head' :: [a] -> a
head' [] = error "Can't call head on an empty list, dummy!"
head' (x:_) = x
-- Ao fazer matching the listas, é possível isolar o primeiro elemento, mas é obrigatório usar parênteses neste caso por conta do :_

tell :: (Show a) => [a] -> String
tell [] = "The list is empty"
tell (x:[]) = "The list has one element: " ++ show x
tell (x:y:[]) = "The list has two elements: " ++ show x ++ " and " ++ show y
tell (x:y:_) = "The list is long. The first two elements are: " ++ show x ++ " and " ++ show y

-- (x:[]) poderia ser escrito como [x]
-- (x:y:[]) poderia ser escrito como [x,y]
-- (x:y:_) não poderia ser escrito de outra forma, devido ao :_

-- Uma versão recursiva de length
length' :: (Num b) => [a] -> b
length' [] = 0
length' (_:xs) = 1 + length' xs
-- Esse exemplo ilustra bem que todos os tipos possíveis de lista foram considerados
-- Já que [] é a lista vazia e _:xs é uma lista com um ou mais elementos

sum' :: (Num a) => [a] -> a
sum' [] = 0
sum' (x:xs) = x + sum' xs

capital :: String -> String
capital "" = "Empty string, whoops!"
capital all@(x:xs) = "The first letter of " ++ all ++ " is " ++ [x]

bmiTell :: (RealFloat a) => a -> String
bmiTell bmi
    | bmi <= 18.5 = "You're underweight."
    | bmi <= 25.0 = "You're normal weighted."
    | bmi <= 30.0 = "You're overweight."
    | otherwise = "You're obese."

bmiTell2 :: (RealFloat a) => a -> a -> String
bmiTell2 weight height
    | weight / height ^ 2 <= 18.5 = "You're underweight."
    | weight / height ^ 2 <= 25.0 = "You're normal weighted."
    | weight / height ^ 2 <= 30.0 = "You're overweight."
    | otherwise = "You're obese."

max' :: (Ord a) => a -> a -> a
max' a b
  | a > b = a
  | otherwise = b

myCompare :: (Ord a) => a -> a -> Ordering
a `myCompare` b
  | a > b = GT
  | a < b = LT
  | otherwise = EQ

bmiTell3 :: (RealFloat a) => a -> a -> String
bmiTell3 weight height
    | bmi <= 18.5 = "You're underweight."
    | bmi <= 25.0 = "You're normal weighted."
    | bmi <= 30.0 = "You're overweight."
    | otherwise = "You're obese."
    where bmi = weight / height ^ 2

bmiTell4 :: (RealFloat a) => a -> a -> String
bmiTell4 weight height
    | bmi <= under = "You're underweight."
    | bmi <= normal = "You're normal weighted."
    | bmi <= over = "You're overweight."
    | otherwise = "You're obese."
    where bmi = weight / height ^ 2
          under = 18.5
          normal = 25.0
          over = 30.0

bmiTell5 :: (RealFloat a) => a -> a -> String
bmiTell5 weight height
    | bmi <= under = "You're underweight."
    | bmi <= normal = "You're normal weighted."
    | bmi <= over = "You're overweight."
    | otherwise = "You're obese."
    where bmi = weight / height ^ 2
          (under, normal, over) = (18.5, 25.0, 30.0)

-- bmiTell5 109 1.83

initials :: String -> String -> String
initials firstname lastname = [f] ++ ". " ++ [l] ++ "."
    where (f:_) = firstname
          (l:_) = lastname
-- Alternative: initials (f:_) (l:_) = [f] ++ ". " ++ [l] ++ "."
-- initials "Felipo" "Soranz"

calcBmis :: (RealFloat a) => [(a, a)] -> [a]
calcBmis xs = [ bmi w h | (w, h) <- xs ]
    where bmi weight height = weight / height ^ 2
-- calcBmis [(109, 1.83), (85, 1.90)]

cylinder :: (RealFloat a) => a -> a -> a
cylinder r h =
    let sideArea = 2 * pi * r * h; topArea = pi * r ^ 2
    in sideArea + 2 * topArea

calcBmis2 :: (RealFloat a) => [(a, a)] -> [a]
calcBmis2 xs = [ bmi | (w, h) <- xs, let bmi = w / h ^ 2 ]

calcBmisFat :: (RealFloat a) => [(a, a)] -> [a]
calcBmisFat xs = [ bmi | (w, h) <- xs, let bmi = w / h ^ 2, bmi > 25.0 ]

headCase :: [a] -> a
headCase xs = case xs of [] -> error "Empty list has no head."
                         (x:_) -> x

describeList :: [a] -> String
describeList xs = "The list is " ++ case xs of []  -> "empty."
                                               [x] -> "a singleton list."
                                               xs  -> "a longer list."


describeList' :: [a] -> String
describeList' xs = "The list is " ++ what xs
    where what []  = "empty."
          what [x] = "a singleton list."
          what xs  = "a longer list."

