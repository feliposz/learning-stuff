-- :l C:\Local\Dev\haskell\learn_chapter7.hs

-- To load a module interactively
-- :m + Data.List
-- Or several
-- :m + Data.List Data.Map Data.Set

-- To import modules in your module/program
import Data.List -- no need to qualify, prelude already imports some stuff
-- import Data.List (nub, sort) -- Import just some functions
-- import Data.List hiding (nub) -- Import hiding some of the functions

import qualified Data.Set -- Functions from this module must be called with Data.Set prefixed
import qualified Data.Map as Map -- Import functions in the context of "M" not in the Main context. For example, to acces Data.Map.filter, use Map.filter

import Data.Function -- `on`

import Data.Char

numUniques :: (Eq a) => [a] -> Int
numUniques = length . nub -- nub is from the Data.List module

-- Data.List

-- intersperse '.' MONKEY
-- intersperse 0 [1..5]

-- intercalate "-" ["abc", "def", "ghi"]
-- intercalate [0,1,2] [[3,4],[5,6],[7,8],[9,10,11]]

-- transpose [[1,2,3],[4,5,6],[7,8,9]]
-- map sum $ transpose [[0,3,5,9],[10,0,0,9],[8,5,1,-1]]

-- foldl' / foldl1' - strict (non-lazy) folds. Use thoses if getting stack overflow

-- concatMap, and, or, any, all, iterate, splitAt, takeWhile, dropWhile

-- any predicate list
-- or list = any (==True) list
-- and list = all (==True) list

-- make a list of powers of 2 from 64 to 1024 inclusive
example1 = dropWhile (<64) $ takeWhile (<=1024) $ iterate (*2) 1

-- span -- kind of a mix of splitAt and takeWhile. Returns a tuple with two list. The first has he same value takeWhile would return and the second is the rest of the list.
-- break -- same as span, but the condition is reversed
-- sort
-- group -- group subsequent elements if they are equal

-- count how many times each element repeats in a list (works for string and lists)
letters :: Ord a => [a] -> [(a, Int)]
letters = map (\ys -> (head ys, length ys)) . group . sort
--or-- letters xs = map (\ys -> (head ys, length ys)) (group (sort xs))

-- inits, tails, recursively applies init and tail to a list and return each intermediate step
-- inits "felipo" --> ["","f","fe","fel","feli","felip","felipo"]
-- tails "felipo" --> ["felipo","elipo","lipo","ipo","po","o",""]

-- A function to check if a sublist (needle) is contained into the list haystack (works with strings)
search needle haystack =
  let nlen = length needle
  in foldl (\acc x -> if take nlen x == needle then True else acc) False (tails haystack)
-- actually this function is already implemented in the module as inInfixOf and there is also isPrefixOf and isSuffixOf
-- elem/notElem are similar but only search for a single element (a Char in case of a String) and not a sublist (substring)

{-
-- A step-by-step trace of how this function is evaluated

1)
search "xx" "abxxcd" =
  let nlen = length "xx"
  in foldl (\acc x -> if take nlen x == "xx" then True else acc) False (tails "abxxcd")

2)
let nlen = 2
in foldl (\acc x -> if take nlen x == "xx" then True else acc) False ["abxxcd","bxxcd","xxcd","xcd","cd","d",""]

3)
foldl (\acc x -> if take 2 x == "xx" then True else acc) False ["abxxcd","bxxcd","xxcd","xcd","cd","d",""]

4)
(\acc=False x="abxxcd" -> if take 2 x == "xx" then True else acc) False ["bxxcd","xxcd","xcd","cd","d",""]
                          if "ab" == "xx" then True else acc
                          acc
                          False
5)
(\acc=False x="bxxcd"  -> if take 2 x == "xx" then True else acc) -- False ["xxcd","xcd","cd","d",""]
                          if "bx" == "xx" then True else acc
                          acc
                          False

6)
(\acc=False x="xxcd"   -> if take 2 x == "xx" then True else acc) -- False ["xcd","cd","d",""]
                          if "xx" == "xx" then True -- Found it here, but needs to keep folding until the end of the list
                          True

7)
(\acc=True  x="xcd"    -> if take 2 x == "xx" then True else acc) -- True ["cd","d",""]
                          if "xc" == "xx" then True else acc
                          acc
                          True
.
.
.
n-1)
(\acc=True  x=""       -> if take 2 x == "xx" then True else acc) -- True []
                          if "" == "xx" then True else acc
                          acc
                          True

n)
True

-}

-- partition p list - Split a list into two lists, one containing all elements that pass the predicate p and another with the elements that failed that predicate.
(mydigits, otherstuff) = partition (`elem` "0123456789") "abc123def456"

-- find p list - Returns the first value that satisfies the predicate p as "Just x" or "Nothing".
-- elemIndex e list - Like find, only it returns Maybe an index. I.e. Just i (where is is the index of the element) or Nothing if the element is not in the list.
-- elemIndices e list - Returns a list of indices for every occurrence of element in the list or an empty list if not found.
-- findIndex p list - Like elemIndex, but test for a predicate instead of an element equality

-- zip / zipWith also have a zip3/zipWith3 and other multiple versions up to 7

exampleZip3 = take 5 $ zip3 [0,3..] [1,4..] [2,5..]

