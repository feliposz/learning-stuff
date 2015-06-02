# 6.00 Problem Set 8
#
# Intelligent Course Advisor
#
# Name: Felipo Soranz
# Time: 
# 18:18 started
# 18:24 problem 1
# 19:00 problem 2
# 19:17 problem 3



import time

SUBJECT_FILENAME = "subjects.txt"
VALUE, WORK = 0, 1

#
# Problem 1: Building A Subject Dictionary
#
def loadSubjects(filename):
    """
    Returns a dictionary mapping subject name to (value, work), where the name
    is a string and the value and work are integers. The subject information is
    read from the file named by the string filename. Each line of the file
    contains a string of the form "name,value,work".

    returns: dictionary mapping subject name to (value, work)
    """

    subjects = {}
    inputFile = open(filename)
    for line in inputFile:
        course, value, word = line.strip().split(',')
        subjects[course] = (int(value), int(word))
    return subjects

    # TODO: Instead of printing each line, modify the above to parse the name,
    # value, and work of each subject and create a dictionary mapping the name
    # to the (value, work).

def printSubjects(subjects):
    """
    Prints a string containing name, value, and work of each subject in
    the dictionary of subjects and total value and work of all subjects
    """
    totalVal, totalWork = 0,0
    if len(subjects) == 0:
        return 'Empty SubjectList'
    res = 'Course\tValue\tWork\n======\t====\t=====\n'
    subNames = list(subjects.keys())
    subNames.sort()
    for s in subNames:
        val = subjects[s][VALUE]
        work = subjects[s][WORK]
        res = res + s + '\t' + str(val) + '\t' + str(work) + '\n'
        totalVal += val
        totalWork += work
    res = res + '\nTotal Value:\t' + str(totalVal) +'\n'
    res = res + 'Total Work:\t' + str(totalWork) + '\n'
    print(res)

def cmpValue(subInfo1, subInfo2):
    """
    Returns True if value in (value, work) tuple subInfo1 is GREATER than
    value in (value, work) tuple in subInfo2
    """
    val1 = subInfo1[VALUE]
    val2 = subInfo2[VALUE]
    return  val1 > val2

def cmpWork(subInfo1, subInfo2):
    """
    Returns True if work in (value, work) tuple subInfo1 is LESS than than work
    in (value, work) tuple in subInfo2
    """
    work1 = subInfo1[WORK]
    work2 = subInfo2[WORK]
    return  work1 < work2

def cmpRatio(subInfo1, subInfo2):
    """
    Returns True if value/work in (value, work) tuple subInfo1 is 
    GREATER than value/work in (value, work) tuple in subInfo2
    """
    val1 = subInfo1[VALUE]
    val2 = subInfo2[VALUE]
    work1 = subInfo1[WORK]
    work2 = subInfo2[WORK]
    return float(val1) / work1 > float(val2) / work2

#
# Problem 2: Subject Selection By Greedy Optimization
#
def greedyAdvisor(subjects, maxWork, comparator):
    """
    Returns a dictionary mapping subject name to (value, work) which includes
    subjects selected by the algorithm, such that the total work of subjects in
    the dictionary is not greater than maxWork.  The subjects are chosen using
    a greedy algorithm.  The subjects dictionary should not be mutated.

    subjects: dictionary mapping subject name to (value, work)
    maxWork: int >= 0
    comparator: function taking two tuples and returning a bool
    returns: dictionary mapping subject name to (value, work)
    """
    selected = {}
    changed = True
    while changed:
        changed = False
        best = None
        for key in subjects.keys():
            #print("key =", key)
            #print("best =", best)                
            if key in selected:
                continue
            elif subjects[key][WORK] <= maxWork and (best == None or comparator(subjects[key], subjects[best])):
                best = key
                changed = True
                #print("found better: ", best, subjects[best])
        if changed:
            maxWork -= subjects[best][WORK]
            selected[best] = subjects[best]
    
    return selected

# Tests

##smallCatalog = {'6.00': (16, 8), '1.00': (7, 7), '6.01': (5, 3), '15.01': (9, 6)}
##print("cmpValue")
##printSubjects(greedyAdvisor(smallCatalog, 15, cmpValue))
##print("cmpWork")
##printSubjects(greedyAdvisor(smallCatalog, 15, cmpWork))
##print("cmpRatio")
##printSubjects(greedyAdvisor(smallCatalog, 15, cmpRatio))
##
##subjects = loadSubjects(SUBJECT_FILENAME)
##print("cmpValue")
##printSubjects(greedyAdvisor(subjects, 15, cmpValue))
##print("cmpWork")
##printSubjects(greedyAdvisor(subjects, 15, cmpWork))
##print("cmpRatio")
##printSubjects(greedyAdvisor(subjects, 15, cmpRatio))

def bruteForceAdvisor(subjects, maxWork):
    """
    Returns a dictionary mapping subject name to (value, work), which
    represents the globally optimal selection of subjects using a brute force
    algorithm.

    subjects: dictionary mapping subject name to (value, work)
    maxWork: int >= 0
    returns: dictionary mapping subject name to (value, work)
    """
    nameList = list(subjects.keys())
    tupleList = list(subjects.values())
    bestSubset, bestSubsetValue = \
            bruteForceAdvisorHelper(tupleList, maxWork, 0, None, None, [], 0, 0)
    outputSubjects = {}
    for i in bestSubset:
        outputSubjects[nameList[i]] = tupleList[i]
    return outputSubjects

