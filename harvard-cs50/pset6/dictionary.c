/****************************************************************************
 * dictionary.c
 *
 * Computer Science 50
 * Problem Set 6
 *
 * Implements a dictionary's functionality.
 ***************************************************************************/

#include <stdbool.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <ctype.h>
#include "dictionary.h"



// hash table of entries pointing to some linked lists
#define MAX (256*256)
wordentry *wordhash[MAX];

// debug table used to keep track of size of lists
int table[MAX];

// words read from dictionary
int wordsread = 0;

/*
 * Returns true if word is in dictionary else false.
 */

bool
check(const char *word)
{
	// convert word to lowercase
	char myword[LENGTH];
	int n = strlen(word);
	for (int i = 0; i < n; i++) {
		myword[i] = tolower(word[i]);
	}
	myword[n] = '\0';

	// calculate hash for lowercase word
	int h = hash(myword) % MAX;

	// get first pointer
	wordentry *ptr = wordhash[h];

	// walk through the list until word is found
	while (ptr != NULL) {
		if (strcmp(ptr->word, myword) == 0) { // found!
			return true;
		}
		ptr = ptr->next;
	}

	// not found
    return false;
}

/*
 * Calculate a hash value for given word
 */

unsigned int hash(char word[])
{
	unsigned int h = 11;
	for (int i = 0; word[i] != '\n' && word[i] != '\0'; i++) {
		h = h * 31 + word[i];
	}
	return h;
}

/*
 * Insert a word in the dictionary hash table
 */

bool insert(char word[])
{
	// allocate memory for wordentry
	wordentry *we = malloc(sizeof(wordentry));
	if (we == NULL) {
		return false;
	}
	// allocate memory for string
	we->word = malloc(sizeof(char) * (strlen(word) + 1));
	if (we->word == NULL) {
		free(we);
		return false;
	}
	// initialize data in wordentry
	strcpy(we->word, word);
	we->next = NULL;

	// get hash for this word
	int h = hash(word) % MAX;

	// insert node in the head of the list
	if (wordhash[h] != NULL) {
		we->next = wordhash[h];
	}
	wordhash[h] = we;

	return true;
}

/*
 * Loads dict into memory.  Returns true if successful else false.
 */

bool
load(const char *dict)
{
	// open dictionary file for read
    FILE *fp = fopen(dict, "r");
    if (fp == NULL)
    	return false;

    // initialize hash table
    for (int i = 0; i < MAX; i++) {
    	table[i] = 0;
    	wordhash[i] = NULL;
    }

    // read a word from dictionary and insert into hash table
    char buffer[LENGTH+1];
    while (fgets(buffer, LENGTH+1, fp) != NULL) {
    	buffer[strlen(buffer) - 1] = '\0'; // remove annoying \n from input
    	if (!insert(buffer))
    		return false;
    	wordsread++;

//    	int h = hash(buffer) % MAX; // used for debug below
//    	table[h]++;
    }

    // close dictionary file
    fclose(fp);

/*
    // create and print a table of number of repeated hash entries
    int entries[15];
    for (int n = 0; n < 15; n++)
    	entries[n] = 0;
    for (int i = 0; i < MAX; i++) {
    	int n = table[i];
    	if (n >= 15) n = 14;
    	entries[n]++;
    }
    printf("repeated hashes:\n");
    for (int n = 0; n < 15; n++) {
    	printf("[%d] = %d\n", n, entries[n]);
    }
*/
    return true;
}


/*
 * Returns number of words in dictionary if loaded else 0 if not yet loaded.
 */

unsigned int
size(void)
{
	// TODO
    return wordsread;
}


/*
 * Unloads dictionary from memory.  Returns true if successful else false.
 */

bool
unload(void)
{
	// go for each entry in hashtable
	for (int i = 0; i < MAX; i++) {
		wordentry *ptr = wordhash[i];

		// walk down the list freeing all nodes and strings
		while (ptr != NULL) {
			wordentry *next = ptr->next;
			free(ptr->word);
			free(ptr);
			ptr = next;
		}
	}

    return true;
}
