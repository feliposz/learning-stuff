import acm.program.ConsoleProgram;
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import acm.graphics.*;

public class TextAndGraphics extends ConsoleProgram {

	private static final int SPACER = 30;
	
	private GCanvas leftCanvas;
	private GCanvas rightCanvas;
	private JTextField textField;
	private int leftY = 10;
	private int rightY = 10;
	
	public void init() {
		setLayout(new GridLayout(1, 3));
		
		leftCanvas = new GCanvas();
		add(leftCanvas);
		
		rightCanvas = new GCanvas();
		add(rightCanvas);
		
		textField = new JTextField(10);
		add(new JLabel("Some text"), SOUTH);
		add(textField, SOUTH);
		textField.addActionListener(this);
		
		add(new JButton("Draw left"), SOUTH);
		add(new JButton("Draw right"), SOUTH);	
		addActionListeners();
	}
	
	public void actionPerformed(ActionEvent e) {
		String cmd = e.getActionCommand();
		
		if (cmd.equals("Draw left")) {
			leftCanvas.add(createFilledRect(), 20, leftY);
			leftY += SPACER;
		} else if (cmd.equals("Draw right")) {
			rightCanvas.add(createFilledRect(), 20, rightY);
			rightY += SPACER;
		}
		
		if (e.getSource() == textField) {
			println("You typed " + textField.getText());
		}
	}
	
	private GRect createFilledRect() {
		GRect rect = new GRect(50, 20);
		rect.setFilled(true);
		return rect;
	}
	
}
