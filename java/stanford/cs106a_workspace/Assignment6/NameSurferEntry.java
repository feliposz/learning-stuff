/*
 * File: NameSurferEntry.java
 * --------------------------
 * This class represents a single entry in the database.  Each
 * NameSurferEntry contains a name and a list giving the popularity
 * of that name for each decade stretching back to 1900.
 */

import acm.util.*;
import java.util.*;

public class NameSurferEntry implements NameSurferConstants {

	private String name;
	private int[] rank;
	            
/* Constructor: NameSurferEntry(line) */
/**
 * Creates a new NameSurferEntry from a data line as it appears
 * in the data file.  Each line begins with the name, which is
 * followed by integers giving the rank of that name for each
 * decade.
 */
	public NameSurferEntry(String line) {
		// You fill this in //
		rank = new int[NDECADES];
		
		int nameStart = 0;
		int nameEnd = line.indexOf(" ");
		
		name = line.substring(nameStart, nameEnd);

		int numStart = nameEnd + 1;
		int numEnd;
		
		for (int i = 0; i < rank.length; i++) {
			if (i == rank.length - 1) { // last number in string
				numEnd = line.length();
			} else {
				numEnd = line.indexOf(" ", numStart + 1);
			}
			//System.out.println("DEBUG: [" + line.substring(numStart, numEnd) + "]");			
			rank[i] = Integer.parseInt(line.substring(numStart, numEnd));
			numStart = numEnd + 1;
		}
	}

/* Method: getName() */
/**
 * Returns the name associated with this entry.
 */
	public String getName() {
		// You need to turn this stub into a real implementation //
		return name;
	}

/* Method: getRank(decade) */
/**
 * Returns the rank associated with an entry for a particular
 * decade.  The decade value is an integer indicating how many
 * decades have passed since the first year in the database,
 * which is given by the constant START_DECADE.  If a name does
 * not appear in a decade, the rank value is 0.
 */
	public int getRank(int decade) {
		// You need to turn this stub into a real implementation //
		return rank[decade];
	}

/* Method: toString() */
/**
 * Returns a string that makes it easy to see the value of a
 * NameSurferEntry.
 */
	public String toString() {
		// You need to turn this stub into a real implementation //
		String str = getName() + " [";
		for (int i = 0; i < rank.length; i++) {
			if (i != 0) {
				str += " ";
			}
			str += getRank(i);
		}
		str += "]";
		return str;
	}
}

