/*
 * File: Target.java
 * Name: 
 * Section Leader: 
 * -----------------
 * This file is the starter file for the Target problem.
 */

import acm.graphics.*;
import acm.program.*;
import java.awt.*;

public class Target extends GraphicsProgram {	

	private static final long serialVersionUID = -8795106798592606723L;
	
	private static final int PIXELS_PER_INCH = 87; //72;

	public void run() {
		int radius1 = PIXELS_PER_INCH;
		int radius2 = PIXELS_PER_INCH * 65 / 100;
		int radius3 = PIXELS_PER_INCH * 30 / 100;
		
		add(getCenteredCircle(radius1, Color.RED));
		add(getCenteredCircle(radius2, Color.WHITE));
		add(getCenteredCircle(radius3, Color.RED));
	}

	private GOval getCenteredCircle(int radius, Color color) {
		GOval oval = new GOval((getWidth() - radius) / 2,(getHeight() - radius) / 2, radius, radius);
		oval.setFillColor(color);
		oval.setFilled(true);
		oval.setColor(color);
		return oval;
	}

}
