-- :l C:\Local\Dev\haskell\learn_chapter10.hs

{-
-- V1, no folding, just recursion
solveRPN :: String -> Int
solveRPN expr = solve (words expr) []

solve :: [String] -> [Int] -> Int
solve ts ss
    | null ts = head ss
    | t == "+" = solve ts' (b + a : ss')
    | t == "-" = solve ts' (b - a : ss')
    | t == "*" = solve ts' (b * a : ss')
    | t == "/" = solve ts' (b `div` a : ss')
    | otherwise = solve ts' ((read t) : ss)
    where (t:ts') = ts
          (a:b:ss') = ss
-}

-- V2, using foldl

solveRPN :: String -> Float
solveRPN = head . foldl foldingF [] . words

foldingF :: [Float] -> String -> [Float]
foldingF stack item
    | item == "+" = y + x : stack'
    | item == "-" = y - x : stack'
    | item == "*" = y * x : stack'
    | item == "/" = y / x : stack'
    | item == "^" = y ** x : stack'
    | item == "ln" = (log (head stack)) : tail stack
    | item == "sum" = [sum stack]
    | otherwise = (read item) : stack
    where (x:y:stack') = stack

-- solveRPN "10 4 3 + 2 * -"  --> -4
-- solveRPN "2 3 +"  --> 5
-- solveRPN "90 34 12 33 55 66 + * - +" --> -3947
-- solveRPN "90 34 12 33 55 66 + * - + -"  --> 4037
-- solveRPN "90 34 12 33 55 66 + * - + -"  --> 4037
-- solveRPN "90 3 -"  --> 87

-- solveRPN "2.7 ln"  --> 0.9932518
-- solveRPN "10 10 10 10 sum 4 /"  --> 10.0
-- solveRPN "10 10 10 10 10 sum 4 /"  --> 12.5
-- solveRPN "10 2 ^"  --> 100.0
-- solveRPN "43.2425 0.5 ^"  --> 6.575903

{-
distances = [50, 10, 30, 5, 90, 20, 40, 2, 25, 10, 8, 0]
(roadA, roadB, roadC) = splitDistances distances

splitDistances' ds = splitD [] [] [] ds
    where
        splitD xs ys zs [] = (xs, ys, zs)
        splitD xs ys zs (x:y:z:ds) = splitD (x:xs) (y:ys) (z:zs) ds

splitDistances [] = ([],[],[])
splitDistances ds = (x:xs, y:ys, z:zs)
    where (x:y:z:ds')  = ds
          (xs, ys, zs) = splitDistances ds'

shortestDistance distances =
    where (roadA, roadB, roadC) = splitDistances distances


roadA = [50,  5, 40, 10] -- 105
roadC = [30, 20, 25,  0] -- 75
roadB = [10, 90,  2,  8] -- 110

10+30+5+20+2+8 = 75

-}

data Section = Section { getA :: Int, getB :: Int, getC :: Int } deriving (Show)
type RoadSystem = [Section]

distances = [50, 10, 30, 5, 90, 20, 40, 2, 25, 10, 8, 0]

buildRoadSystem :: [Int] -> RoadSystem
buildRoadSystem [] = []
buildRoadSystem ds = Section x y z : buildRoadSystem ds'
    where (x:y:z:ds') = ds

heathrowToLondon = buildRoadSystem distances

data Label = A | B | C deriving (Show, Eq)
type Path = [(Label, Int)]

roadStep :: (Path, Path) -> Section -> (Path, Path)
roadStep (pathA, pathB) (Section a b c) =
    let priceA = sum $ map snd pathA
        priceB = sum $ map snd pathB
        forwardPriceToA = priceA + a
        crossPriceToA = priceB + b + c
        forwardPriceToB = priceB + b
        crossPriceToB = priceA + a + c
        newPathToA = if forwardPriceToA <= crossPriceToA
                        then (A,a):pathA
                        else (C,c):(B,b):pathB
        newPathToB = if forwardPriceToB <= crossPriceToB
                        then (B,b):pathB
                        else (C,c):(A,a):pathA
     in (newPathToA, newPathToB)

optimalPath :: RoadSystem -> Path
optimalPath roadSystem =
    let (bestAPath, bestBPath) = foldl roadStep ([],[]) roadSystem
    in  if sum (map snd bestAPath) <= sum (map snd bestBPath)
            then reverse bestAPath
            else reverse bestBPath

-- optimalPath heathrowToLondon == [(B,10),(C,30),(A,5),(C,20),(B,2),(B,8),(C,0)]
