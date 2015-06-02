# Problem Set 5: Ghost
# Name: 
# Collaborators: 
# Time: 
#

import random

# -----------------------------------
# Helper code
# (you don't need to understand this helper code)
import string

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

# Actually load the dictionary of words and point to it with 
# the wordlist variable so that it can be accessed from anywhere
# in the program.
wordlist = load_words()

# TO DO: your code begins here!
#
# Build data structures used for entire session and play game
#

# Constants returned by check_fragment
OK = 0
WORD = 1
INVALID = 2

# Remember last position where fragment was found in the sorted
# wordlist to speed up searching for the next fragment.
wordlist.sort()
last_position = 0

def check_fragment(fragment):
    global last_position
    for pos in range(last_position, len(wordlist)):
        if fragment == wordlist[pos][:len(fragment)]:
            if fragment == wordlist[pos]:
                last_position = pos
                return WORD
            else:
                last_position = pos
                return OK
    return INVALID

def get_letter(player):
    while True:
        letter = input("Player " + str(player) + " says letter: ")
        letter = letter.lower()
        if len(letter) == 1 and letter in string.ascii_lowercase:
            return letter
        print("Please, enter a valid letter.")

def play_game():

    player = 1
    fragment = ""

    print("Welcome to Ghost!")
    print("Player", str(player), "goes first.")
    print("Current word fragment: '" + fragment.upper() + "'")

    while True:
        
        fragment += get_letter(player)

        print("\nCurrent word fragment: '" + fragment.upper() + "'")

        result = check_fragment(fragment)
        if result == WORD:
            print("Player", player, "loses because '" + fragment.upper() + "' is a word!")
            break
        elif result == INVALID:
            print("Player", player, "loses because no word begins with '" + fragment + "'!")
            break
        
        if player == 1:
            player = 2
        else:
            player = 1
        
    if player == 1:
        winner = 2
    else:
        winner = 1

    print("Player", winner, "wins!")    

if __name__ == '__main__':
    play_game()

