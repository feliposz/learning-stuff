/****************************************************************************
 * dictionary.h
 *
 * Computer Science 50
 * Problem Set 6
 *
 * Declares a dictionary's functionality.
 ***************************************************************************/

#ifndef DICTIONARY_H
#define DICTIONARY_H

#include <stdbool.h>


// maximum length for a word
// (e.g., pneumonoultramicroscopicsilicovolcanoconiosis)
#define LENGTH 45

// linked list of words
typedef struct wordentry {
	char *word;
	struct wordentry *next;
} wordentry;

/*
 * Returns true if word is in dictionary else false.
 */

bool check(const char *word);


/*
 * Loads dict into memory.  Returns true if successful else false.
 */

bool load(const char *dict);


/*
 * Returns number of words in dictionary if loaded else 0 if not yet loaded.
 */

unsigned int size(void);


/*
 * Unloads dictionary from memory.  Returns true if successful else false.
 */

bool unload(void);

/*
 * Calculate a hash value for given word
 */

unsigned int hash(char word[]);

/*
 * Insert a word in the dictionary hash table
 */

bool insert(char word[]);

#endif // DICTIONARY_H
