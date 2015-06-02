# Problem Set 4
# Name: Felipo Soranz
# Collaborators: 
# Time: Problem 1 - 13 minutes
#       Problem 2 -  7 minutes
#       Problem 3 -  5 minutes
#       Problem 4 - 15 minutes
#       TOTAL====== 40 minutes

#
# Problem 1
#

def nestEggFixed(salary, save, growthRate, years):
    """
    - salary: the amount of money you make each year.
    - save: the percent of your salary to save in the investment account each
      year (an integer between 0 and 100).
    - growthRate: the annual percent increase in your investment account (an
      integer between 0 and 100).
    - years: the number of years to work.
    - return: a list whose values are the size of your retirement account at
      the end of each year.
    """
    # TODO: Your code here.
    assert salary >= 0, "salary should be positive"
    assert save >= 0, "save should be positive"
    assert growthRate >= 0, "growthRate should be positive"
    assert years >= 0, "years should be positive"
    result = [ ]
    for i in range(years):
        if i == 0:
            account = salary * save * 0.01
        else:
            account = account * (1 + 0.01 * growthRate) + salary * save * 0.01;
        result.append(account)
    return result
            

def testNestEggFixed():
    salary     = 10000
    save       = 10
    growthRate = 15
    years      = 5
    savingsRecord = nestEggFixed(salary, save, growthRate, years)
    print(", ".join([format(x, ".2f") for x in savingsRecord]))
    # Output should have values close to:
    # [1000.0, 2150.0, 3472.5, 4993.375, 6742.3812499999995]

    # TODO: Add more test cases here.
    salary     = 20000
    save       = 3
    growthRate = 11
    years      = 6
    savingsRecord = nestEggFixed(salary, save, growthRate, years)
    print(", ".join([format(x, ".2f") for x in savingsRecord]))

#
# Problem 2
#

def nestEggVariable(salary, save, growthRates):
    """
    - salary: the amount of money you make each year.
    - save: the percent of your salary to save in the investment account each
      year (an integer between 0 and 100).
    - growthRate: a list of the annual percent increases in your investment
      account (integers between 0 and 100).
    - return: a list of your retirement account value at the end of each year.
    """
    # TODO: Your code here.
    assert salary >= 0, "salary should be positive"
    assert save >= 0, "save should be positive"
    assert isinstance(growthRates, list), "growthRates should be a list"
    assert len(growthRates) > 0, "growthRates should have more than one value"
    result = []
    for i in range(len(growthRates)):
        if i == 0:
            account = salary * save * 0.01
        else:
            assert isinstance(growthRates[i], float) or isinstance(growthRates[i], int), "growthRates values should be numbers"
            account = account * (1 + 0.01 * growthRates[i]) + salary * save * 0.01;
        result.append(account)
    return result
    
    
def testNestEggVariable():
    salary      = 10000
    save        = 10
    growthRates = [3, 4, 5, 0, 3]
    savingsRecord = nestEggVariable(salary, save, growthRates)
    print(", ".join([format(x, ".2f") for x in savingsRecord]))
    # Output should have values close to:
    # [1000.0, 2040.0, 3142.0, 4142.0, 5266.2600000000002]

    # TODO: Add more test cases here.
    salary      = 20000
    save        = 5
    growthRates = [0, 10, 5, 0, -5, -10]
    savingsRecord = nestEggVariable(salary, save, growthRates)
    print(", ".join([format(x, ".2f") for x in savingsRecord]))

#
# Problem 3
#

def postRetirement(savings, growthRates, expenses):
    """
    - savings: the initial amount of money in your savings account.
    - growthRate: a list of the annual percent increases in your investment
      account (an integer between 0 and 100).
    - expenses: the amount of money you plan to spend each year during
      retirement.
    - return: a list of your retirement account value at the end of each year.
    """
    # TODO: Your code here.
    assert savings >= 0, "savings should be positive"
    assert expenses > 0, "expenses shouldn't be negative"
    assert isinstance(growthRates, list), "growthRates should be a list"
    assert len(growthRates) > 0, "growthRates should have more than one value"
    result = []
    for i in range(len(growthRates)):
        assert isinstance(growthRates[i], float) or isinstance(growthRates[i], int), "growthRates values should be numbers"
        if i == 0:
            account = savings * (1 + 0.01 * growthRates[i]) - expenses
        else:
            account = account * (1 + 0.01 * growthRates[i]) - expenses;
        result.append(account)
    return result

def testPostRetirement():
    savings     = 100000
    growthRates = [10, 5, 0, 5, 1]
    expenses    = 30000
    savingsRecord = postRetirement(savings, growthRates, expenses)
    print(savingsRecord)
    # Output should have values close to:
    # [80000.000000000015, 54000.000000000015, 24000.000000000015,
    # -4799.9999999999854, -34847.999999999985]

    # TODO: Add more test cases here.
    savings     = 250000
    growthRates = [3, 4, 5, 0, 3, -3, 10]
    expenses    = 50000
    savingsRecord = postRetirement(savings, growthRates, expenses)
    print(savingsRecord)

#
# Problem 4
#

def findMaxExpenses(salary, save, preRetireGrowthRates, postRetireGrowthRates,
                    epsilon):
    """
    - salary: the amount of money you make each year.
    - save: the percent of your salary to save in the investment account each
      year (an integer between 0 and 100).
    - preRetireGrowthRates: a list of annual growth percentages on investments
      while you are still working.
    - postRetireGrowthRates: a list of annual growth percentages on investments
      while you are retired.
    - epsilon: an upper bound on the absolute value of the amount remaining in
      the investment fund at the end of retirement.
    """
    # TODO: Your code here.
    assert epsilon > 0, "espilon should be positive"
    savings = nestEggVariable(salary, save, preRetireGrowthRates)
    retirementSavings = savings[-1]
    print ("retirementSavings:", retirementSavings)
    low = 0
    high = retirementSavings
    expenses = (low + high) / 2
    savings = postRetirement(retirementSavings, postRetireGrowthRates, expenses)
    lastSavings = savings[-1]
    while abs(lastSavings) > epsilon:
        #print("low: %.2f high: %.2f expenses: %.2f lastSavings: %.2f" % (low, high, expenses, lastSavings))
        print ("expenses:", expenses)
        if lastSavings > 0:
            low = expenses
        else:
            high = expenses
        expenses = (low + high) / 2
        savings = postRetirement(retirementSavings, postRetireGrowthRates, expenses)
        lastSavings = savings[-1]
    #print("low: %.2f high: %.2f expenses: %.2f lastSavings: %.2f" % (low, high, expenses, lastSavings))
    return expenses
    

def testFindMaxExpenses():
    salary                = 10000
    save                  = 10
    preRetireGrowthRates  = [3, 4, 5, 0, 3]
    postRetireGrowthRates = [10, 5, 0, 5, 1]
    epsilon               = .01
    expenses = findMaxExpenses(salary, save, preRetireGrowthRates,
                               postRetireGrowthRates, epsilon)
    print(expenses)
    # Output should have a value close to:
    # 1229.95548986

    # TODO: Add more test cases here.
    salary                = 20000
    save                  = 5
    preRetireGrowthRates  = [0, 10, 5, 0, -5, -10]
    postRetireGrowthRates = [3, 4, 5, 0, 3, -3, 10]
    epsilon               = 1
    expenses = findMaxExpenses(salary, save, preRetireGrowthRates,
                               postRetireGrowthRates, epsilon)
    print(expenses)
    
