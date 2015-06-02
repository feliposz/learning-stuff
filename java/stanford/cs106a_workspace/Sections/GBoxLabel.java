import acm.graphics.*;


public class GBoxLabel extends GCompound {

	public GBoxLabel(String text, int width, int height) {
		// TODO Auto-generated constructor stub
		GRect rect = new GRect(width, height);
		GLabel label = new GLabel(text);
		
		add(rect);
		double x = (width - label.getWidth()) / 2;
		double y = (height - label.getDescent()) / 2;
		add(label, x, y);		
	}
	
}
