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
    (command:args) <- getArgs
    let (Just action) = lookup command dispatch
    action args

add :: TaskAction
add [fileName, todoItem ] = appendFile fileName (todoItem ++ "\n")

view :: TaskAction
view [fileName] = do
    contents <- readFile fileName
    let todoTasks = lines contents
        numberedTasks = unlines $ zipWith (\n str -> show n ++ " - " ++ str) [0..] todoTasks
    putStrLn numberedTasks

remove :: TaskAction
remove [fileName, numberString] = do
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
    renameFile tempName fileName
