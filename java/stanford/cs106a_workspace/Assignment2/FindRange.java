/*
 * File: FindRange.java
 * Name: 
 * Section Leader: 
 * --------------------
 * This file is the starter file for the FindRange problem.
 */

import acm.program.*;

public class FindRange extends ConsoleProgram {

	private static final long serialVersionUID = -5995814078277545505L;

	public void run() {
		println("This program finds the largest and smallest number.");
		int num = readInt("? ");		
		int min = num;
		int max = num;		
		while (num != 0) {
			num = readInt("? ");
			if (num == 0) break;
			if (num < min) min = num;
			if (num > max) max = num;
		}
		if (max == 0 && min == 0) {
			println("No values entered.");
		} else {
			println("smallest: " + min);
			println("largest: " + max);
		}
	}
}

