import java.awt.event.*;

import acm.graphics.*;


public class MusicShopDisplay extends GCanvas implements ComponentListener {

	private Album lastAlbum;
	
	public MusicShopDisplay() {
		addComponentListener(this);
		lastAlbum = null;
	}
	
	public void componentHidden(ComponentEvent arg0) {	}
	public void componentMoved(ComponentEvent arg0) {	}
	public void componentShown(ComponentEvent arg0) {	}
	public void componentResized(ComponentEvent arg0) {
		update();		
	}

	public void displayInventory(Album album) {
		lastAlbum = album;
		update();
	}

	private void update() {
		removeAll();
		if (lastAlbum != null) {
			int stock = lastAlbum.getStock();

			int boxY = getHeight() / 2;
			int padding = 10;			
			int boxWidth = getWidth() / 40 - padding;
			int boxHeight = getHeight() / 10;
			
			for (int i = 0; i < stock; i++) {
				GRect rect = new GRect(padding + i * (boxWidth + padding), boxY, boxWidth, boxHeight);
				rect.setFilled(true);
				add(rect);
			}

			GLabel label = new GLabel(lastAlbum.toString());
			int labelY = boxY - (int)label.getAscent() - padding;
			label.setLocation(padding, labelY);
			add(label);
			
		
		}
	}
	
	

}
