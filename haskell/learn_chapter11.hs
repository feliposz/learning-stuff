-- :l C:\Local\Dev\haskell\learn_chapter11.hs
import Data.Char
import Data.List
import Control.Applicative

-- A functor is a type of data that can be mapped (fmap) over. Ex: lists, Maybe, Either a, Tree, IO

-- Functors, Applicative Functors and Monoids

-- Functors redux

reverseTest = do
    line <- fmap reverse getLine
    putStrLn $ "You said " ++ line ++ " backwards!"

crazyStuff = do
    line <- fmap (intersperse '-' . reverse . map toUpper) getLine
    putStrLn line

data CMaybe a = CNothing | CJust Int a deriving (Show)

-- Not a real functor
instance Functor CMaybe where
    fmap f CNothing = CNothing
    fmap f (CJust counter x) = CJust (counter+1) (f x)

-- Because:

-- fmap id (CJust 0 "a") --> Returns (CJust 1 "a") and not (CJust 0 "a")  !!!

-- Applicative functors

a = fmap (*) [1,2,3,4]
-- a = [(*1), (*2), (*3), (*4)]
-- fmap (\f -> f 9) [(*1), (*2), (*3), (*4)]
-- [(9*1), (9*2), (9*3), (9*4)]
-- [9,18,27,36]

-- To use a normal function on applicative functors, just sprinkle some <$> and <*> about and the function will operate on applicatives and return an applicative.
mjt :: Maybe String
mjt = (++) <$> Just "johntra" <*> Just "volta"
-- compare with --
jt :: String
jt = (++) "johntra" "volta"

tabuada = fmap (*) [1..9] <*> [1..9]
-- fmap (*) [1,2,3,4,5,6,7,8,9] <*> [1,2,3,4,5,6,7,8,9]
-- [(1*),(2*),(3*),(4*),(5*),(6*),(7*),(8*),(9*)] <*> [1,2,3,4,5,6,7,8,9]
-- [ (1*1),(1*2),(1*3), ... (2*1),(2*2),(2*3), ... (9*8), (9*9) ]
-- [ 1, 2, 3, ... 2, 4, 6, ... 72, 81 ]

mult1 = [ x*y | x <- [2,5,10], y <- [8,10,11]]   
mult2 =  (*) <$> [2,5,10] <*> [8,10,11] 

myAction :: IO String  
myAction = (++) <$> getLine <*> getLine
-- Something like:
-- myAction = do  
--     a <- getLine  
--     b <- getLine  
--     return $ a ++ b  

zipped = getZipList $ (+) <$> ZipList [1,2,3] <*> ZipList [100,100,100]  

concat2Maybes = liftA2 (:) (Just 3) (Just [4]) -- or -- (:) <$> Just 3 <*> Just [4]  

sequenceA :: (Applicative f) => [f a] -> f [a]  
sequenceA = foldr (liftA2 (:)) (pure [])  

seqJust = sequenceA [Just 3, Just 2, Just 1]  -- Just [3,2,1]  

-- and $ map (\f -> f 7) [(>4),(<10),odd]  
-- and $ sequenceA [(>4),(<10),odd] 7  

-- So, what does this have to do with this newtype keyword? Well, think about how we might write the data declaration for our ZipList a type. One way would be to do it like so:
