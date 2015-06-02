/*
 * File: ProgramHierarchy.java
 * Name: 
 * Section Leader: 
 * ---------------------------
 * This file is the starter file for the ProgramHierarchy problem.
 */

import acm.graphics.*;
import acm.program.*;

public class ProgramHierarchy extends GraphicsProgram {	

	private static final long serialVersionUID = 1518457438654021734L;

	private static final int BOX_WIDTH = 120;
	private static final int BOX_HEIGHT = 40;
	private static final int BOX_SPACE = 20;
	
	public void run() {
		// total dimensions for the class hierarchy box diagram
		int diagramHeight = BOX_HEIGHT * 3;
		int diagramWidth = BOX_WIDTH * 3 + BOX_SPACE * 2;

		// center the parent "Program" box
		int parentX = (getWidth() - BOX_WIDTH) / 2;
		int parentY = (getHeight() - diagramHeight) / 2;
		
		drawBox("Program", parentX, parentY);
		
		// center the child boxes
		int childX = (getWidth() - diagramWidth) / 2;
		int childY = parentY + BOX_HEIGHT * 2;
		
		drawBox("GraphicsProgram", childX, childY);
		drawBox("ConsoleProgram",
				     childX + BOX_WIDTH + BOX_SPACE, childY);
		drawBox("DialogProgram",
				     childX + BOX_WIDTH * 2 + BOX_SPACE * 2, childY);
		
		// calculate coordinates for the lines in the diagram
		int parentCenterX = parentX + BOX_WIDTH / 2;
		int parentBaseY = parentY + BOX_HEIGHT;
		int childCenterX = childX + BOX_WIDTH / 2;
		
		// draw the 3 lines connecting to the parent box
		for (int i = 0; i < 3; i++) {
			add(new GLine(parentCenterX, parentBaseY,
					      childCenterX + (BOX_WIDTH + BOX_SPACE) * i, childY));
		}
	}
	
	private void drawBox(String labelString, int x, int y) {
		add(new GRect(x, y, BOX_WIDTH, BOX_HEIGHT));
		GLabel label = new GLabel(labelString);
		label.setLocation(x + (BOX_WIDTH - label.getWidth()) / 2,
				          y + (BOX_HEIGHT + label.getAscent()) / 2);
		add(label);
	}
}

