# Problem Set 6: 6.00 Word Game
# Name: Felipo Soranz
# Time: 
#       Problem 1  25 minutes
#       Problem 2  20 minutes
#       Problem 3  2 hours 20 minutes
#       Problem 4  1 hour 20 minutes
#       Cleaning up code 30 minutes
#       Problem 5  30 minutes
#       Total =====> 5 hours 25 minutes

import random
import string
import time

VOWELS = 'aeiou'
CONSONANTS = 'bcdfghjklmnpqrstvwxyz'
HAND_SIZE = 7

SCRABBLE_LETTER_VALUES = {
    'a': 1, 'b': 3, 'c': 3, 'd': 2, 'e': 1, 'f': 4, 'g': 2, 'h': 4, 'i': 1, 'j': 8, 'k': 5, 'l': 1, 'm': 3, 'n': 1, 'o': 1, 'p': 3, 'q': 10, 'r': 1, 's': 1, 't': 1, 'u': 1, 'v': 4, 'w': 4, 'x': 8, 'y': 4, 'z': 10
}

word_list = []
points_dict = {}
time_limit = 0
rearrange_dict = {}

# -----------------------------------
# Helper code
# (you don't need to understand this helper code)

WORDLIST_FILENAME = "words.txt"

def load_words():
    """
    Returns a list of valid words. Words are strings of lowercase letters.

    Depending on the size of the word list, this function may
    take a while to finish.
    """
    print("Loading word list from file...")
    # inFile: file
    inFile = open(WORDLIST_FILENAME, 'r', 1)
    # wordlist: list of strings
    wordlist = []
    for line in inFile:
        wordlist.append(line.strip().lower())
    print("  ", len(wordlist), "words loaded.")
    return wordlist

def get_frequency_dict(sequence):
    """
    Returns a dictionary where the keys are elements of the sequence
    and the values are integer counts, for the number of times that
    an element is repeated in the sequence.

    sequence: string or list
    return: dictionary
    """
    # freqs: dictionary (element_type -> int)
    freq = {}
    for x in sequence:
        freq[x] = freq.get(x,0) + 1
    return freq


# (end of helper code)
# -----------------------------------

#
# Problem Set 5 - Problem #1: Scoring a word
#
def get_word_score(word, n):
    """
    Returns the score for a word. Assumes the word is a
    valid word.

    The score for a word is the sum of the points for letters
    in the word, plus 50 points if all n letters are used on
    the first go.

    Letters are scored as in Scrabble; A is worth 1, B is
    worth 3, C is worth 3, D is worth 2, E is worth 1, and so on.

    word: string (lowercase letters)
    returns: int >= 0
    """
    if len(word) == n:
        score = 50
    else:
        score =0

    for letter in word:
        score += SCRABBLE_LETTER_VALUES[letter]

    return score

#
# Make sure you understand how this function works and what it does!
#
def display_hand(hand):
    """
    Displays the letters currently in the hand.

    For example:
       display_hand({'a':1, 'x':2, 'l':3, 'e':1})
    Should print out something like:
       a x x l l l e
    The order of the letters is unimportant.

    hand: dictionary (string -> int)
    """
    for letter in hand.keys():
        for j in range(hand[letter]):
            print(letter, end=" ") # print all on the same line
    print()                                # print an empty line

#
# Make sure you understand how this function works and what it does!
#
def deal_hand(n):
    """
    Returns a random hand containing n lowercase letters.
    At least n/3 the letters in the hand should be VOWELS.

    Hands are represented as dictionaries. The keys are
    letters and the values are the number of times the
    particular letter is repeated in that hand.

    n: int >= 0
    returns: dictionary (string -> int)
    """
    hand={}
    num_vowels = n // 3 #intended integer division

    for i in range(num_vowels):
        x = VOWELS[random.randrange(0,len(VOWELS))]
        hand[x] = hand.get(x, 0) + 1

    for i in range(num_vowels, n):
        x = CONSONANTS[random.randrange(0,len(CONSONANTS))]
        hand[x] = hand.get(x, 0) + 1

    return hand

