/*
 * File: KarelDefendsDemocracy.java
 * --------------------------------
 * Assignement Section 1
 */

import stanford.karel.*;
public class KarelDefendsDemocracy extends SuperKarel {
	
	public void run() {
		while (frontIsClear()) {
			enterNextBallot();		
			checkBallot();
		}
	}

	private void enterNextBallot() {
		move();
	}
	
	private void checkBallot() {
		if (beepersPresent()) {
			fillBallot();
		} else {
			emptyBallot();
		}		
	}

	private void fillBallot() {
		goToBottomOfBallot();
		while (true) {
			if (!beepersPresent()) {
				putBeeper();
			}
			if (frontIsBlocked()) {
				break;
			}
			move();
		}
		leaveBallot();		
	}

	private void emptyBallot() {
		goToBottomOfBallot();
		while (true) {
			while (beepersPresent()) {
				pickBeeper();
			}
			if (frontIsBlocked()) {
				break;
			}
			move();
		}
		leaveBallot();		
	}


	private void goToBottomOfBallot() {
		turnRight();
		while (frontIsClear()) {
			move();
		}
		turnAround();
	}

	private void leaveBallot() {
		turnAround();
		while (leftIsBlocked()) {
			move();
		}
		turnLeft();
		move();		
	}

	
}
