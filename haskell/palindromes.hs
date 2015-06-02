isPalindrome :: Eq a => [a] -> Bool
isPalindrome xs = xs == reverse xs

processPalindromes :: String -> String
processPalindromes contents = unlines $ map (\xs -> if isPalindrome xs then "palindrome" else "not a palindrome") (lines contents)

main :: IO ()
main = interact processPalindromes
