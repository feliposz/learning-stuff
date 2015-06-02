import acm.program.*;
import acm.graphics.*;
import java.awt.*;

public class RobotFace extends GraphicsProgram {
	
	private static final int HEAD_WIDTH = 120;
	private static final int HEAD_HEIGHT = 200;
	private static final int EYE_RADIUS = 25;
	private static final int MOUTH_WIDTH = 80;
	private static final int MOUTH_HEIGHT = 20;
	
	public void run() {
		GRect head = new GRect(
				(getWidth() - HEAD_WIDTH) / 2,
				(getHeight() - HEAD_HEIGHT) / 2,
				HEAD_WIDTH,
				HEAD_HEIGHT
			);
		head.setColor(Color.BLACK);
		head.setFillColor(Color.GRAY);
		head.setFilled(true);
		
		GRect mouth = new GRect(
				(head.getWidth() - MOUTH_WIDTH) * 0.50 + head.getX(),
				(head.getHeight() - MOUTH_HEIGHT) * 0.75 + head.getY(),
				MOUTH_WIDTH,
				MOUTH_HEIGHT
			);
		mouth.setColor(Color.WHITE);
		mouth.setFillColor(Color.WHITE);
		mouth.setFilled(true);
		
		GOval leftEye = new GOval(
				(head.getX() + head.getWidth() * 0.25) - (EYE_RADIUS / 2),
				(head.getY() + head.getHeight() * 0.25) - (EYE_RADIUS / 2),
				EYE_RADIUS,
				EYE_RADIUS
			);
		leftEye.setColor(Color.YELLOW);
		leftEye.setFillColor(Color.YELLOW);
		leftEye.setFilled(true);
		
		GOval rightEye = new GOval(
				(head.getX() + head.getWidth() * 0.75) - (EYE_RADIUS / 2),
				leftEye.getY(),
				EYE_RADIUS,
				EYE_RADIUS
			);
		rightEye.setColor(Color.YELLOW);
		rightEye.setFillColor(Color.YELLOW);
		rightEye.setFilled(true);
		
		add(head);
		add(mouth);
		add(leftEye);
		add(rightEye);
	}
	
}
