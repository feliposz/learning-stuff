/*
 * File: Pyramid.java
 * Name: 
 * Section Leader: 
 * ------------------
 * This file is the starter file for the Pyramid problem.
 * It includes definitions of the constants that match the
 * sample run in the assignment, but you should make sure
 * that changing these values causes the generated display
 * to change accordingly.
 */

import acm.graphics.*;
import acm.program.*;

public class Pyramid extends GraphicsProgram {

	private static final long serialVersionUID = 5703830460071233096L;

/** Width of each brick in pixels */
	private static final int BRICK_WIDTH = 30;

/** Width of each brick in pixels */
	private static final int BRICK_HEIGHT = 12;

/** Number of bricks in the base of the pyramid */
	private static final int BRICKS_IN_BASE = 14;
	
	public void run() {
		// draw from bottom up, as if building a real pyramid =)
		for (int row = BRICKS_IN_BASE; row > 0; row--) {
			// each row has the number of bricks equivalent to its number
			int totalBricks = row;
			// the y coordinate where to draw the row of bricks
			int y = getHeight() - BRICK_HEIGHT * (BRICKS_IN_BASE - row + 1);
			// the x coordinate of the beginning of the row centered on the screen
			int x = (getWidth() - BRICK_WIDTH * totalBricks) / 2; 
			for (int brick = 0; brick < totalBricks; brick++) {
				// the offset of the brick from the beginning of the current row
				int brickOffset = brick * BRICK_WIDTH;
				GRect r = new GRect(x + brickOffset, y, BRICK_WIDTH, BRICK_HEIGHT);
				add(r);
			}
		}
	}
}

