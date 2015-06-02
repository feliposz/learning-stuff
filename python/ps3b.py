# Problem Set 3 - Problem 2
# Author: Felipo Soranz
# Time: 20 minutes

def subStringMatchExact(target, key):
    """Return a list of positions where string key was found \
    inside of the string target. (Method: iteration)"""
    pos = 0
    result = ()
    while True:
        pos = target.find(key, pos)
        if pos == -1:
            break
        result += (pos,)
        pos += 1
    return result

def subStringMatchExactRecursive(target, key, pos=0):
    """Return a list of positions where string key was found \
    inside of the string target. (Method: recursion)"""
    pos = target.find(key, pos)
    if pos >= 0:
        result = subStringMatchExactRecursive(target, key, pos+1)
        if result != None:
            return (pos,) + result
        else:
            return (pos,)
    return None

def testMe():
    """Test functions"""
    #  target strings

    target1 = 'atgacatgcacaagtatgcat'
    target2 = 'atgaatgcatggatgtaaatgcag'

    # key strings

    key10 = 'a'
    key11 = 'atg'
    key12 = 'atgc'
    key13 = 'atgca'

    from pprint import pprint

    for fn in (subStringMatchExact, subStringMatchExactRecursive):
        print("Testing:", fn.__name__)
        for target in (target1, target2):
            print("Target:", target)
            for key in (key10, key11, key12, key13):
                print("Key:", key, "= ", end="")
                pprint(fn(target, key))

testMe()
