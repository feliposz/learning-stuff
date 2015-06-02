# Problem Set 1 - Problem 2
# Name: Felipo Soranz
# Time: 11 minutes (used Problem 1 as base)

from math import log

# Largest number
n = 10000

# The sum of the natural logarithms of primes smaller than n
# Start with the log of 2 
sum_of_logs = log(2)

# Start checking primes with 3
number = 3

while number <= n:

    # No need to check for < 3 (only testing odd numbers)
    #  or anything > half of it's value
    is_prime = True
    for divisor in range(3, int(number / 2)):
        if number % divisor == 0:
            is_prime = False
            break

    if is_prime:
        sum_of_logs += log(number)

    # Check next odd number (started with 3)
    number += 2

# Ratio aproximate to 1 for large values of n
ratio = sum_of_logs / n

# Print results
print("sum of logs of primes =", sum_of_logs)
print("n =", n)
print("ratio(sum/n) =", ratio)
