--type Parser a = String -> Tree
--type Parser a = String -> Maybe (Tree, String)
--data Maybe a = Nothing | Just a
type Parser a = String -> [(a,String)] -- using a list for convenience


item :: Parser Char
item = \inp -> case inp of
                    [] -> []
                    (x:xs) -> [(x, xs)]

failure :: Parser a
failure = \inp -> []

return' :: a -> Parser a
return' v = \inp -> [(v, inp)]

(+++) :: Parser a -> Parser a -> Parser a
p +++ q = \inp -> case p inp of
                    [] -> parse q inp
                    [(v,out)] -> [(v,out)]

parse :: Parser a -> String -> [(a,String)]
parse p inp = p inp -- Just to make it more explicity, but this is really the identity function

-- parse item "" --> []
-- parse item "abc" --> [('a', "bc")]
-- parse (return' 1) "abc" --> [(1, "abc")]
-- parse failure "abc" --> []
-- parse (item +++ return' 'd') "abc" --> [('d', "bc")]
-- parse (failure +++ return' 'd') "abc" --> [('d', "abc")]

