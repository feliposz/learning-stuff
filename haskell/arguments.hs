import System.Environment
import Data.List

main = do
    args <- getArgs
    progName <- getProgName
    putStrLn "Arguments:"
    mapM putStrLn args
    putStrLn "Program name:"
    putStrLn progName
