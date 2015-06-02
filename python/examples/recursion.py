def isPalindrome(str):
    """Returns True if str is a palindrome or False otherwise"""
    if len(str) < 2: 
        return True
    elif str[0] == str[-1]:
        return isPalindrome(str[1:-1])
    else:
        return False

# Simpler definition, longer execution and larger space requirements
def fib_r(x):
    """Calculate recursively the fibonacci number x"""
    if x == 0 or x == 1:
        return x;
    elif x >= 2:
        return fib_r(x - 2) + fib_r(x - 1)
    else:
        print("Invalid parameter x =", x)
        return None

# Faster execution, smaller space requirements, more complex code
def fib_i(x):
    """Calculate iteratively the fibonacci number x"""
    if x == 0 or x == 1:
        return x
    elif x >= 2:        
        p1 = 0
        p2 = 1
        result = 0
        for i in range(x-1):
            result = p1 + p2
            p1 = p2
            p2 = result
        return result
    else:
        print("Invalid parameter x =", x)
        return None
        