-- lines -> string separted by \n to list of strings
-- unlines -> list of strings to string separated by \n
-- words -> split words by space and return list of strings (words)
-- unwords -> get a list of words and join then in a string separated by spaces
-- nub -> remove all duplicate elements from a list (non consecutive as well)
-- delete -> remove the first occurrence of element in the list
-- \\ -> is like applying delete for every element from the right list on the left list (it's almost like a MINUS set operation but only removing the FIRST element)
-- union -> merges two lists but with duplicates removed from the SECOND list (if there are duplicates on the first they are kept)
-- intersect -> returns only elements found on both lists
-- insert -> useful for inserting an element at the right position in a sorted list

-- Int version: length, take, drop, splitAt, !!, replicate
-- Num version: genericLength, genericTake, genericDrop, genericSplitAt, genericIndex, genericReplicate

-- Equality test: nub, delete, union, intersect, group
-- Generic test: nubBy, deleteBy, unionBy, intersectBy, groupBy
-- These function work with any function that you want to use for comparison (usually a specialized version of ==)

isUpperCase x = x `elem` ['A'..'Z']
groupByExample1 = groupBy (\x y -> isUpperCase x == isUpperCase y ) "ABCdefGHIjklMNOpqr"
-- ["ABC","def","GHI","jkl","MNO","pqr"]
groupByExample2 = groupBy (\x y -> (x > 0) == (y > 0)) [-4.3, -2.4, -1.2, 0.4, 2.3, 5.9, 10.5, 29.1, 5.3, -2.4, -14.5, 2.9, 2.3]

-- Standard ordering test: sort, insert, maximum, minimum
-- Your ordering function: sortBy, insertBy, maximumBy, minimumBy
sortByExample = sortBy (compare `on` length) [[5,4,5,4,4],[1,2,3],[3,5,4,3],[],[2],[2,2]]

encode :: Int -> [Char] -> [Char]
encode shift msg =
    let ords = map ord msg
        shifted = map (+ shift) ords
    in map chr shifted
--or-- map (\x -> chr (ord x + shift)) msg

decode :: Int -> [Char] -> [Char]
decode shift msg = encode (negate shift) msg

-- Data.Map

phoneBook = [ ("fulano", "234-5678"),
              ("sicrano", "456-7890"),
              ("beltrano", "567-8901"),
              ("mengano", "678-9012"),
              ("zutano", "789-0123") ]

findKey :: (Eq k) => k -> [(k,v)] -> v
findKey key xs = head [ v | (k,v) <- xs, k == key ]
-- from the book: findKey key xs = snd . head . filter (\(k,v) -> key == k) $ xs -- I prefer my version =)

findKey2 :: (Eq k) => k -> [(k,v)] -> Maybe v
findKey2 key [] = Nothing
findKey2 key ((k,v):xs) = if key == k then Just v else findKey2 key xs

findKey3 :: (Eq k) => k -> [(k,v)] -> Maybe v
findKey3 key xs = foldr (\(k,v) acc -> if key == k then Just v else acc) Nothing xs

-- equivalent to the function Data.List.lookup

-- when working with large association lists, it's better to use Data.Map
phoneMap = Map.fromList(phoneBook)

-- Map functions: empty insert null size singleton lookup member map filter fromList keys elems toList

fromList' :: (Ord k) => [(k,v)] -> Map.Map k v
fromList' xs = foldr (\(k,v) acc -> Map.insert k v acc) Map.empty xs

phoneBook2 =   
    [("betty","555-2938")  
    ,("betty","342-2492")  
    ,("bonnie","452-2928")  
    ,("patsy","493-2928")  
    ,("patsy","943-2929")  
    ,("patsy","827-9162")  
    ,("lucille","205-2928")  
    ,("wendy","939-8282")  
    ,("penny","853-2492")  
    ,("penny","555-2111")  
    ]  
    
-- Use Map.fromListWith if the original list have duplicates. You pass a function to handle the duplicates
    
phoneBookToMap :: (Ord k) => [(k, String)] -> Map.Map k String
phoneBookToMap xs = Map.fromListWith (\number1 number2 -> number1 ++ ", " ++ number2) xs

phoneBookToMap2 :: (Ord k) => [(k, a)] -> Map.Map k [a]
phoneBookToMap2 xs = Map.fromListWith (++) $ map (\(k,v) -> (k,[v])) xs

phoneBookMap = phoneBookToMap phoneBook2
phoneBookMap2 = phoneBookToMap2 phoneBook2

-- other examples: 
-- Map.fromListWith max [(2,3),(2,5),(2,100),(3,29),(3,22),(3,11),(4,22),(4,15)] -- Keep the largest value for duplicate keys
-- Map.fromListWith (+) [(2,3),(2,5),(2,100),(3,29),(3,22),(3,11),(4,22),(4,15)] -- Add the values having duplicate keys

-- Map.lookup "penny" phoneBookMap2

-- Data.Set (must be imported with 'qualified', optionally with "as Set")

text1 = "I just had an anime dream. Anime... Reality... Are they so different?"
text2 = "The old man left his garbage can out and now his trash is all over my lawn!"

set1 = Data.Set.fromList text1
set2 = Data.Set.fromList text2

-- Functions: empty, singleton, null, insert, delete, member, size, isSubsetOf, map, filter

-- Data.Set.intersection set1 set2  --> fromList " adefhilmnorstuy"
-- Data.Set.difference set1 set2    --> fromList ".?AIRj"
-- Data.Set.difference set2 set1    --> fromList "!Tbcgvw"
-- Data.Set.union set1 set2         --> fromList " !.?AIRTabcdefghijlmnorstuvwy"
-- Data.Set.size (Data.Set.intersection set1 set2) --> 16

-- Instead of using "nub" to remove duplicate elements from a list, it's faster in larger lists to create a set and then convert it back to a list, but elements must be of type "Ord", not only "Eq".
-- Also, elements will be sorted when using set, while nub keeps elements in the order they where
setNub xs = Data.Set.toList $ Data.Set.fromList xs

-- setNub "HEY WHATS CRACKALACKIN" --> " ACEHIKLNRSTWY"
-- nub "HEY WHATS CRACKALACKIN"    --> "HEY WATSCRKLIN"
