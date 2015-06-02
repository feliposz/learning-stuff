import acm.program.*;
import acm.graphics.*;

public class Checkerboard extends GraphicsProgram {

	private static final long serialVersionUID = -6797944879516969493L;
	
	private static final int NROWS = 8;
	private static final int NCOLS = 8;
		
	public void run() {
		int squareSize = getHeight() / NROWS;
		
		for (int i = 0; i < NROWS; i++) {
			for (int j = 0; j < NCOLS; j++) {
				int x = j * squareSize;
				int y = i * squareSize;
				GRect square = new GRect(x, y, squareSize, squareSize);
				square.setFilled(((i + j) % 2) != 0);
				add(square);
			}
		}
	}
}
