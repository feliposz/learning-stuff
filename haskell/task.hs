import System.Environment
import System.Directory
import System.IO
import Data.List

type TaskAction = [String] -> IO ()

dispatch :: [ ( String, TaskAction ) ]
dispatch = [ ("add", add)
           , ("view", view)
           , ("remove", remove)
           ]

main = do
    args <- getArgs
    if args == []
        then errorExit
        else let command = head args
                 commandArgs = tail args
              in case lookup command dispatch of
                    Nothing -> errorExit
                    (Just action) -> action commandArgs

errorExit :: IO ()
errorExit = do
    progName <- getProgName
    putStrLn "Usage:"
    putStrLn []
    putStrLn ("Add a task:    " ++ progName ++ " add <filename> <task description>")
    putStrLn ("List tasks:    " ++ progName ++ " view <filename>")
    putStrLn ("Remove a task: " ++ progName ++ " remove <filename> <task number>")

ifFileExists :: String -> IO () -> IO ()
ifFileExists fileName doSomething = do
    exists <- doesFileExist fileName 
    if exists
        then doSomething
        else putStrLn ("File " ++ fileName ++ " not found.")

add :: TaskAction
add [fileName, todoItem] = appendFile fileName (todoItem ++ "\n")
add _ = errorExit

view :: TaskAction
view [fileName] = ifFileExists fileName (do
    contents <- readFile fileName
    let todoTasks = lines contents
        numberedTasks = unlines $ zipWith (\n str -> show n ++ " - " ++ str) [0..] todoTasks
    putStr numberedTasks)
view _ = errorExit

-- TODO: Test if numberString is a valid number
remove :: TaskAction
remove [fileName, numberString] = ifFileExists fileName (do
    handle <- openFile fileName ReadMode
    (tempName, tempHandle) <- openTempFile "." "temp"
    contents <- hGetContents handle
    let number = read numberString
        todoTasks = lines contents
        newTodoTasks = delete (todoTasks !! number) todoTasks
        newContents = unlines newTodoTasks
    hPutStr tempHandle newContents
    hClose handle
    hClose tempHandle
    removeFile fileName
    renameFile tempName fileName)
remove _ = errorExit
