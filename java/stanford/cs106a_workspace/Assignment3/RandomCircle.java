import acm.program.*;
import acm.graphics.*;
import acm.util.*;
import java.awt.*;

public class RandomCircle extends GraphicsProgram {

	private static final int MAX_RADIUS = 50;
	private static final int MIN_RADIUS = 5;
	private static final int NCIRCLES = 10;
	
	private static RandomGenerator rgen = RandomGenerator.getInstance();
	
	public void run() {
		for (int i = 0; i < NCIRCLES; i++) {
			drawRandomCircle();
		}
	}
	
	private void drawRandomCircle() {
		int radius = rgen.nextInt(MIN_RADIUS, MAX_RADIUS);
		int x = rgen.nextInt(0, getWidth() - radius*2);
		int y = rgen.nextInt(0, getHeight() - radius*2);
		GOval circle = new GOval(x, y, radius*2, radius*2);
		circle.setFilled(true);
		circle.setColor(rgen.nextColor());
		add(circle);
	}
	
}
