/*
 * File: Hailstone.java
 * Name: 
 * Section Leader: 
 * --------------------
 * This file is the starter file for the Hailstone problem.
 */

import acm.program.*;

public class Hailstone extends ConsoleProgram {

	private static final long serialVersionUID = -2384864848503218968L;

	public void run() {
		int n = readInt("Enter a positive integer: ");
		if (n <= 0) {
			println("Error, number must be a positive integer.");
		} else {
			int i = 0;
			while (n > 1) {
				if (n % 2 == 0) {
					println(n + " is even, so I take half: " + n / 2);
					n = n / 2;
				} else {
					println(n + " is odd, so I make 3n + 1: " + (3*n + 1));
					n = 3*n + 1;
				}
				i++;
			}
			println("This process took " + i + " step(s) to reach 1.");
		}
	}
}

