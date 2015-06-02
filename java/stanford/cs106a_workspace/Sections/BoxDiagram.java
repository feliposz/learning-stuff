import acm.program.*;
import acm.graphics.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;

public class BoxDiagram extends GraphicsProgram {

	private static final int BOX_WIDTH = 120;
	private static final int BOX_HEIGHT = 50;
		
	private JTextField nameTF;
	private JButton addBT;
	private JButton removeBT;
	private JButton clearBT;
	private GBoxLabel selected;
	private Map<String, GBoxLabel> boxes;
	private int dx = 0;
	private int dy = 0;

	public void init() {		
		boxes = new HashMap<String, GBoxLabel>();
		 
		nameTF = new JTextField(30);
		addBT = new JButton("Add");
		removeBT = new JButton("Remove");
		clearBT = new JButton("Clear");
		
		nameTF.addActionListener(this);
		
		add(new JLabel("Name:"), SOUTH);
		add(nameTF, SOUTH);
		add(addBT, SOUTH);
		add(removeBT, SOUTH);
		add(clearBT, SOUTH);		
		
		//add(createBoxLabel("Test"));
		
		addBoxLabel("Apple");
		addBoxLabel("Banana");
		clearBoxLabels();
		
		
		
		addActionListeners();
		addMouseListeners();
		//mousedragged
	}
	
	public void mousePressed(MouseEvent e) {
		selected = (GBoxLabel) getElementAt(e.getX(), e.getY());
		dx = (int)(e.getX() - selected.getX());
		dy = (int)(e.getY() - selected.getY());
		
		// HACK?
		for (String name : boxes.keySet()) {
			if (selected == boxes.get(name)) {
				nameTF.setText(name);
			}
		}
	}
	
	public void mouseReleased(MouseEvent e) {
		selected = null; 
	}
	
	public void mouseDragged(MouseEvent e) {
		if (selected != null) {
			selected.setLocation(e.getX() - dx, e.getY() - dy);
		}
	}
	
	public void actionPerformed(ActionEvent e) {
		Object src = e.getSource();
		String name = nameTF.getText();
		
		if (src == addBT || src == nameTF) {
			addBoxLabel(name);
		} else if (src == removeBT) {
			removeBoxLabel(name);
		} else if (src == clearBT) {
			clearBoxLabels();
		}
	}
	
	private GBoxLabel createBoxLabel(String label) {
		GBoxLabel box = new GBoxLabel(label, BOX_WIDTH, BOX_HEIGHT);
		double x = (getWidth() - BOX_WIDTH) / 2;
		double y = (getHeight() - BOX_HEIGHT) / 2;
		box.setLocation(x, y);
		return box;
	}
	
	private void addBoxLabel(String name) {
		if (!boxes.containsKey(name)) {
			GBoxLabel box = createBoxLabel(name);
			add(box);
			boxes.put(name, box);
		}
	}

	private void removeBoxLabel(String name) {
		if (boxes.containsKey(name)) {
			remove(boxes.get(name)); // remove from screen
			boxes.remove(name); // remove from hash
		}
	}
	
	private void clearBoxLabels() {
		for (String name : boxes.keySet()) {
			remove(boxes.get(name));
		}
		boxes.clear();
	}
}

