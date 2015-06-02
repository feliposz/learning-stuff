import Data.Char
import Control.Monad

main = forever $ do
    putStr "Input:"
    l <- getLine
    putStrLn $ map toUpper l
