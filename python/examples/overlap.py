#dynamic programming examples
#using memoization for solving exponential problem
#with overlapping subproblems and optimal structure

#recursive fibonacci
def fib(n):
    global num_calls
    num_calls += 1
    if n <= 1:
        return 1
    else:
        return fib(n-1) + fib(n-2)

#recursive with memoization
memo_fib = {0:1, 1:1}
def fast_fib(n):
    global num_calls, memo_fib
    num_calls += 1
    if not n in memo_fib:
        memo_fib[n] = fast_fib(n-1) + fast_fib(n-2)
    return memo_fib[n]

##n = 30
##
##num_calls = 0
##print("fib of", n, "=", fib(n), "number of calls", num_calls)
##
##num_calls = 0
##print("fast_fib of", n, "=", fast_fib(n), "number of calls", num_calls)

#----------------------------------

#recursive knapsack problem solution
def max_val(weights, values, i, available_weight):
    global num_calls
    num_calls += 1
    #print("max_val", i, available_weight)
    if i == 0:
        if weights[i] <= available_weight:
            return values[i]
        else:
            return 0
    without_i = max_val(weights, values, i - 1, available_weight)
    if weights[i] > available_weight:
        return without_i
    else:
        with_i = values[i] + max_val(weights, values, i - 1, available_weight - weights[i])
    return max(with_i, without_i)

#memoized knapsack problem solution
def fast_max_val(weights, values, i, available_weight, memo):
    global num_calls
    num_calls += 1
    try:
        return memo[(i, available_weight)]
    except KeyError:
        if i == 0:
            if weights[i] <= available_weight:
                memo[(i, available_weight)] = values[i]
                return values[i]
            else:
                memo[(i, available_weight)] = 0
                return 0
        without_i = fast_max_val(weights, values, i - 1, available_weight, memo)
        if weights[i] > available_weight:
            memo[(i, available_weight)] = without_i
            return without_i
        else:
            with_i = values[i] + fast_max_val(weights, values, i - 1, available_weight - weights[i], memo)
        result = max(with_i, without_i)
        memo[(i, available_weight)] = result
        return result

def fmax_val(weights, values, i, available_weight):
    memo = {}
    result = fast_max_val(weights, values, i, available_weight, memo)
    print("size of memo =", len(memo))
    return result

##weights = [1, 5, 3, 4]
##values = [15, 10, 9, 5]
##num_calls = 0
##res = max_val(weights, values, len(values) - 1, 8)
##print("max_val =", res, "number of calls", num_calls)

##weights = [1, 1, 5, 5, 3, 3, 4, 4]
##values = [15, 15, 10, 10, 9, 9, 5, 5]
##num_calls = 0
##res = max_val(weights, values, len(values) - 1, 8)
##print("max_val =", res, "number of calls", num_calls)
##num_calls = 0
##res = fmax_val(weights, values, len(values) - 1, 8)
##print("fmax_val =", res, "number of calls", num_calls)

weights = [5, 5, 1, 8, 2, 9, 7, 5, 2, 8, 1, 2, 7, 3, 5, 7, 8, 5, 5, 8, 2, 3, 8, 4, 9, 1]
values =  [5, 5, 3, 5, 6, 7, 2, 3, 7, 1, 8, 3, 6, 8, 8, 6, 5, 6, 8, 4, 3, 5, 2, 3, 4, 3]
num_calls = 0
res = fmax_val(weights, values, len(values) - 1, 40)
print("fmax_val =", res, "number of calls", num_calls)
#max_val = 75 number of calls 5707495
#fmax_val = 75 number of calls 1540

