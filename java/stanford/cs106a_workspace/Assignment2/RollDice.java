/*
* File: RollDice.java
* -------------------
* This program simulates rolling some number of dice until
* the maximal value on the all the dice is rolled.
*/

import acm.program.*;
import acm.util.*;

public class RollDice extends ConsoleProgram {
	
	private static final int NUM_SIDES = 6;
	private RandomGenerator rnd = RandomGenerator.getInstance(); 
	
	public void run() {
		int numDice = readInt("Enter the number of dice: ");
		int maxRoll = numDice * NUM_SIDES;
		int total = 0, count = 0;		
		while (total != maxRoll) {
			total = rollDice(numDice);
			println("Rolled " + total);
			count++;
		}
		println("It took " + count + " attempts to get " + numDice * NUM_SIDES + " on the dice.");
	}
	
	/* Returns the total of rolling numDice dice */
	private int rollDice(int numDice) {
		int total = 0;
		for (int i = 0; i < numDice; i++) {
			total += rnd.nextInt(1, NUM_SIDES);
		}
		return total;
	}
}
