
module Geometry
( sphereVolume
, sphereArea
, cubeVolume
, cubeArea
, cuboidArea
, cuboidVolume
) where

sphereVolume :: Float -> Float
sphereVolume radius = (4.0/3.0) * pi * (radius ^ 3)

sphereArea :: Float -> Float
sphereArea radius = 4 * pi * (radius ^ 2)

cubeVolume :: Float -> Float
cubeVolume side = cuboidVolume side side side

cubeArea :: Float -> Float
cubeArea side = cuboidVolume side side side
8
cuboidArea :: Float -> Float -> Float -> Float
cuboidArea a b z = 2 * (rectangleArea a b + rectangleArea a z + rectangleArea b z)

cuboidVolume :: Float -> Float -> Float -> Float
cuboidVolume a b c = rectangleArea a b * c

rectangleArea :: Float -> Float -> Float
rectangleArea a b = a * b

{-
test = if testsPassed then putStrLn "All tests passed" else "Tests failed"
  where testsPassed = 
    all [ 
        ,
        ]
-}
