import stanford.karel.*;

public class CleanupKarel2 extends SuperKarel {

	public void run() {
		cleanRow();
		while (leftIsClear()) {
			turnLeft();
			move();
			turnLeft();
			cleanRow();
			if (rightIsClear()) {
				turnRight();
				move();
				turnRight();
				cleanRow();
			} else {
				turnAround();
			}
		}
	}

	private void cleanRow() {
		while (true) {
			while (beepersPresent()) {
				pickBeeper();
			}
			if (frontIsBlocked()) {
				break;
			}
			move();
		}		
	}

}
