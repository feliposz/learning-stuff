fsfactorial n = product [1..n]
{-
factorial 4
product [1..4]
product [1,2,3,4]
1*2*3*4
24
-}

fsproduct :: Num a => [a] -> a
fsproduct [] = 1
fsproduct (x:xs) = x * product xs

fsfactrec :: (Num a, Eq a) => a -> a
fsfactrec 0 = 1
fsfactrec n = n * fsfactrec (n - 1)

{-
product [1..n] == fsfactrec n
product [1,2,3,...,n-1,n] == n * fsfactrec n - 1 = n * n - 1 * ... * fsfactrec 0
1 * 2 * 3 * ... * (n-1) * n == n * (n-1) * ... * 3 * 2 * 1
-}

fsdrop 0 xs = xs
fsdrop _ [] = []
fsdrop n (x:xs) = fsdrop (n-1) xs

-- Exercise 1

fsand :: [Bool] -> Bool
fsand [] = True
fsand (x:xs)
    | x = fsand xs
    | otherwise = False

fsconcat :: [[a]] -> [a]
fsconcat [] = []
fsconcat (x:xs) = x ++ fsconcat xs

fsreplicate :: Int -> a -> [a]
fsreplicate 0 _ = []
fsreplicate n x = x:fsreplicate (n-1) x

fsindex :: [a] -> Int -> a
fsindex (x:xs) 0 = x
fsindex (x:xs) n = fsindex xs (n - 1)

fselem :: Eq a => a -> [a] -> Bool
fselem _ [] = False
fselem n (x:xs)
    | n == x = True
    | otherwise = fselem n xs

-- Exercise 2

fsmerge :: [Int] -> [Int] -> [Int]
fsmerge xs [] = xs
fsmerge [] ys = ys
fsmerge xo@(x:xs) yo@(y:ys)
    | x <= y = x:fsmerge xs yo
    | otherwise = y:fsmerge xo ys

-- Exercise 3
fsmsort :: [Int] -> [Int]
fsmsort [] = []
fsmsort [x] = [x]
fsmsort xs = fsmerge (fsmsort firstHalf) (fsmsort secondHalf)
    where 
        (firstHalf, secondHalf) = splitAt mid xs
        mid = length xs `div` 2
