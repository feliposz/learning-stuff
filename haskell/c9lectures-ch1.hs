-- 1930s Alonzo Churchu - Lambda Calculus
-- 1950s John McCarthy - Created LISP - first functional language
-- 1960s Peter Landin - ISWIM first pure functional language
-- 1970s John Backus - FP higher order functions
-- 1970s Robin Milner - ML type inference, polymorphic types
-- 1970s-1980s David Turner - Cecil, Miranda - Lazy functional languages
-- 1987 Haskell
-- 2003 Haskell 98 ?

main = putStrLn "Hello"

-- Quicksort implementation
-- (actually quicksort does sorting in place and not with lists)
f [] = []
f (x:xs) = f ys ++ [x] ++ f zs
    where
      ys = [a | a <- xs, a <= x]
      zs = [b | b <- xs, b > x]
