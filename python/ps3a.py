# Problem Set 3 - Problem 1
# Author: Felipo Soranz
# Time: 20 minutes

def countSubStringMatch(target, key):
    """Count ocurrences of the string key in the string target iteratively"""
    count = 0
    pos = 0
    while True:
        pos = target.find(key, pos)
        if pos == -1:
            break
        pos += 1
        count += 1
    return count

def countSubStringMatchRecursive(target, key):
    """Count ocurrences of the string key in the string target recursively"""
    pos = target.find(key)
    if pos >= 0:
        return 1 + countSubStringMatchRecursive(target[pos + 1:], key)
    else:
        return 0

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

    for fn in (countSubStringMatch, countSubStringMatchRecursive):
        print("Testing:", fn.__name__)
        for target in (target1, target2):
            print("Target:", target)
            for key in (key10, key11, key12, key13):
                print("Key:", key, "= ", end="")
                pprint(fn(target, key))
