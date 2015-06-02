/*
 * File: HangmanLexicon.java
 * -------------------------
 * This file contains a stub implementation of the HangmanLexicon
 * class that you will reimplement for Part III of the assignment.
 */

import java.io.*;
import java.util.*;
import acm.util.*;

public class HangmanLexicon {

	private ArrayList<String> wordList = new ArrayList<String>();
	
/** Returns the number of words in the lexicon. */
	public int getWordCount() {
		return wordList.size();
	}

/** Returns the word at the specified index. */
	public String getWord(int index) {
		if (index < 0 || index > getWordCount())
			throw new ErrorException("getWord: Illegal index");
		return wordList.get(index);
	};
	
	public HangmanLexicon() {
		try {
			BufferedReader rd = new BufferedReader(new FileReader("ShorterLexicon.txt"));
			while (true) {
				String line = rd.readLine();
				if (line == null) break;
				wordList.add(line);
			}
			rd.close();
		} catch (IOException ex) {
			// Bad... bad... bad!
		}
	}		
}
