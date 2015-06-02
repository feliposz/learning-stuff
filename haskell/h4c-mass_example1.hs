--types for variables
type Mass = Double                  -- Only a type rename
type Pos = (Double, Double, Double) -- x, y, z
type Obj = (Mass, Pos)              -- (Double, (Double, Double, Double))

--list of functions needed
{-
Take a list of objects.
Returns a list of (sum of mass times other object mass over distance from all objects).
Order is preserved.
-}

--overall function type
calcMassesOverDists :: [Obj] -> [Double]
calcMassesOverDists objs = calcHelper objs objs

--Iterates over the list of objects and calculates the sum for each
--It uses pattern matching to recurse and terminate    
calcHelper :: [Obj] -> [Obj] -> [Double]
calcHelper (obj:objs) objList = (sum (calcMMoD obj objList)) : calcHelper objs objList
calcHelper [] _ = []

-- This calculates the list of mass times mass over distance for a single object.   
-- It uses pattern matching to recurse and terminate and a where clause to keep the code cleaner 
calcMMoD :: Obj -> [Obj] -> [Double]
calcMMoD obj@(m1, pos1) ((m2, pos2):rest) = safeValue : calcMMoD obj rest
    where
    dist = distXYZ pos1 pos2
    safeValue = if pos1 == pos2 then 0 else m1 * m2 / dist
calcMMoD _ [] = []

--This is a function that computes a distance between two Pos values
distXYZ :: Pos -> Pos -> Double
distXYZ (x1, y1, z1) (x2, y2, z2) = sqrt (xd*xd + yd*yd + zd*zd)
    where
    (xd, yd, zd) = (x1 - x2, y1 - y2, z1 - z2)


