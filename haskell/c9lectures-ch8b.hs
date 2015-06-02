-- :l D:\OneDrive\Desenvolvimento\Projects\Haskell\c9lectures-ch8b.hs
import Parsing

-- (>>=)  SelectMany (????)


p :: Parser (Char, Char)
p = do x <- item
       item -- throw away, kind of like '_ <- item'
       y <- item
       return (x, y)

-- parse p "abcdef" --> [(('a','c'),"def")] 
       
{-       
     a    [a]         a    Parser a            
     |     |          |      |
     v     v          v      v
[x | x <- xs ]     do x <- item       
-}

{- (?????)
from x in item
from _ in item
from y in item
select new{x, y}
-}

p2 :: Parser String
p2 = do char '['
        d <- digit
        ds <- many (do char ',' 
                       digit)
        char ']'
        return (d:ds)

-- parse p2 "[1,2,3,4,5]" --> [("12345","")]

-- Expressions:  digits, +, *, (, )
-- * + associate to the right
-- * has higher priority than +      

{- 

Context free grammar (1 - original)

expr -> term '+' expr | term
term -> factor '*' term | factor
factor -> digit | '(' expr ')'
digit -> '0' | '1' | ... | '9'

Context free grammar (2 - factorized)

expr -> term ( '+' expr | empty )
term -> factor ( '*' term | empty )
factor -> digit | '(' expr ')'
digit -> '0' | '1' | ... | '9'

-}        

-- Exercise 2 applied

expr :: Parser Int
expr = do t <- term
          do char '+'
             e <- expr
             return (t + e)
           +++ do char '-'
                  e <- expr
                  return (t - e)
                +++ return t

term :: Parser Int
term = do f <- factor
          do char '*'
             t <- term
             return (f * t)
           +++ do char '/'
                  t <- term
                  return (f `div` t)
                +++ return f

factor :: Parser Int           
factor = do d <- digit
            return (digitToInt d)
          +++ do char '('
                 e <- expr
                 char ')'
                 return e

digitToInt :: Char -> Int
digitToInt d = read (d : [])
                 
eval :: String -> Int
eval xs = fst (head (parse expr xs))


-- Exercise 1 - Reimplemented using the original
-- This approach is less efficient because of the need to always try the entire expression instead of failing early. (RIGHT?)


-- expr -> term '+' expr | term

expr' :: Parser Int
expr'  = do t <- term'
            char '+'
            e <- expr'
            return (t + e)
          +++ do t <- term'
                 return t

-- term -> factor '*' term | factor

term' :: Parser Int
term' = do f <- factor'
           char '*'
           t <- term'
           return (f * t)
         +++ do f <- factor'
                return f

-- factor -> digit | '(' expr ')'

factor' :: Parser Int           
factor' = do d <- digit
             return (digitToInt' d)
           +++ do char '('
                  e <- expr'
                  char ')'
                  return e

-- digit -> '0' | '1' | ... | '9'

digitToInt' :: Char -> Int
digitToInt' d = read [d]
                 
eval' :: String -> Int
eval' xs = fst (head (parse expr' xs))

