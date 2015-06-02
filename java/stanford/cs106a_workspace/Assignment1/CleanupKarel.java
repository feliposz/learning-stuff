import stanford.karel.*;

public class CleanupKarel extends SuperKarel {

	public void run() {
		while (true) {
			clearRow();
			if (leftIsBlocked()) {
				break;
			}
			goBack();
			goToLineAbove();
		}
	}
	
	public void clearRow() {
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
	
	public void goBack() {
		turnAround();
		while (frontIsClear()) {
			move();
		}
		turnAround();
	}
	
	public void goToLineAbove() {
		turnLeft();
		move();
		turnRight();
	}
	
}
