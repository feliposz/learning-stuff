{-------------------------------------------------

Exercise: implement the "nim" game

1: * * * * * 
2: * * * *
3: * * *
4: * *
5: *

players take turn removing one or more stars from the end of a row
the winner is the player who removes the last star or stars from the board

--------------------------------------------------}

import Data.Char

initialBoard :: [Int]
initialBoard = [5,4,3,2,1]

nim :: [Int] -> Int -> IO ()
nim board player = 
    do showBoard board
       if finished board then
         showWinner (nextPlayer player)         
       else
         do showMove player
            row <- getInt "Row: "
            stars <- getInt "Stars: "
            if valid board row stars then
              nim (playBoard board row stars) (nextPlayer player)
            else
              do putStrLn "ERROR: Invalid move"
                 nim board player

nextPlayer :: Int -> Int
nextPlayer 1 = 2
nextPlayer 2 = 1

finished :: [Int] -> Bool
finished = all (==0)

valid :: [Int] -> Int -> Int -> Bool
valid board row stars = board !! (row - 1) >= stars

playBoard :: [Int] -> Int -> Int -> [Int]
playBoard xs row stars = 
    [ if r == row then x - stars else x | (r, x) <- zip [1..] xs ]

isNumeric :: String -> Bool
isNumeric str = all isDigit str

getInt :: String -> IO Int
getInt s = do putStr s
              line <- getLine
              if isNumeric line then
                return (read line)
              else
                do putStrLn "ERROR: Invalid input"
                   getInt s

showMove :: Int -> IO ()
showMove player = do putStr "Player "
                     putStr (show player)
                     putStrLn " move:"

showWinner :: Int -> IO ()
showWinner player = do putStr "Player " 
                       putStr (show player)
                       putStrLn " wins!"

showBoard :: [Int] -> IO ()
showBoard xs = showBoardH $ zip [1..] xs
    where
        showBoardH [] = return ()
        showBoardH ((n, stars):xs) = 
            do putStr (show n)
               putStr ": " 
               putStrLn (concat (replicate stars "* "))
               showBoardH xs

main = nim initialBoard 1
