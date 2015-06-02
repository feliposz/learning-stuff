from itertools import product, permutations

numbers = ['1','3','7','10','25','50']
operators = ['+', '-', '*', '//']

#results = {}

for size in range(2, 7):
    for ops in product(operators, repeat=(size-1)):
        for nums in permutations(numbers, size):
            expr = ""
            for i in range(0, size-1):
                expr += nums[i] + ops[i]
            expr += nums[-1]
            value = eval(expr)
            #if int(value) > 0:
                #results[value] = results.get(value, []) + [expr]
            if int(value) == 999:
                print(expr, '=', value)
print("done.")

