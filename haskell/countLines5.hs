import System.Environment
import System.IO
import System.IO.Error
import Prelude hiding (catch)
import Control.Exception (catch)

main = doBlock `catch` handler
    where
        doBlock :: IO ()
        doBlock = do
            (fileName:_) <- getArgs
            contents <- readFile fileName
            putStrLn $ "The file has " ++ show (length (lines contents)) ++ " lines."            
        handler :: IOError -> IO ()
        handler e
            | isDoesNotExistError e = putStrLn $ "File doesn't exist: " ++ case ioeGetFileName e of 
                Nothing -> "unknown location"
                (Just fileName) -> fileName
            | otherwise = ioError e
            
