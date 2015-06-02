/*
 * File: NameSurferGraph.java
 * ---------------------------
 * This class represents the canvas on which the graph of
 * names is drawn. This class is responsible for updating
 * (redrawing) the graphs whenever the list of entries changes or the window is resized.
 */

import acm.graphics.*;
import java.awt.event.*;
import java.util.*;
import java.awt.*;

public class NameSurferGraph extends GCanvas
	implements NameSurferConstants, ComponentListener {

	private ArrayList<NameSurferEntry> entries;
	
	/**
	* Creates a new NameSurferGraph object that displays the data.
	*/
	public NameSurferGraph() {
		addComponentListener(this);
		//	 You fill in the rest //
		
		entries = new ArrayList<NameSurferEntry>();
	}
	
	/**
	* Clears the list of name surfer entries stored inside this class.
	*/
	public void clear() {
		//	 You fill this in //
		entries.clear();
		System.out.println("Clear");
		update();
	}
	
	/* Method: addEntry(entry) */
	/**
	* Adds a new NameSurferEntry to the list of entries on the display.
	* Note that this method does not actually draw the graph, but
	* simply stores the entry; the graph is drawn by calling update.
	*/
	public void addEntry(NameSurferEntry entry) {
		// You fill this in //
		entries.add(entry);
		System.out.println("Graph: " + entry);
		update();
	}
	
	
	
	/**
	* Updates the display image by deleting all the graphical objects
	* from the canvas and then reassembling the display according to
	* the list of entries. Your application must call update after
	* calling either clear or addEntry; update is also called whenever
	* the size of the canvas changes.
	*/
	public void update() {
		//	 You fill this in //
		removeAll();
		
		drawGrid();

		drawEntries();
		//MAX_RANK = 1000;	
	}

	private void drawGrid() {
		int width = getWidth();
		int height = getHeight();
		
		add(new GLine(0, GRAPH_MARGIN_SIZE, width, GRAPH_MARGIN_SIZE));
		add(new GLine(0, height - GRAPH_MARGIN_SIZE, width, height - GRAPH_MARGIN_SIZE));
		
		int spacing = width / NDECADES;
		
		for (int i = 0; i < NDECADES; i++) {
			int decade = START_DECADE + i * 10;
			GLabel label = new GLabel("" + decade);

			int x = spacing * i;
			int y = height - (int)label.getDescent();

			label.setLocation(x + 2, y);
			add(label);
			add(new GLine(x, 0, x, height));
		}
	}
	
	private void drawEntries() {
		for (int i = 0; i < entries.size(); i++) {
			graphEntry(entries.get(i), i);
		}
	}
	
	private void graphEntry(NameSurferEntry n, int p) {
		int spacing = getWidth() / NDECADES;
		int x2 = -1;
		int y2 = -1;
		
		Color colors[] = { Color.BLACK, Color.RED, Color.BLUE, Color.MAGENTA };
		int c = p % colors.length;
		Color color = colors[c];
		
		for (int i = 0; i < NDECADES; i++) {
			int rank = n.getRank(i);
			String str = n.getName() + " ";
			if (rank == 0) {
				str += "*";
				rank = 1000;
			} else {
				str += rank;
			}
			GLabel label = new GLabel(str);

			int x = spacing * i;
			int y = GRAPH_MARGIN_SIZE + (rank * (getHeight() - GRAPH_MARGIN_SIZE * 2)) / 1000;
		
			label.setLocation(x + 2, y);
			label.setColor(color);
			add(label);
			
			if (x2 > -1) {
				GLine line = new GLine(x, y, x2, y2);
				line.setColor(color);
				add(line);
			}
			x2 = x;
			y2 = y;
		}
	}
	
	/* Implementation of the ComponentListener interface */
	public void componentHidden(ComponentEvent e) { }
	public void componentMoved(ComponentEvent e) { }
	public void componentResized(ComponentEvent e) { update(); }
	public void componentShown(ComponentEvent e) { }
}
