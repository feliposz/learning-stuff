import System.IO

main = do
    putStr "Task description: "
    hFlush stdout
    todo <- getLine
    appendFile "todo.txt" (todo ++ "\n")
