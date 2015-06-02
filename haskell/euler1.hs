-- :l C:\Local\Dev\haskell\euler1.hs

-- v1
a = sum (take (999 `div` 3) [3,6..]) 
b = sum (take (999 `div` 5) [5,10..])
c = sum (take (999 `div` 15) [15,30..])
result1 = a + b - c

-- v2
result2 = sum [x | x <- [1..999], x `mod` 3 == 0 || x `mod` 5 == 0]

-- v3

myF :: (Integral a) => a -> a -> a
myF n f = (p + u) * q `div` 2
  where q = n `div` f
        p = f
        u = q * f


result3 :: (Integral a) => a
result3 = (myF n 3) + (myF n 5) - (myF n 15)
  where n = 999

