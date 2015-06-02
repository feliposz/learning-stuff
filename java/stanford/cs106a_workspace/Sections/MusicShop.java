import acm.program.*;
import acm.util.*;

import java.awt.event.*;
import java.io.*;
import java.util.*;
import javax.swing.*;

public class MusicShop extends Program {

	HashMap<String, Album> inventory = new HashMap<String, Album>();
	private JTextField albumTF;
	private MusicShopDisplay canvas; 
	
	
	public void init() {
	
		albumTF = new JTextField(20);
		
		add(new JLabel("Album Name"), SOUTH);
		add(albumTF, SOUTH);
		
		canvas = new MusicShopDisplay();
		add(canvas);

		loadInventory();

		addActionListeners();
		albumTF.addActionListener(this);		
	}

	private void loadInventory() {
		try {
			BufferedReader rd = new BufferedReader(new FileReader("MusicData.txt"));
			while (true) {
				String line = rd.readLine();
				if (line == null)
					break;
				Album album = parseAlbumLine(line);
				inventory.put(album.getTitle(), album);
			}		
			rd.close();
		} catch (IOException e) {
			throw new ErrorException(e);
		}		
	}

	private Album parseAlbumLine(String line) {
		int startTitle = line.indexOf("[") + 1;
		int endTitle = line.indexOf("]");
		String title = line.substring(startTitle, endTitle);

		int startBand = line.indexOf("[", endTitle + 1) + 1;
		int endBand = line.indexOf("]", endTitle + 1);
		String band = line.substring(startBand, endBand);

		int startStock = line.indexOf(" ", endBand + 1) + 1;
		int stock = Integer.parseInt(line.substring(startStock)); 
		
		return new Album(title, band, stock);
	}
	
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == albumTF) {
			canvas.displayInventory(inventory.get(albumTF.getText()));
		}
	}
	
}
