import acm.program.*;
import acm.graphics.*;


public class ImageProcessing extends GraphicsProgram {
	
	private static final int PADDING = 5; 
	
	GImage horizontalFlip(GImage original) {
		int[][] pixels = original.getPixelArray();
		int height = (int)original.getHeight();
		int width = (int)original.getWidth();
		for (int y = 0; y < height; y++) {
			for (int x = 0; x < (width / 2); x++) {
				int x2 = width - x - 1;
				int tmp = pixels[y][x];
				pixels[y][x] = pixels[y][x2];
				pixels[y][x2] = tmp;
			}
		}
		return new GImage(pixels);
	}
	
	GImage verticalFlip(GImage original) {
		int[][] pixels = original.getPixelArray();
		int height = (int)original.getHeight();
		int width = (int)original.getWidth();
		for (int y = 0; y < (height / 2); y++) {
			for (int x = 0; x < width; x++) {
				int y2 = height - y - 1;
				int tmp = pixels[y][x];
				pixels[y][x] = pixels[y2][x];
				pixels[y2][x] = tmp;
			}
		}
		return new GImage(pixels);
	}	
	
	public void run() {
		GImage image = new GImage("TheMilkmaid.jpg");
		setSize((int)image.getWidth() * 2 + PADDING, (int)image.getHeight() * 2 + PADDING + 60);
		GImage flippedH = horizontalFlip(image);
		GImage flippedV = verticalFlip(image);
		GImage flippedHV = horizontalFlip(verticalFlip(image));
		flippedH.setLocation(image.getWidth() + PADDING, 0);
		flippedV.setLocation(0, image.getHeight() + PADDING);
		flippedHV.setLocation(image.getWidth() + PADDING, image.getHeight() + PADDING);
		add(image);
		add(flippedH);
		add(flippedV);
		add(flippedHV);
	}
	
}
