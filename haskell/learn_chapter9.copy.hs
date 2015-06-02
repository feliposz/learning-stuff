-- :l C:\Local\Dev\haskell\learn_chapter9.hs
import Data.Char
import Control.Monad (when,forever,forM)
import System.IO
import System.Directory
import Data.List
import System.Random
import qualified Data.ByteString.Lazy as B
import qualified Data.ByteString as S


-- To compile a program: ghc --make helloworld
-- To execute it on the fly: runhaskell helloworld.hs

-- === Input and Output ===

helloworld = putStrLn "hello, world"

helloyou = do
    putStrLn "Hello, what's your name?"
    name <- getLine
    putStrLn ("Hey " ++ name ++ ", you rock!")

names = do
    putStrLn "What is your first name?"
    firstName <- getLine
    putStrLn "What is your last name?"
    lastName <- getLine
    let bigFirstName = map toUpper firstName
        bigLastName = map toUpper lastName
    putStrLn $ "hey " ++ bigFirstName ++ " " ++ bigLastName ++ ", how are you?"

revwords = do
    line <- getLine
    if null line
        then return ()
        else do
            putStrLn $ reverseWords line
            revwords
-- Important: return doesn't work like in other languages!
-- It simply creates an IO action out of a regular expression.
-- It can be used at the end of a IO function to force a certain return value,
-- but it does not interrupt the code and exit immediatly like in imperative languages.

reverseWords :: String -> String
reverseWords = unwords . map reverse . words

