/*
 * File: StoneMasonKarel.java
 * --------------------------
 * The StoneMasonKarel subclass as it appears here does nothing.
 * When you finish writing it, it should solve the "repair the quad"
 * problem from Assignment 1.  In addition to editing the program,
 * you should be sure to edit this comment so that it no longer
 * indicates that the program does nothing.
 */

import stanford.karel.*;

public class StoneMasonKarel extends SuperKarel {

	public void run() {
		fillColumn();
		descendBack();
		while (frontIsClear()) {
			moveToNextColumn();
			fillColumn();
			descendBack();			
		}
	}

	private void fillColumn() {
		turnLeft();
		repairStone();
		while (frontIsClear()) {
			move();
			repairStone();			
		}
	}

	private void repairStone() {
		if (!beepersPresent()) {
			putBeeper();
		}
	}

	private void descendBack() {
		turnAround();
		while (frontIsClear()) {
			move();
		}
		turnLeft();
	}

	private void moveToNextColumn() {
		move();
		move();
		move();
		move();		
	}
	
}
