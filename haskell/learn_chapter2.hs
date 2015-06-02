-- :load C:\Local\Dev\haskell\learn_chapter2.hs

removeNonUppercase :: [Char] -> [Char]
removeNonUppercase st = [ c | c <- st, c `elem` ['A'..'Z'] ]

add3 :: Int -> Int -> Int -> Int
add3 x y z = x + y + z

addLengths :: String -> String -> Int
addLengths s1 s2 = length s1 + length s2

circumference :: Float -> Float
circumference r = 2 * pi * r

circumference' :: Double -> Double
circumference' r = 2 * pi * r