-- getLine, putStrLn, putStr, putChar, print
-- Use putStrLn for strings (doesn't show quotes) and print for all other values

-- when - kind of like an if without the else part [the else part is always return ()]
whentest = do
    c <- getChar
    when (c /= ' ') $ do
        putChar c
        whentest

-- sequence/map/mapM_ are for performing IO actions over a list
seq1 = sequence (map print [1..5])
seq2 = do { sequence (map print [1..5]); return () }
seq3 = mapM print [1..5]
seq4 = mapM_ print [1..5]

-- forM is like mapM but with arguments reversed, useful in situations where a "for loop" would be used, like this:
fortest = do
    colors <- forM [1..4] (\a -> do
        putStr $ "Color " ++ show a ++ ": "
        color <- getLine
        return color)
    putStrLn "Colors: "
    mapM_ putStrLn colors

-- "forever" creates an "infinite" loop (Ctrl+C to interrupt)
capslocker = forever $ do
    putStr "Input:"
    l <- getLine
    putStrLn $ map toUpper l

-- "getContents" captures all the input (in a lazy way, i.e. it doesn't read all content on memory first. It reads content on demand)
capslocker2 = do
    contents <- getContents
    putStrLn $ map toUpper contents

-- "interact" is a higher order function that takes a function of type String->String then reads content from the input, call a function with that content and prints back the result

shortlines = interact shortLinesOnly

shortLinesOnly :: String -> String
shortLinesOnly input = let allLines = lines input
                           shortLines = filter (\s -> length s < 10) allLines
                           result = unlines shortLines
                       in  result

-- a less readable version but using composition
shortlines2 = interact $ unlines . filter ((<10) . length) . lines

-- import System.IO

readtest = do
    handle <- openFile "words.txt" ReadMode -- Open file for reading
    contents <- hGetContents handle         -- Get the contents of the file (like getContents, but getContents work at stdin)
    putStrLn contents                       -- Do something with the content (actually, due to laziness, up until this point, no data was read, only when putStr NEED the data to print, data will be read from the file)
    hClose handle                           -- Close the file (you must close the file yourself if you opened with openFile)

-- withFile does the same thing as the code above, handling the openFile/hClose
readtest2 = do
    withFile "words.txt" ReadMode (\handle -> do contents <- hGetContents handle
                                                 putStrLn contents)

-- equivalent generic IO operations: hGetLine, hPutStr, hPutStrLn, hGetChar

readtest3 = do
    contents <- readFile "words.txt"
    putStrLn contents

writename = do
    putStr "Please, type in a name: "
    name <- getLine
    writeFile "name.txt" (name ++ "\n")
    putStrLn "Name stored in file"

appendname = do
    putStr "Please, type in a name: "
    name <- getLine
    appendFile "name.txt" (name ++ "\n")
    putStrLn "Name appended to file"


readname = do
    name <- readFile "name.txt"
    putStr "Name read from file: "
    putStrLn name

readbuffered = do
    withFile "learn_chapter9.hs" ReadMode (\handle -> do
        hSetBuffering handle $ BlockBuffering (Just 2048) -- Sets buffering to read chunks of 2048 bytes
        contents <- hGetContents handle
        putStrLn contents)
-- Using buffering can improve performance, specially if file is on a slow network connection

todoappend = do
    putStr "Task description: "
    hFlush stdout
    todo <- getLine
    appendFile "todo.txt" (todo ++ "\n")

-- import System.Directory
-- import Data.List

tododelete = do
    handle <- openFile "todo.txt" ReadMode
    (tempName, tempHandle) <- openTempFile "." "temp"
    contents <- hGetContents handle
    let todoTasks = lines contents
        numberedTasks = zipWith (\n line -> show n ++ " - " ++ line) [0..] todoTasks
    putStrLn "These are your TO-DO items:"
    putStr $ unlines numberedTasks -- mapM putStrLn numberedTasks
    putStrLn "Which one do you want to delete?"
    numberString <- getLine
    let number = read numberString
        newTodoItems = delete (todoTasks !! number) todoTasks
    hPutStr tempHandle $ unlines newTodoItems
    hClose handle
    hClose tempHandle
    removeFile "todo.txt"
    renameFile tempName "todo.txt"

-- Command line arguments
-- check: task.hs
    
-- Randomness

rndPair = random (mkStdGen 100) :: (Int, StdGen)
(rndNum, rndNext) = rndPair

-- random (mkStdGen 949488) :: (Float, StdGen) 

threeCoins :: Int -> (Bool, Bool, Bool)
threeCoins gen = 
    let gen1 = (mkStdGen gen)
        (coin1, gen2) = random gen1
        (coin2, gen3) = random gen2
        (coin3, _)    = random gen3
     in (coin1, coin2, coin3)

-- > Actually, there is function called randoms that returns an infinite list of randoms
randomList :: Random a => Int -> [a]
randomList seed = randomBuilder (mkStdGen seed)
    where 
        randomBuilder gen = 
            let (num, nextGen) = random gen 
             in num : randomBuilder nextGen

-- take 10 $ randomList 100 :: [Int]
-- take 10 $ randomList 100 :: [Bool]
-- take 10 $ randomList 100 :: [Float]

randomString = do
    gen <- getStdGen
    putStrLn $ take 20 (randomRs ('a', 'z') gen)
    gen' <- newStdGen -- also reinitializes the global number generator
    putStrLn $ take 20 (randomRs ('a', 'z') gen')

-- Bytestrings

-- Word8 is an integer that can hold 0-255, i.e. a byte

upperB = B.pack [65..90]
lowerB = B.pack [97..122]

upperS = S.pack [65..90]
lowerS = S.pack [97..122]

-- B.unpack(upperB)

-- cons is the equivalent of : for bytestrings (also cons' is strict, if inserting a lot of bytes at the beginning of a bytestring)
newBS = B.cons 65 $ B.cons 66 $ B.cons 67 $ B.empty -- "ABC"

-- build a bytestring from right to left using cons', starting with empty
otherBS = foldr B.cons' B.empty [65..90]

-- Other functions available for bytestrings, equivalent to Data.List: head, tail, init, null, length, map, reverse, foldl, foldr, concat, takeWhile, filter, etc.
-- Also, there are equivalent functions to System.IO: readFile
-- WARNING: when using Strict Bytestrings, files are read into memory all at once, while using Lazy Bytestrings, files are read in chunks

-- If using Lazy Bytestrings, this function reads and writes in chunks, but if using Strict the whole file needs to be read into memory before it can be written
copyFile :: FilePath -> FilePath -> IO ()
copyFile source dest = do
    contents <- B.readFile source
    B.writeFile dest contents
