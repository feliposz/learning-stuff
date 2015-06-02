/*
 * File: NameSurfer.java
 * ---------------------
 * When it is finished, this program will implements the viewer for
 * the baby-name database described in the assignment handout.
 */

import acm.program.*;
import java.awt.event.*;
import javax.swing.*;

// TODO: Revert to Program instead of ConsoleProgram
public class NameSurfer extends Program implements NameSurferConstants {

	private JLabel lbName;
	private JTextField tfName;
	private JButton btGraph;
	private JButton btClear;
	
	private NameSurferDataBase db;
	private NameSurferGraph graph;
	
/* Method: init() */
/**
 * This method has the responsibility for reading in the data base
 * and initializing the interactors at the bottom of the window.
 */
	public void init() {
	    // You fill this in, along with any helper methods //
		
		db = new NameSurferDataBase(NAMES_DATA_FILE);
		graph = new NameSurferGraph();
		
		add(graph);
		
		lbName = new JLabel("Name");
		tfName = new JTextField(20);
		btGraph = new JButton("Graph");
		btClear = new JButton("Clear");
		
		add(lbName, SOUTH);
		add(tfName, SOUTH);
		add(btGraph, SOUTH);
		add(btClear, SOUTH);
		
		tfName.addActionListener(this);		
		addActionListeners();
		
		// Test NameSurferEntry
		
		/*
		NameSurferEntry nse = new NameSurferEntry("Abel 664 613 626 575 542 491 497 422 381 385 354");
		println(nse.getName());
		println(nse.getRank(0));
		println(nse.getRank(10));
		println(nse.toString());
		*/
		
		// Test NameSurferDataBase
				
		//println(db.findEntry("Abel").toString());
	}

/* Method: actionPerformed(e) */
/**
 * This class is responsible for detecting when the buttons are
 * clicked, so you will have to define a method to respond to
 * button actions.
 */
	public void actionPerformed(ActionEvent e) {
		// You fill this in //
		Object src = e.getSource();		
		if (src == tfName || src == btGraph) {
			NameSurferEntry entry = db.findEntry(tfName.getText());
			graph.addEntry(entry);
			//println("Graph: " + entry);
		} else if (src == btClear) {
			graph.clear();
			//println("Clear");
		}
	}
}
