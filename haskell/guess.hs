import System.Random
import Control.Monad

main = do    
    gen <- getStdGen    
    putStrLn "Guess a number between 1 and 10:"
    guessesString <- fmap words getContents
    let (secret, _) = randomR (1,10) gen
        guesses = map readInt guessesString
    forM_ (takeWhile (/= secret) guesses) (\guess -> do
            case secret `compare` guess of
                LT -> putStrLn "Smaller"
                GT -> putStrLn "Bigger"
        )
    putStrLn "That's right"

readInt :: String -> Int
readInt = read
