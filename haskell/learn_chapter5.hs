-- :l C:\Local\Dev\haskell\learn_chapter5.hs

-- Recursion

fsmaximum1 :: Ord a => [a] -> a
fsmaximum1 (x:xs)
    | null xs = x
    | otherwise = max x (fsmaximum1 xs)

fsmaximum2 :: Ord a => [a] -> a
fsmaximum2 [] = error "maximum of empty list"
fsmaximum2 [x] = x
fsmaximum2 (x:xs) = max x (fsmaximum2 xs)

fsreplicate :: (Num n, Ord n) => n -> e -> [e]
fsreplicate n e
    | n <= 0 = []
    | otherwise = e:fsreplicate (n-1) e

fstake :: (Num n, Ord n) => n -> [x] -> [x]
fstake n xs
    | n <= 0 || null xs = []
    | otherwise = head xs:fstake (n-1) (tail xs)

fstake2 :: (Num n, Ord n) => n -> [x] -> [x]
fstake2 n [] = []
fstake2 n (x:xs)
    | n <= 0 = []
    | otherwise = x:fstake2 (n-1) xs

{-

take 3 [4,3,2,1]
take n = 3, x = 4, xs = [3,2,1]
  return 4 : take 2 [3,2,1]
             take n = 2, x = 3, xs = [2,1]
               return 3 : take 1 [2,1]
                          take n = 1, x = 2, xs = [1]
                             return 2 : take 0 [1]
                                        []
                                        
         4 :          3 :           2 : []
  4:3:2:[]
[4,3,2]

-}

fsreverse :: [a] -> [a]
fsreverse [] = []
fsreverse (x:xs) = fsreverse xs ++ [x]

fsrepeat :: a -> [a]
fsrepeat x = x:fsrepeat x

fszip :: [a] -> [b] -> [(a,b)]
fszip [] _ = []
fszip _ [] = []
fszip (x:xs) (y:ys) = (x,y):fszip xs ys

-- Challenge - DONE! GREAT!
fsunzip :: [(a, b)] -> ([a], [b])
fsunzip pairs = uzHelp pairs [] []
    where
        uzHelp [] xs ys = (reverse xs, reverse ys) -- Assuming reversing one time is faster than adding to the tail every cycle
        uzHelp ((x,y):pairs) xs ys = uzHelp pairs (x:xs) (y:ys) -- Tail recursion optimization possible

-- Even better!!! =D
-- Unzip elements and assemble the lists in order, but no tail recursion opt. possible (right?!)
fsunzip2 :: [(a, b)] -> ([a], [b])
fsunzip2 [] = ([], [])
fsunzip2 ((x,y):pairs) = ((x:xs), (y:ys))
    where (xs, ys) = fsunzip2 pairs
        
fselem :: Eq e => e -> [e] -> Bool
fselem _ [] = False
fselem e (x:xs)
    | e == x = True
    | otherwise = fselem e xs

fsqsort :: (Ord a) => [a] -> [a]
fsqsort [] = []
fsqsort (x:xs) = smaller ++ [x] ++ bigger
  where smaller = fsqsort [a | a <- xs, a <= x] 
        bigger  = fsqsort [a | a <- xs, a > x]

fssum :: (Num a) => [a] -> a
fssum [] = 0
fssum (x:xs) = x + fssum xs

fsproduct :: (Num a) => [a] -> a
fsproduct [] = 1
fsproduct (x:xs) = x * fsproduct xs

fslength :: (Num a) => [a] -> a
fslength [] = 0
fslength (_:xs) = 1 + fslength xs

-- main = putStr $ show $ fsunzip2 [(1, True), (0, False)]
