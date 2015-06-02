-- :l C:\Local\Dev\haskell\h4c.hs


-- Exemplos do tutorial http://www.haskell.org/haskellwiki/Haskell_Tutorial_for_C_Programmers

-- 2.5

-- Uma implementa��o recursiva de fib que usa uma fun��o auxiliar geradora
fib :: Integer -> Integer
fib n = fibGen 0 1 n

fibGen :: Integer -> Integer -> Integer -> Integer
fibGen a b n = case n of
    0 -> a
    n -> fibGen b (a + b) (n - 1)

-- Uma lista infinita de n�meros de fibonacci
fibs :: [Integer]
fibs = 0 : 1 : [ a + b | (a, b) <- zip fibs (tail fibs) ]

-- 3.3

-- Pequeno teste de aplica��o de uma fun��o que recebe dois par�metros e retorna um terceiro
apply' :: (a -> b -> c) -> a -> b -> c
apply' f a b = f a b

-- Aplica a fun��o do tipo (a -> b) a todos os elementos de a gerando uma nova lista
map' :: (a -> b) -> [a] -> [b]
map' f xs = [ f x | x <- xs ]

-- Vers�o recursiva de map
mapRec :: (a -> b) -> [a] -> [b]
mapRec f [] = []
mapRec f xs = f (head xs):(mapRec f (tail xs))

fooList :: [Int]
fooList = [3, 1, 5, 4]

bar :: Int -> Int
bar n = n - 2

-- Ex: map bar fooList

-- 3.4

subEachFromTen :: [Int] -> [Int]
subEachFromTen = map (10 -)

-- 4.1

-- Reimplementa��o de fib usando correspond�ncia de padr�o
-- Foi necess�rio usar Num a, Eq a devido a uma altera��o recente na linguagem em que Num n�o implica mais em Eq
fibPat :: (Num a, Eq a, Num b, Eq b) => a -> b
fibPat n = fibGenPat 0 1 n

fibGenPat :: (Num a, Eq a, Num b, Eq b) => b -> b -> a -> b
fibGenPat x _ 0 = x
fibGenPat x y n = fibGenPat y (x + y) (n - 1)

-- 4.2

showTime :: Int -> Int -> String
showTime hours minutes
    | hours > 23   = error "Invalid hours"
    | minutes > 59 = error "Invalid minutes"
    | hours == 0   = showHour 12 "am"
    | hours <= 11  = showHour hours "am"
    | hours == 12  = showHour hours "pm"
    | otherwise    = showHour (hours - 12) "pm"
    where
    showHour h s = (show h) ++ ":" ++ showMin ++ " " ++ s
    showMin
        | minutes < 10 = "0" ++ show minutes
        | otherwise    = show minutes

-- 4.3

showLen :: [a] -> String
showLen lst = (show (theLen)) ++ (if theLen == 1 then " item" else " itens")
    where theLen = length lst

-- 4.5 Lambda

-- map (\x -> "the " ++ show x) [1, 2, 3, 4, 5]

-- 4.6 Type constructors

-- main = putStrLn (show 4)
-- main = return ()

sayHello :: Maybe String -> String
sayHello Nothing = "Hello!"
sayHello (Just name) = "Hello, " ++ name ++ "!"

-- Exemplos: sayHello Nothing, sayHello (Just "Felipo")

-- 4.7 Monadas
-- � uma forma de mesclar a programa��o "imperativa" (com estado, ordem de execu��o, resolu��o imediata, entrada/sa�da, etc.) com a programa��o funcional.

someFunc :: Int -> Int -> [Int]
someFunc a b = [a, b]

main = do putStr "prompt 1: "
          a <- getLine
          putStr "prompt 2: "
          b <- getLine
          putStrLn (show (someFunc (read a) (read b)))

-- Comandos depois da palavra-chave "do" devem estar alinhados para denotar um bloco
test1 = do
    putStrLn "Sum 3 numbers"
    putStr "num 1: "
    a <- getLine
    putStr "num 2: "
    b <- getLine
    putStr "num 3: "
    c <- getLine
    putStrLn ("Result is " ++ (show ((read a) + (read b) + (read c))))

getName1 :: IO String
getName1 = do
    putStr "Please enter your name: "
    name <- getLine
    putStrLn "Thank you. Please wait."
    return name

getName2 :: IO String
getName2 = do
    putStr "Please enter your name: "
    getLine

{-
O �ltimo c�digo dentro de uma Monada deve ter o mesmo tipo de retorno da Monada
  :t getLine
  getLine :: IO String
Em getName1 � realizado um retorno expl�cito de uma vari�vel.
Em getName2 � retornado o pr�prio valor retornado por getLine diretamente
-}

-- 5.1 Where are the for loops

-- Como um "for" � escrito em haskell

bar' :: Int -> Int
bar' x = x*2

foo' :: [Int] -> [Int]
foo' (x:xs) = bar' x : foo' xs
foo' [] = []

-- Equivale a definir foo' = map bar'
-- Muitos casos de "for" s�o escritos como map, foldr, foldl, sum, dentre outras fun��es de tratamento de lista de haskell altamente otimizadas
-- Quando n�o for poss�vel usar nenhum dos casos uma fun��o recursiva deve resolver o problema.


-- 5.2 Lazy Evaluation

data Tree a = Null | Node a (Tree a) (Tree a)

t1 :: Tree Int
t1 = Node 3 (Node 2 Null Null) (Node 5 (Node 4 Null Null) Null)

inOrderList :: Tree a -> [a]
inOrderList Null = []
inOrderList (Node item left right) =
    inOrderList left ++ [item] ++ inOrderList right

fooTree :: Int -> Tree Int
fooTree n = Node n (fooTree (n - 1)) (fooTree (n + 1))

t2 = fooTree 0

inOrderListLevel :: Tree a -> Int -> [a]
inOrderListLevel Null _ = []
inOrderListLevel _ 0 = []
inOrderListLevel (Node item left right) n =
    inOrderListLevel left (n - 1) ++ [item] ++ inOrderListLevel right (n - 1)

fooTreeLevel :: Int -> Int -> Tree Int
fooTreeLevel _ 0 = Null
fooTreeLevel n l = Node n (fooTreeLevel (n - 1) (l - 1)) (fooTreeLevel (n + 1) (l - 1))

