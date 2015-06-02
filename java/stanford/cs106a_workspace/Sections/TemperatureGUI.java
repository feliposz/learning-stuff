import java.awt.event.*;
import acm.program.*;
import acm.gui.*;
import javax.swing.*;

public class TemperatureGUI extends Program {

	private IntField fahrenheitField;
	private IntField celsiusField;

	public void init() {
		setLayout(new TableLayout(2, 3));
		
		fahrenheitField = new IntField(32);
		fahrenheitField.setActionCommand("F -> C");
		fahrenheitField.addActionListener(this);
		
		celsiusField = new IntField(0);
		celsiusField.setActionCommand("C -> F");
		celsiusField.addActionListener(this);
		
		add(new JLabel("Degree Fahrenheit: "));
		add(fahrenheitField);
		add(new JButton("F -> C"));

		add(new JLabel("Degree Celsius: "));
		add(celsiusField);
		add(new JButton("C -> F"));
		
		addActionListeners();		
	}
	
	public void actionPerformed(ActionEvent e) {
		String cmd = e.getActionCommand();
		if (cmd.equals("F -> C")) {
			int f = fahrenheitField.getValue();
			int c = (int)((5.0 / 9.0) * (f - 32));
			celsiusField.setValue(c);
		} else if (cmd.equals("C -> F")) {
			int c = celsiusField.getValue();
			int f = (int)((9.0 / 5.0) * c + 32);
			fahrenheitField.setValue(f);
		}
	}
	
}
