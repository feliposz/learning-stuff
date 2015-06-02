/*
 * File: MidpointFindingKarel.java
 * -------------------------------
 * When you finish writing it, the MidpointFindingKarel class should
 * leave a beeper on the corner closest to the center of 1st Street
 * (or either of the two central corners if 1st Street has an even
 * number of corners).  Karel can put down additional beepers as it
 * looks for the midpoint, but must pick them up again before it
 * stops.  The world may be of any size, but you are allowed to
 * assume that it is at least as tall as it is wide.
 */

import stanford.karel.*;

public class MidpointFindingKarel extends SuperKarel {

	public void run() {
		goToTheMiddle();
		placeBeeperAbovePosition();
		clearThisRow();
		findBeeper();
		moveBeeperDown();
	}

	private void goToTheMiddle() {
		
		// This method goes to one side of the world to the order,
		// placing beepers at the farest side in each trip.
		// When it finally runs into another beeper, means that the
		// midpoint was reached.
		while (!beepersPresent()) {
			// move until there is no wall and no beeper ahead
			while (frontIsClear() && !beepersPresent()) {		
				move();			
			}
			turnAround();
			if (beepersPresent()) {
				// if a beeper was present, move back
				move();
			}
			// put a beeper to mark that this corner was taken
			putBeeper();
			move();
		}

		// midpoint reached, move back one corner
		turnAround();
		move();
	}	

	private void placeBeeperAbovePosition() {
		// put a beeper above
		if (leftIsClear()) {
			turnLeft();
		} else {
			turnRight();
		}
		move();
		putBeeper();
		
		// go back down
		turnAround();
		move();
		turnLeft();
	}

	private void clearThisRow() {
		// go to the edge		
		while (frontIsClear()) {
			move();
		}
		turnAround();
		
		// clear the row
		if (beepersPresent()) {
			pickBeeper();
		}
		while (frontIsClear()) {
			move();			
			if (beepersPresent()) {
				pickBeeper();
			}
		}
	}

	private void findBeeper() {
		// go to row above
		turnRight();
		move();
		turnRight();
		// find the bepper
		while (!beepersPresent()) {
			move();
		}
	}
	
	private void moveBeeperDown() {
		pickBeeper();
		turnRight();
		move();
		putBeeper();
		turnLeft();
	}
}
