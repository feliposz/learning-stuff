import acm.program.*;
import acm.graphics.*;
import java.awt.*;
import java.awt.event.*;

public class DrawingLines extends GraphicsProgram {

	private GLine line;
	
	public void run() {
		addMouseListeners();
	}
	
	public void mousePressed(MouseEvent me) {
		line = new GLine(me.getX(), me.getY(), me.getX(), me.getY());
		add(line);
	}
	
	public void mouseDragged(MouseEvent me) {
		if (line != null)
			line.setEndPoint(me.getX(), me.getY());
	}
	
}
