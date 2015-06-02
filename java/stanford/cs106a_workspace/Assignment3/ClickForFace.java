/*
 * File: ClickForFace.java
 * -----------------------
 * This program displays a face in every location the user
 * clicks on.  It is an example of an event-driven program.
 */

import acm.program.*;
import java.awt.event.*;

public class ClickForFace extends GraphicsProgram {

	private static final int FACE_DIAM = 30;
	
	// Note: no run() method is this program
	// init() method is called when program starts
	public void init() {
		// Must call this method to be able to get mouse events
		addMouseListeners();
	}
	
	public void mouseClicked(MouseEvent e) {
		GFace face = new GFace(FACE_DIAM, FACE_DIAM);
		add(face, e.getX(), e.getY());
	}
	
}