#
# Problem Set 5 - Problem #2: Update a hand by removing letters
#
def update_hand(hand, word):
    """
    Assumes that 'hand' has all the letters in word.
    In other words, this assumes that however many times
    a letter appears in 'word', 'hand' has at least as
    many of that letter in it.

    Updates the hand: uses up the letters in the given word
    and returns the new hand, without those letters in it.

    Has no side effects: does not mutate hand.

    word: string
    hand: dictionary (string -> int)
    returns: dictionary (string -> int)
    """
    hand2 = hand.copy()
    for letter in word:
        hand2[letter] -= 1
        if hand2[letter] == 0:
            del(hand2[letter])
    return hand2

#
# Problem Set 5 - Problem #3: Test word validity
#
def is_valid_word(word, hand, points_dict):
    """
    Returns True if word is in the word_list and is entirely
    composed of letters in the hand. Otherwise, returns False.
    Does not mutate hand or word_list.

    word: string
    hand: dictionary (string -> int)
    word_list: list of lowercase strings
    """
    letter_counts = get_frequency_dict(word)
    for letter in letter_counts.keys():
        if letter_counts[letter] > hand.get(letter, 0):
            return False
##    for w in word_list:
##        if w == word:
##            return True
##    return False
    return points_dict.get(word, 0) > 0

#
# Problem Set 5 - Problem #4: Playing a hand
# Altered for Problem Set 6
#
def play_hand(hand, word_list):
    """
    Allows the user to play the given hand, as follows:

    * The hand is displayed.

    * The user may input a word.

    * An invalid word is rejected, and a message is displayed asking
      the user to choose another word.

    * When a valid word is entered, it uses up letters from the hand.

    * After every valid word: the score for that word and the total
      score so far are displayed, the remaining letters in the hand
      are displayed, and the user is asked to input another word.

    * The sum of the word scores is displayed when the hand finishes.

    * The hand finishes when there are no more unused letters.
      The user can also finish playing the hand by inputing a single
      period (the string '.') instead of a word.

    * The final score is displayed.

      hand: dictionary (string -> int)
      word_list: list of lowercase strings
    """
    current_hand = hand.copy()
    total_score = 0
    global time_limit
    allowed_time = time_limit
    remaining_time = allowed_time
    while len(current_hand) > 0:
        print("Current hand:", end=" ")
        display_hand(current_hand)
        word = ""
        start_time = time.time()
        while True:
            #word = input("Enter a word, or a . to indicate that you are finished: ")
            
            word = pick_best_word_faster(current_hand, points_dict)
##            current_hand_size = len(hand2list(current_hand))
##            if (current_hand_size <= 7):
##                print("DEBUG: current_hand size is", current_hand_size,". Using pick_best_word")            
##                word = pick_best_word(current_hand, points_dict)
##            else:
##                print("DEBUG: current_hand size is", current_hand_size,". Using pick_first_word")
##                word = pick_first_word(current_hand, points_dict)            
            if is_valid_word(word, current_hand, points_dict) or word == ".":
                break
            else:
                print("Invalid word, please try again.")
        if word == ".":
            break
        end_time = time.time()
        elapsed_time = end_time - start_time
        remaining_time -= elapsed_time
        if remaining_time <= 0.0:
            break
        print("It took %0.2f seconds to provide an answer." % (elapsed_time,))
        print("You have %0.2f seconds remaining." % (remaining_time,))
        if elapsed_time <= 1.0:
            elapsed_time = 1.0
        score = get_word_score(word, len(hand)) / elapsed_time
        total_score += score
        print("%s earned %0.2f points. Total: %0.2f" % (word, score, total_score))
        current_hand = update_hand(current_hand, word)
    if (remaining_time <= 0.0):
        print("Total time exceeds %0.2f seconds. " % (allowed_time,), end="")
    print("Total score: %0.2f points." % total_score)

