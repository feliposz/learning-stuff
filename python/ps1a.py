# Problem Set 1 - Problem 1
# Name: Felipo Soranz
# Time: 56 minutes (with interruptions ~ 15-20 min)

# Start looking for primes with 3
number = 3

# Start with 1 prime counted (2 is a prime)
count_primes = 1

while count_primes < 1000:

    # No need to check for < 3 (only testing odd numbers)
    #  or anything > half of it's value
    is_prime = True
    for divisor in range(3, int(number/2)):
        if number % divisor == 0:
            is_prime = False
            break

    if is_prime:
        count_primes += 1

    # Check next odd number (started with 3)
    number += 2

# Value found (number - 2 to compensate for last increment)
print("found #", count_primes, "prime =", number - 2)
