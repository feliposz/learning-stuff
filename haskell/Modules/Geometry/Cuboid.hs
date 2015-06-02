module Geometry.Cuboid
( area
, volume
) where

area :: Float -> Float -> Float -> Float
area a b z = 2 * (rectangleArea a b + rectangleArea a z + rectangleArea b z)

volume :: Float -> Float -> Float -> Float
volume a b c = rectangleArea a b * c

rectangleArea :: Float -> Float -> Float
rectangleArea a b = a * b