def bruteForceAdvisorHelper(subjects, maxWork, i, bestSubset, bestSubsetValue,
                            subset, subsetValue, subsetWork):
    global num_calls
    num_calls += 1
    # Hit the end of the list.
    if i >= len(subjects):
        if bestSubset == None or subsetValue > bestSubsetValue:
            # Found a new best.
            return subset[:], subsetValue
        else:
            # Keep the current best.
            return bestSubset, bestSubsetValue
    else:
        s = subjects[i]
        # Try including subjects[i] in the current working subset.
        if subsetWork + s[WORK] <= maxWork:
            subset.append(i)
            bestSubset, bestSubsetValue = bruteForceAdvisorHelper(subjects,
                    maxWork, i+1, bestSubset, bestSubsetValue, subset,
                    subsetValue + s[VALUE], subsetWork + s[WORK])
            subset.pop()
        bestSubset, bestSubsetValue = bruteForceAdvisorHelper(subjects,
                maxWork, i+1, bestSubset, bestSubsetValue, subset,
                subsetValue, subsetWork)
        return bestSubset, bestSubsetValue

#
# Problem 3: Subject Selection By Brute Force
#
def bruteForceTime():
    """
    Runs tests on bruteForceAdvisor and measures the time required to compute
    an answer.
    """
    subjects = loadSubjects(SUBJECT_FILENAME)
    for work in range(1, 10):
        start = time.time()
        bruteForceAdvisor(subjects, work)
        elapsed = time.time() - start
        print("Elapsed time for work =", work, " was =", elapsed, "seconds")

# Problem 3 Observations
# ======================
#
# TODO: write here your observations regarding bruteForceTime's performance

#bruteForceTime()
##Elapsed time for work = 1  was = 0.016000032424926758 seconds
##Elapsed time for work = 2  was = 0.03099989891052246 seconds
##Elapsed time for work = 3  was = 0.12400007247924805 seconds
##Elapsed time for work = 4  was = 0.42100000381469727 seconds
##Elapsed time for work = 5  was = 1.2639999389648438 seconds
##Elapsed time for work = 6  was = 3.5879998207092285 seconds
##Elapsed time for work = 7  was = 12.869999885559082 seconds
##Elapsed time for work = 8  was = 34.37399983406067 seconds
##Elapsed time for work = 9  was = 92.40900015830994 seconds

#
# Problem 4: Subject Selection By Dynamic Programming
#

def dpAdvisor(subjects, maxWork):
    """
    Returns a dictionary mapping subject name to (value, work) that contains a
    set of subjects that provides the maximum value without exceeding maxWork.

    subjects: dictionary mapping subject name to (value, work)
    maxWork: int >= 0
    returns: dictionary mapping subject name to (value, work)
    """
    courses = []
    works = []
    values = []
    for key in subjects.keys():
        courses.append(key)
        works.append(subjects[key][0])
        values.append(subjects[key][1])
    memo = {}
    winners = dpAdvisorHelper(works, values, len(values) - 1, maxWork, memo)
    results = {}
    for i in winners:
        results[courses[i]] = (values[i], works[i])
    return results

# TODO: This implementation is incomplete
# The result is not optimal
def dpAdvisorHelper(works, values, i, available_work, memo):
    global num_calls
    num_calls += 1
    
    try:
        return memo[(i, available_work)]
    except KeyError:
        pass
    
    if i == 0:
        if works[i] <= available_work:
            memo[(i, available_work)] = [i]
            return [i]
        else:
            return []

    without_i = dpAdvisorHelper(works, values, i - 1, available_work, memo)
    
    if works[i] > available_work:
        memo[(i, available_work)] = without_i
        return without_i
    else:
        with_i = [i] + dpAdvisorHelper(works, values, i - 1, available_work - works[i], memo)

    if branch_value(with_i, values) >=  branch_value(without_i, values):
        winners = with_i
    else:
        winners = without_i

    memo[(i, available_work)] = winners
    return winners

def branch_value(branch, value):
    total = 0
    for i in branch:
        total += value[i]
    return total
    
##subjects = {'a1': (16, 8), 'b1': (7, 7), 'c1': (5, 3), 'd1': (9, 6)}
##work = 20
##subjects = loadSubjects(SUBJECT_FILENAME)
##work = 5

##print("\n>>> dpAdvisor <<< \n")
##num_calls = 0
##printSubjects(dpAdvisor(subjects, work))
##print("number of calls =", num_calls)
##
##print("\n>>> bruteForceAdvisor <<< \n")
##num_calls = 0
##printSubjects(bruteForceAdvisor(subjects, work))
##print("number of calls =", num_calls)


num_calls = 0

#
# Problem 5: Performance Comparison
#
def dpTime():
    """
    Runs tests on dpAdvisor and measures the time required to compute an
    answer.
    """
    global num_calls
    subjects = loadSubjects(SUBJECT_FILENAME)
    for work in range(5, 100, 10):
        start = time.time()
        num_calls = 0
        result = dpAdvisor(subjects, work)
        #printSubjects(result)
        elapsed = time.time() - start
        print("Elapsed time for work =", work, " was =", elapsed, "seconds")



# Problem 5 Observations
# ======================
#
# TODO: write here your observations regarding dpAdvisor's performance and
# how its performance compares to that of bruteForceAdvisor.

##dpTime()
####Elapsed time for work = 5  was = 0.019999980926513672 seconds
####Elapsed time for work = 15  was = 0.08999991416931152 seconds
####Elapsed time for work = 25  was = 0.15999984741210938 seconds
####Elapsed time for work = 35  was = 0.25999999046325684 seconds
####Elapsed time for work = 45  was = 0.3710000514984131 seconds
####Elapsed time for work = 55  was = 0.49899983406066895 seconds
####Elapsed time for work = 65  was = 0.35899996757507324 seconds
####Elapsed time for work = 75  was = 0.7799999713897705 seconds
####Elapsed time for work = 85  was = 0.9200000762939453 seconds
####Elapsed time for work = 95  was = 1.1349999904632568 seconds


