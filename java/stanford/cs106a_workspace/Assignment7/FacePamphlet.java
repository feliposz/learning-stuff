/* 
 * File: FacePamphlet.java
 * -----------------------
 * When it is finished, this program will implement a basic social network
 * management system.
 */

import acm.program.*;
import acm.graphics.*;
import acm.util.*;

import java.awt.*;
import java.awt.event.*;
import java.util.*;

import javax.swing.*;


/**
 * The FacePamphlet class
 * @author Felipo
 *
 */

public class FacePamphlet extends Program 
					implements FacePamphletConstants {

	
	private JLabel lblName;
	private JTextField tfName;
	private JButton btnAdd;
	private JButton btnDelete;
	private JButton btnLookup;
	private JTextField tfStatus;
	private JButton btnChangeStatus;
	private JTextField tfPictureFilename;
	private JButton btnChangePicture;
	private JTextField tfFriend;
	private JButton btnAddFriend;	
	
	FacePamphletDatabase db;
	FacePamphletProfile currentProfile;
	FacePamphletCanvas canvas;
	
	/**
	 * This method has the responsibility for initializing the 
	 * interactors in the application, and taking care of any other 
	 * initialization that needs to be performed.
	 */
	public void init() {
		// You fill this
		buildInteractors();
		db = new FacePamphletDatabase();
		canvas = new FacePamphletCanvas();
		currentProfile = null;
		
		add(canvas);
		
		//tests();
    }

    /**
     * This method is responsible for detecting when the buttons are
     * clicked or interactors are used.
     */
    public void actionPerformed(ActionEvent e) {
		// You fill this in as well as add any additional methods
    	Object src = e.getSource();
    	if (src == btnAdd) {
    		profileAdd();
    	} else if (src == btnDelete) {
    		profileDelete();
    	} else if (src == btnLookup) {
    		profileLookup();
    	} else if (src == btnChangeStatus || src == tfStatus) {
    		changeStatus();
    	} else if (src == btnChangePicture || src == tfPictureFilename) {
    		changePicture();
    	} else if (src == btnAddFriend || src == tfFriend) {
    		addFriend();
    	}    		
	}
    
	/**
	 * Place the interactores in the screen. (Labels, buttons, text fields, etc.
	 * Also adds listeners.
	 */
	private void buildInteractors() {
		setPreferredSize(new Dimension(APPLICATION_WIDTH, APPLICATION_HEIGHT));
				
		lblName = new JLabel("Name");
		tfName = new JTextField(TEXT_FIELD_SIZE);
		btnAdd = new JButton("Add");
		btnDelete = new JButton("Delete");
		btnLookup = new JButton("Lookup");
		add(lblName, NORTH);
		add(tfName, NORTH);
		add(btnAdd, NORTH);
		add(btnDelete, NORTH);
		add(btnLookup, NORTH);

		tfStatus = new JTextField(TEXT_FIELD_SIZE);
		btnChangeStatus = new JButton("Change Status");
		tfPictureFilename = new JTextField(TEXT_FIELD_SIZE);
		btnChangePicture = new JButton("Change Picture");
		tfFriend = new JTextField(TEXT_FIELD_SIZE);
		btnAddFriend = new JButton("Add Friend");
		
		add(tfStatus, WEST);
		add(btnChangeStatus, WEST);
		add(new JLabel(EMPTY_LABEL_TEXT), WEST);
		add(tfPictureFilename, WEST);
		add(btnChangePicture, WEST);
		add(new JLabel(EMPTY_LABEL_TEXT), WEST);
		add(tfFriend, WEST);
		add(btnAddFriend, WEST);

		tfStatus.addActionListener(this);
		tfPictureFilename.addActionListener(this);
		tfFriend.addActionListener(this);
		addActionListeners();
	}
    
	/**
	 * Updates the display of the current profile
	 */	
	private void displayCurrentProfile() {
		if (currentProfile != null) {
			println("Current Profile: [" + currentProfile + "]");
		}
		canvas.displayProfile(currentProfile);
	}

	/**
	 * Updates the current message being displayed in the message bar
	 * @param message Message string to be display
	 */
	private void displayMessage(String message) {
		println(">>> Message: " + message);
		canvas.showMessage(message);
	}

	/**
	 * Add a new profile to the database if name is not there yet.
	 */
	private void profileAdd() {
		String name = tfName.getText();
		if (!name.equals("")) {
			//println("Add: " + name);
			if (db.containsProfile(name)) {
				displayMessage("There is a profile for " + name + " already.");
			} else {
				currentProfile = new FacePamphletProfile(name); 
				db.addProfile(currentProfile);
				displayCurrentProfile();
			}
		}
	}
	
	/**
	 * Delete the named profile.
	 */

	private void profileDelete() {
		String name = tfName.getText();
		if (!name.equals("")) {
			//println("Delete: " + name);			
			if (!db.containsProfile(name)) {
				displayMessage("There is no profile for " + name + ". Nothing to delete.");
			} else {
				if (currentProfile == db.getProfile(name)) {
					currentProfile = null;
					displayCurrentProfile();
				}
				db.deleteProfile(name);
				displayMessage("Profile of " + name + " deleted.");
			}

		}
	}
	
	/**
	 * Lookup a profile by name and display it.
	 */

	private void profileLookup() {
		String name = tfName.getText();
		if (!name.equals("")) {
			//println("Lookup: " + name);
			if (!db.containsProfile(name)) {
				displayMessage("No profile found for " + name + ".");
			} else {
				currentProfile = db.getProfile(name);
				displayCurrentProfile();
			}
		}
	}

	/**
	 * Change the status of the current profile.
	 *
	 */
	private void changeStatus() {
		String status = tfStatus.getText();
		if (!status.equals("")) {
			//println("Change Status: " + status);
			if (currentProfile != null) {				
				currentProfile.setStatus(status);
				displayCurrentProfile();
				displayMessage("Status updated to " + status);
			} else {
				displayMessage("No profile selected. Add/Lookup a profile to change the status.");
			}
		}
	}

	/**
	 * Handle click on button Change Picture.
	 *
	 */
	private void changePicture() {
		String pictureFilename = tfPictureFilename.getText();
		if (!pictureFilename.equals("")) {
			//println("Change Picture: " + pictureFilename);
			if (currentProfile != null) {				
				changePictureOfProfile(currentProfile, pictureFilename);
				displayCurrentProfile();
			} else {
				displayMessage("No profile selected. Add/Lookup a profile to change the picture.");
			}
		}
	}
	
	/**
	 * Handle click on button Add Friend
	 *
	 */
	private void addFriend() {
		String friend = tfFriend.getText();
		if (!friend.equals("")) {
			//println("Add Friend: " + friend);
			if (currentProfile != null) {				
				addFriendToProfile(friend);	
				displayCurrentProfile();
			} else {
				displayMessage("No profile selected. Add/Lookup a profile to add a friend.");
			}
		}
	}

	/**
	 * Add named profile to current profile as a friend, if profile for given friend exists.
	 *
	 */

	private void addFriendToProfile(String friend) {
		if (db.containsProfile(friend)) {
			if (currentProfile.addFriend(friend)) {
				db.getProfile(friend).addFriend(currentProfile.getName());
				displayMessage(friend + " added as a friend.");
			} else {
				displayMessage(friend + " is already a friend.");
			}
		} else {
			displayMessage("There is no profile named: " + friend);
		}
	}

	/**
	 * Change the picture of the current profile to the picture in the given filename.
	 *
	 */
	private void changePictureOfProfile(FacePamphletProfile profile, String pictureFilename) {
		GImage image = null;
		try {
			image = new GImage(pictureFilename);
			profile.setImage(image);
			displayMessage("Picture updated: " + pictureFilename);
		} catch (ErrorException e) {
			displayMessage("Picture file not found: " + pictureFilename);
		}
	}
	
	/** 
	 * Quick test for FacePamphletProfile class.
	 *
	 */
	private void tests() {
		FacePamphletProfile p = new FacePamphletProfile("Felipo");
		p.setStatus("programming");
		p.addFriend("Rafaella");
		p.addFriend("Bianca");
		println(p);
		p.setStatus("thinking");
		p.removeFriend("Bianca");
		println(p);
	}	
}
