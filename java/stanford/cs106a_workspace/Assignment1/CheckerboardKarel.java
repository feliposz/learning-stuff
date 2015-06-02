/*
 * File: CheckerboardKarel.java
 * ----------------------------
 * When you finish writing it, the CheckerboardKarel class should draw
 * a checkerboard using beepers, as described in Assignment 1.  You
 * should make sure that your program works for all of the sample
 * worlds supplied in the starter folder.
 */

import stanford.karel.*;

public class CheckerboardKarel extends SuperKarel {

	public void run() {
		fillEvenColumn();
		while (frontIsClear()) {
			nextColumn();
			fillOddColumn();
			if (frontIsClear()) {
				nextColumn();
				fillEvenColumn();
			}
		}
	}

	private void nextColumn() {
		if (frontIsClear()) {
			move();
		}		
	}

	private void fillEvenColumn() {
		turnLeft();
		putBlack();
		while (frontIsClear()) {
			move();
			putWhite();
			if (frontIsClear()) {
				move();
				putBlack();
			}
		}
		descendBack();
	}

	// Just for FUN!, here I'm reusing the fillEvenColumn since
	// it's the same logic as the Odd column, but one row up.
	private void fillOddColumn() {
		turnLeft();
		putWhite();
		if (frontIsClear()) {
			move();
			turnRight();
			fillEvenColumn();
		}
//		while (frontIsClear()) {
//			move();
//			putBlack();
//			if (frontIsClear()) {
//				move();
//				putWhite();
//			}
//		}
//		descendBack();
	}

	private void putWhite() {
		// do nothing!
	}

	private void putBlack() {
		putBeeper();
	}

	private void descendBack() {
		turnAround();
		while (frontIsClear()) {
			move();
		}
		turnLeft();
	}	

}