#
# Problem Set 5 - Problem #5: Playing a game
# Make sure you understand how this code works!
#
def play_game():
    """
    Allow the user to play an arbitrary number of hands.

    * Asks the user to input 'n' or 'r' or 'e'.

    * If the user inputs 'n', let the user play a new (random) hand.
      When done playing the hand, ask the 'n' or 'e' question again.

    * If the user inputs 'r', let the user play the last hand again.

    * If the user inputs 'e', exit the game.

    * If the user inputs anything else, ask them again.
    """
    hand = deal_hand(HAND_SIZE) # random init
    while True:
        cmd = input('Enter n to deal a new hand, r to replay the last hand, or e to end game: ')
        if cmd == 'n':
            hand = deal_hand(HAND_SIZE)
            play_hand(hand.copy(), word_list)
            print()
        elif cmd == 'r':
            play_hand(hand.copy(), word_list)
            print()
        elif cmd == 'e':
            break
        else:
            print("Invalid command.")

#
# Problem Set 6, Problem 3
def pick_best_word(hand, points_dict):
    """
    Return the highest scoring word from points_dict that can be made with the given hand.

    Return '.' if no words can be made with the given hand.
    """
##    s = time.time()

    # This part is roughly O(n!)
    combinations = combine_list2(hand2list(hand))
    
##    combtime = time.time() - s
##    s = time.time()
##    combinations2 = combine_list2(hand2list(hand))
##    combtime2 = time.time() - s
##
##    print (combtime, combtime2)

    #HACK: remove duplicates
    #TODO: find a better way to generate combinations without duplicates


    # This part is O(n)
    dict_comb = {}
    for word in combinations:
        dict_comb[word] = 1
    combinations = list(dict_comb.keys())

##    dict_comb = {}
##    for word in combinations2:
##        dict_comb[word] = 1
##    combinations2 = list(dict_comb.keys())

##    print(len(combinations) - 1, len(combinations2))
    
    #print("DEBUG: Total combinations =", len(combinations))

    # This part is O(n)
    best_word = ""
    best_points = 0
    for word in combinations:
        points = points_dict.get(word, 0)
        #if points > 0:
        #    print("DEBUG: Valid word =", word, " points=", points)
        if points > best_points:
            #print("DEBUG: Best word =", word, " points=", points)
            best_word = word
            best_points = points

    if best_points > 0:
        return best_word
    
    return "."

def hand2list(hand):
    result = []
    for letter in list(hand.keys()):
        for n in range(hand[letter]):
            result.append(letter)
    return result

# Unfortunatelly, this generates duplicate combinations :(
# Perhaps it could run faster if it didn't?
# TODO: Try dynamic programming?
# Also, uses a lot of memory for large hand sizes

# Order of growth = O(n!) where n = len(hand_list) for both time and space
def combine_list(hand_list):
    """
    Returns a list with every possible combination from hand in any order.
    NOTE: Returns duplicates!
    """
    result = []
    if len(hand_list) == 0:
        return [""]
    # Combine each letter from hand with the combination
    # of the rest of the letters recursively
    for first in hand_list:
        rest = hand_list[:]
        rest.remove(first)
        combinations = combine_list(rest)
        for combination in combinations:
            result.append(first + combination)
        # append also the sub-combinations
        result += combinations
    return result

# This version produces a list that is smaller than the other and has
# no repetitions (except when letters are repeated) but it's slower

# Order of growth = O(n!) where n = len(hand_list) for both time and space
def combine_list2(hand_list):
    """
    Returns a list with every possible combination from hand in any order.
    NOTE: Returns duplicates!
    """    
    words = hand_list
    while True:
        new_words = []

        # Combine each word with a letter from the hand
        for word in words:
           
            # Do not combine letters already in the word
            rest_hand = hand_list[:]
            for letter in word:
                if letter in rest_hand:
                    rest_hand.remove(letter)

            if len(rest_hand) == 0:
                print("DEBUG: empty hand")
                return words

            for letter in rest_hand:
                new_words.append(word + letter)
       
        words = words + new_words


