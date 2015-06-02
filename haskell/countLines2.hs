import System.Environment
import System.Directory
import System.IO

main = do
    (fileName:_) <- getArgs
    fileExists <- doesFileExist fileName
    if fileExists
        then do
            contents <- readFile fileName
            putStrLn $ "The file has " ++ show (length (lines contents)) ++ " lines."
        else putStrLn "File doesn't exist."
