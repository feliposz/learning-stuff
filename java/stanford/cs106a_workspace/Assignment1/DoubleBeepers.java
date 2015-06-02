import stanford.karel.*;

public class DoubleBeepers extends SuperKarel {
	
	public void run() {
		move();
		doubleBeepers();
		moveBack();
		moveBeepersForward();
		turnAround();
	}
	
	private void doubleBeepers() {
		while (beepersPresent()) {
			pickBeeper();
			putDoubleBack();
		}
	}
	
	private void putDoubleBack() {
		moveBack();
		putBeeper();
		putBeeper();
		moveBack();
	}
	
	private void moveBack() {
		turnAround();
		move();
	}
	
	private void moveBeepersForward() {
		while (beepersPresent()) {
			pickBeeper();
			moveBack();
			putBeeper();
			moveBack();
		}
	}
	
}
