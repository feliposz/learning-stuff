import acm.gui.TableLayout;
import acm.program.*;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class GUIGrid extends Program {

	public void init() {
		//setLayout(new GridLayout(4, 4));
		setLayout(new TableLayout(4, 4));		
		add(new JButton("7"));
		add(new JButton("8"));
		add(new JButton("9"));
		add(new JButton("/"));
		add(new JButton("4"));
		add(new JButton("5"));
		add(new JButton("6"));
		add(new JButton("*"));
		add(new JButton("1"));
		add(new JButton("2"));
		add(new JButton("3"));
		add(new JButton("-"));
		add(new JButton("0"));
		add(new JButton("."));
		add(new JButton("="));
		add(new JButton("+"));		
	}
	
}
