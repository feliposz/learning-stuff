import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;

import acm.graphics.GCanvas;
import acm.graphics.GRect;


public class MyCanvas extends GCanvas implements ComponentListener {

	private static final double BOX_WIDTH = 50;
	private static final double BOX_HEIGHT = 50;
	private GRect rect;

	public MyCanvas() {
		addComponentListener(this);
		rect = new GRect(BOX_WIDTH, BOX_HEIGHT);
		rect.setFilled(true);
	}
	
	public void update() {
		removeAll();
		add(rect, (getWidth() - BOX_WIDTH) / 2,
				  (getHeight() - BOX_HEIGHT) / 2);
	}
	
	public void componentHidden(ComponentEvent arg0) {
		// TODO Auto-generated method stub

	}

	public void componentMoved(ComponentEvent arg0) {
		// TODO Auto-generated method stub

	}

	public void componentResized(ComponentEvent arg0) {
		// TODO Auto-generated method stub
		update();
	}

	public void componentShown(ComponentEvent arg0) {
		// TODO Auto-generated method stub

	}

}
