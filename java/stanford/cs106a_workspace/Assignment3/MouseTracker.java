import acm.program.*;
import acm.graphics.*;
import java.awt.event.MouseEvent;


public class MouseTracker extends GraphicsProgram {

	private GLabel label;
	
	public void init() {
		addMouseListeners();
		label = new GLabel("", 50, 50);
		label.setFont("Serif-20");
		add(label);
	}
	
	public void mouseMoved(MouseEvent e) {
		label.setLabel("Mouse coord: " + e.getX() + ", " + e.getY());
	}
	
}
