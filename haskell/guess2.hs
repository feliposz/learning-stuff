import System.IO
import System.Random
import Control.Monad

main = do
    gen <- getStdGen
    askForNumber gen
    
askForNumber :: StdGen -> IO ()
askForNumber gen = do
    let (randNumber, newGen) = randomR (1, 10) gen :: (Int, StdGen)
    putStr "What is the secret number (1-10)? "
    hFlush stdout
    numberString <- getLine
    when (not $ null numberString) $ do
        let [number] = reads numberString
        if randNumber == number
            then putStrLn "You are right!"
            else putStrLn $ "Sorry, it was " ++ show randNumber
        askForNumber newGen

    