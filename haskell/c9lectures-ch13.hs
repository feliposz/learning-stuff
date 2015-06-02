
isZero 0 = True
isZero n
	| n /= 0 = False

data Nat = Zero | Succ Nat

add Zero m = m
add (Succ n) m = Succ (add n m)

{-


add n Zero = n
Succ (add n Zero)
Succ n


x = Zero

add x (add y z) = add (add x y) z
add Zero (add y z) = add (add Zero y) z
add y z = add y z

-}

slowReverse [] = []
slowReverse (x:xs) = slowReverse xs ++ [x]

fastReverse xs = reverseHelper xs []
	where 
		reverseHelper [] ys = ys 
		reverseHelper (x:xs) ys = reverseHelper xs (x:ys)

{-

fastReverse [1,2,3]
reverseHelper [1,2,3] []
reverseHelper [2,3] [1]
reverseHelper [3] [2,1]
reverseHelper [] [3,2,1]
[3,2,1]

-}
