import acm.program.*;

import java.awt.event.*;
import javax.swing.*;

public class GUITest extends ConsoleProgram {

	private JButton hello;
	private JTextField text;
	private JSlider size;
	
	public void init() {
		setFont("Courier New-24");
		add(new JButton("North"), NORTH);
		add(new JButton("South"), SOUTH);
		add(new JButton("East"), EAST);
		add(new JButton("West"), WEST);

		hello = new JButton("Hello");
		add(hello, SOUTH);
		
		size = new JSlider(12, 36, 24);
		add(size, SOUTH);

		add(new JLabel("Name:"), SOUTH);
		text = new JTextField(10);
		text.addActionListener(this);
		add(text, SOUTH);

		addActionListeners();
	}
	
	public void actionPerformed(ActionEvent e) {
		String cmd = e.getActionCommand();
		
		setFont("Courier New-" + size.getValue());
		
		// Refer by text in the button
		if (cmd.equals("North")) {
			println("Hello from the North");
		} else if (cmd.equals("South")) {
			println("Hello from the South");
		} else if (cmd.equals("East")) {
			println("Hello from the East");
		} else if (cmd.equals("West")) {
			println("Hello from the West");
		}

		// Refer by object reference
		if (hello == e.getSource()) {
			println("Hello " + text.getText());
		}
		
		if (text == e.getSource()) {
			println("Your name is " + text.getText());
		}

	}
	
}
