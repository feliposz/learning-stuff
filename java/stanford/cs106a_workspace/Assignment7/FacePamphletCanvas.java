/*
 * File: FacePamphletCanvas.java
 * -----------------------------
 * This class represents the canvas on which the profiles in the social
 * network are displayed.  NOTE: This class does NOT need to update the
 * display when the window is resized.
 */


import acm.graphics.*;
import java.awt.*;
import java.util.*;

public class FacePamphletCanvas extends GCanvas 
					implements FacePamphletConstants {
	
	private String message;
	private GLabel lbMessage;
	
	/** 
	 * Constructor
	 * This method takes care of any initialization needed for 
	 * the display
	 */
	public FacePamphletCanvas() {
		// You fill this in
		message = null;
		lbMessage = new GLabel("");
		lbMessage.setFont(MESSAGE_FONT);
		//add(lbMessage);
	}

	
	/** 
	 * This method displays a message string near the bottom of the 
	 * canvas.  Every time this method is called, the previously 
	 * displayed message (if any) is replaced by the new message text 
	 * passed in.
	 */
	public void showMessage(String msg) {
		// You fill this in
		message = msg;
		lbMessage.setLabel(message);
		double x = (getWidth() - lbMessage.getWidth()) / 2;
		double y = getHeight() - BOTTOM_MESSAGE_MARGIN;
		lbMessage.setLocation(x, y);
		add(lbMessage); // could have been removed from displayProfile
	}
	
	
	/** 
	 * This method displays the given profile on the canvas.  The 
	 * canvas is first cleared of all existing items (including 
	 * messages displayed near the bottom of the screen) and then the 
	 * given profile is displayed.  The profile display includes the 
	 * name of the user from the profile, the corresponding image 
	 * (or an indication that an image does not exist), the status of
	 * the user, and a list of the user's friends in the social network.
	 */
	public void displayProfile(FacePamphletProfile profile) {
		// You fill this in
		removeAll();
		
		if (profile == null)
			return;
		
		showMessage("Displaying " + profile.getName());

		GCompound picture = createPicture(profile.getImage());
		
		GLabel name = new GLabel(profile.getName());
		name.setFont(PROFILE_NAME_FONT);
		name.setColor(Color.BLUE);
		
		String statusString;
		if (profile.getStatus() != "") {
			statusString = profile.getName() + " is " + profile.getStatus();
		} else {
			statusString = "No current status.";
		}
		
		GLabel status = new GLabel(statusString);
		status.setFont(PROFILE_STATUS_FONT);
		
		double posY = TOP_MARGIN + name.getHeight();		
		name.setLocation(LEFT_MARGIN, posY);
		posY += name.getHeight();
		picture.setLocation(LEFT_MARGIN, posY);
		posY += status.getHeight() + picture.getHeight() + STATUS_MARGIN;
		status.setLocation(LEFT_MARGIN, posY);
		
		add(name);
		add(picture);
		add(status);
		
		addFriendsList(profile.getFriends());
	}


	private void addFriendsList(Iterator<String> friends) {
		double friendsX = getWidth() / 2;
		double friendsY = TOP_MARGIN * 3;
		
		GLabel friendLabel = new GLabel("Friends:");
		friendLabel.setFont(PROFILE_FRIEND_LABEL_FONT);
		friendLabel.setLocation(friendsX, friendsY);
		add(friendLabel);
		
		friendsY += friendLabel.getHeight();
		
		while (friends.hasNext()) {
			GLabel friendName = new GLabel(friends.next());
			friendName.setFont(PROFILE_FRIEND_FONT);
			friendName.setLocation(friendsX, friendsY);
			add(friendName);
			friendsY += friendName.getHeight();
		}
	}


	private GCompound createPicture(GImage image) {
		GCompound picture = new GCompound();
		
		if (image == null) {
			picture.add(new GRect(0, 0, IMAGE_WIDTH, IMAGE_HEIGHT));
			GLabel pictureLabel = new GLabel("No Image");
			double labelX = (IMAGE_WIDTH - pictureLabel.getWidth()) / 2;
			double labelY = (IMAGE_HEIGHT - pictureLabel.getAscent()) / 2;
			pictureLabel.setFont(PROFILE_IMAGE_FONT);
			pictureLabel.setLocation(labelX, labelY);
			picture.add(pictureLabel);
		} else {
			image.setSize(IMAGE_WIDTH, IMAGE_HEIGHT);
			picture.add(image);
		}
		return picture;
	}
	
}