# This is also O(n!) on worst case (no word found)
def pick_first_word(hand, points_dict):
    """
    Return the first valid word combination with the letters from hand
    """
    hand_list = hand2list(hand)

    # Start with every letter from the hand as a candidate word
    words = hand_list
    while True:
        new_words = []

        # Combine each current word with a letter from the hand
        for word in words:
            
            # Do not combine letters already in the word
            rest_hand = hand_list[:]
            for letter in word:
                if letter in rest_hand:
                    rest_hand.remove(letter)

            # Have all letters from the hand been used?
            if len(rest_hand) == 0: # should have found so far?
                print("DEBUG: empty hand")
                return "."

            # Try combining each letter from hand with current word
            for letter in rest_hand:
                new_word = word + letter
                # Return first combination found
                if (points_dict.get(new_word,0) > 0):
                    return new_word
                new_words.append(new_word)

        # Could not generate any word?
        if len(new_words) == 0: # should have found so far?
            print("DEBUG: no new words")
            return "."
       
        words = words + new_words
    

def get_words_to_points(word_list):
    """
    Return a dict that maps every word in word_list to its point value.
    """
    points_dict = {}
    for word in word_list:
        points = get_word_score(word, HAND_SIZE)
        points_dict[word] = points
    return points_dict

def get_time_limit(points_dict, k):
    """
    Return the time limit for the computer player as a function of the
    multiplier k.

    points_dict should be the same dictionary that is created by
    get_words_to_points.
    """
    start_time = time.time()
    # Do some computation. The only purpose of the computation is so we can
    # figure out how long your computer takes to perform a known task.
    for word in points_dict:
        get_frequency_dict(word)
        get_word_score(word, HAND_SIZE)
    end_time = time.time()
    return (end_time - start_time) * k


#
# Problem Set 6, Problem 4
def pick_best_word_faster(hand, rearrange_dict):
    """
    Return the highest scoring word from points_dict that can be made with the given hand.

    Return '.' if no words can be made with the given hand.
    """
    best_word = ""
    best_points = 0

    # Get a list of all possible hand subsets and transform them on
    # a list of strings to use as keys when fetching rearrange_dict
    hand_list = hand2list(hand)
    hand_list.sort()
    words = []    
    for subset in hand_subsets(hand_list):
        word = "".join(subset)
        words.append(word)

    # This part is O(n) where n = len(words)

    # Check every combination and find the highest scoring word
    for word in words:
        possible_word = rearrange_dict.get(word, "")
        if possible_word != "":
            points = points_dict.get(word, 0)
            #if points > 0:
            #    print("DEBUG: Valid word =", word, " points=", points)
            if points > best_points:
                #print("DEBUG: Best word =", word, " points=", points)
                best_word = word
                best_points = points

    if best_points > 0:
        return best_word

    # No word found    
    return "."

def hand_subsets(hand_list):
    """
    Get a list of all possible subsets of a hand.
    """
    return rest_of_subsets([], hand_list)

# This is O(2^n)
def rest_of_subsets(good, remaining):
    """
    Return a list of all possible subsets of "remaining" combined with "good".
    """
    answers = []
    for j in range(len(remaining)):
        nextsub = good + remaining[j:j+1]
        answers.append(nextsub)
        answers += rest_of_subsets(nextsub, remaining[j+1:])
    return answers

def get_word_rearrangements(word_list):
    """
    Return a dictionary of words with the key being the letters from the word in a sorted order.
    """
    rearrange_dict = {}
    for word in word_list:
        letters = list(word)
        letters.sort()
        sorted_letters = "".join(letters)
        # It doesn't matter if there are duplicates,
        # since both words will have the same score.
        rearrange_dict[sorted_letters] = word


# Problem 5 - Order of growth
#
# get_best_word is O(n!)
# pick_first_word is also O(n!) in worst case (has to try all possible permutations)
# get_best_word_faster is O(2^n)

#
# Build data structures used for entire session and play game
#
if __name__ == '__main__':
    word_list = load_words()
    points_dict = get_words_to_points(word_list)
    rearrange_dict = get_word_rearrangements(word_list)
    time_limit = get_time_limit(points_dict, 2.0) # enough for 7 letters?
    print("DEBUG time_limit =", time_limit)
    play_game()

##combine_list2(['a','a', 'c'])

