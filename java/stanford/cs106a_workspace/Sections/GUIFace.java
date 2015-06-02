import acm.program.*;
import acm.graphics.*;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class GUIFace extends GraphicsProgram {
	
	private static final double SMALL_DIAM = 20;
	private static final double MEDIUM_DIAM = 40;
	private static final double LARGE_DIAM = 60;
	
	// Must declare the variables for the components you need to keep track
	private JCheckBox drawFront;
	private JRadioButton sm;
	private JRadioButton md;
	private JRadioButton lg;
	private JComboBox pick;

	public void init() {
		
		add(new JButton("Clear"), SOUTH); // 
		
		drawFront = new JCheckBox("Front");
		drawFront.setSelected(true);
		add(drawFront, SOUTH);

		initSizeSelector();
		
		initColorPicker();
	
		addActionListeners();
		addMouseListeners();
	}

	private void initSizeSelector() {
		ButtonGroup size = new ButtonGroup();
		sm = new JRadioButton("Small");
		md = new JRadioButton("Medium");
		lg = new JRadioButton("Large");
		// It is necessary to create a buttom group so that radio buttons will change correctly when clicked
		size.add(sm);
		size.add(md);
		size.add(lg);
		md.setSelected(true); // must selected only AFTER buttom group is created
		add(sm, SOUTH); // radio buttons are addad individually
		add(md, SOUTH);
		add(lg, SOUTH);
	}

	private void initColorPicker() {
		add(new JLabel("    Color:"), SOUTH);		
		pick = new JComboBox(); 
		pick.addItem("Black"); // populate the values before showing
		pick.addItem("Blue");
		pick.addItem("Green");
		pick.addItem("Red");
		pick.setEditable(false); // this is important if you don't want to handle custom values from the user
		pick.setSelectedItem("Black");
		add(pick, SOUTH);
	}
	
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("Clear")) {
			removeAll();
		}
	}
	
	public void mouseClicked(MouseEvent e) {
		GObject face;
		double size = getSelectedSize();		
		if (drawFront.isSelected()) {
			face = new GFace(size, size); 
		} else {
			face = new GOval(size, size);
		}
		face.setColor(getSelectedColor());
		add(face, e.getX(), e.getY());
	}
	
	private double getSelectedSize() {
		double size = 0;
		if (sm.isSelected()) {
			size = SMALL_DIAM; 
		} else if (md.isSelected()) {
			size = MEDIUM_DIAM; 
		} else if (lg.isSelected()) {
			size = LARGE_DIAM; 
		}
		return size;
	}
	
	private Color getSelectedColor() {
		String colorName = (String) pick.getSelectedItem();
		Color color = Color.WHITE;
		if (colorName.equals("Black")) {
			color = Color.BLACK;
		} else if (colorName.equals("Blue")) {
			color = Color.BLUE; 
		} else if (colorName.equals("Green")) {
			color = Color.GREEN; 
		} else if (colorName.equals("Red")) {
			color = Color.RED;
		}
		return color; 
	}			

}