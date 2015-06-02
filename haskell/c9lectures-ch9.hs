-- to implement getCh below
import System.IO 

a :: IO (Char, Char)
a  = do x <- getChar   -- Char <- IO Char
        getChar        -- _ <- IO Char
        y <- getChar   -- ...
        return (x,y)   -- IO (Char, Char)

-- do is syntatic sugar for the bind operator (>>=)
-- (>>=) : IO a -> (a -> IO b) -> IO b
{- The function above would look like:
getChar >>= \x ->
    getChar >>= \_ -> 
        getChar >>= \y ->
            return (x, y)
-}

-- a reimplementation of standard getLine
fsGetLine :: IO String
fsGetLine = do x <- getChar
               if x == '\n' then
                 return []
               else
                 do xs <- fsGetLine
                    return (x:xs)

-- a reimplementation of standard putStr
fsPutStr :: String -> IO ()
fsPutStr []     = return ()
fsPutStr (x:xs) = do putChar x
                     fsPutStr xs

-- a reimplementation of standard putStrLn
fsPutStrLn :: String -> IO ()
fsPutStrLn xs = do fsPutStr xs
                   putChar '\n'

fsStrLen :: IO ()
fsStrLen = do fsPutStr "Enter a string: "
              xs <- fsGetLine
              fsPutStr "The string has "
              fsPutStr (show (length xs))
              fsPutStrLn " characters"

example1 = do s <- fsGetLine
              fsPutStrLn s

{-------------------------------
Hangman

one player types secret word
the other player enters a sequence of guesses
for each guess, the computer says which letter in the secret word occur in the guess(?)
--------------------------------}

-- Hugs originaly had a primitive getCh :: IO Char, but that doesn't work
-- This should work with GHCI (needs import System.IO)
-- Check also FFI (foreign function interface) for importing/exporting external functions from C
getCh :: IO Char
getCh = 
    do hSetEcho stdin False
       c <- getChar
       hSetEcho stdin True
       return c

hangman :: IO ()
hangman = 
    do putStrLn "Think of a word: "
       word <- sgetLine
       putStrLn "Try to guess it: "
       guess word

-- same as getLine, but omit letters being typed
sgetLine :: IO String
sgetLine = 
    do x <- getCh
       if x == '\n' then
         do putChar x
            return []
       else
         do putChar '-'
            xs <- sgetLine
            return (x:xs)

-- compare every line entered by the player with the secret word
guess :: String -> IO ()
guess word = 
    do putStr "> "
       xs <- getLine
       if xs == word then
         putStrLn "You got it!"
       else
         do putStrLn (diff word xs)
            guess word

-- abstracting "guess" and turning it into a higher order function
interactiveDialog :: IO () -> (String -> Bool) -> IO () -> (String -> IO ()) -> (a -> a) -> a -> IO ()
interactiveDialog prelude test terminate success next state =
    do prelude
       xs <- getLine
       if test xs then
         terminate
       else
         do success xs
            interactiveDialog prelude test terminate success next (next state)

-- reimplementing guess using the higher order function created...
guess' :: String -> IO ()
guess' word = 
    interactiveDialog (putStr "> ") 
                      (==word) 
                      (putStrLn "You got it!") 
                      (\xs -> putStrLn (diff word xs))
                      id
                      word

-- or "naming" the parameters
guess2 :: String -> IO ()
guess2 word = 
    let r = putStr "> "
        p = (==word)
        t = (putStrLn "You got it!") 
        s = (\xs -> putStrLn (diff word xs))
    in interactiveDialog r p t s id word


-- for exery letter from xs that are present in ys, show it, but if it's not, show a '-'
diff :: String -> String -> String
diff xs ys = [ if x `elem` ys then x else '-' | x <- xs ]

{-------------------------------------------------

Exercise: implement the "nim" game

1: * * * * * 
2: * * * *
3: * * *
4: * *
5: *

players take turn removing one or more stars from the end of a row
the winner is the player who removes the last star or stars from the board

Initial implementation. It works, but check the refactored (clearer) version at:
D:\OneDrive\Desenvolvimento\Projects\Haskell\c9lectures-ch9-nim.hs

--------------------------------------------------}

initialBoard :: [Int]
initialBoard = [5,4,3,2,1]

nim :: [Int] -> Int -> IO ()
nim board player = 
    do showBoard board
       putStrLn ("Player " ++ (show player) ++ " move:")
       putStr "Row: "
       row <- getLine
       putStr "Stars: "
       stars <- getLine
       let newBoard = playBoard board (read row) (read stars)
        in if all (==0) newBoard then 
              putStrLn ("Player " ++ (show player) ++ " wins!")
           else
              nim newBoard (if player == 1 then 2 else 1)

playBoard :: [Int] -> Int -> Int -> [Int]
playBoard xs row stars = 
    [ if n == row then (if x >= stars then x - stars else 0) else x | (n, x) <- zip [1..] xs ]

showBoard :: [Int] -> IO ()
showBoard xs = showBoardHelper $ zip [1..] xs
    where
        showBoardHelper [] = return ()
        showBoardHelper ((n, stars):xs) = 
            do putStrLn ((show n) ++ ": " ++ (replicate stars '*'))
               showBoardHelper xs

main = return ()
