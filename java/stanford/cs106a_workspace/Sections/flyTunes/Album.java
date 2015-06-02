package flyTunes;

import java.util.*;

public class Album {

	private String title;
	private String band;
	private ArrayList<Song> tracks;
	
	public Album(String newTitle, String newBand) {
		title = newTitle;
		band = newBand;
		tracks = new ArrayList<Song>();
	}
	
	public String getTitle() {
		return title;
	}
	
	public String getBand() {
		return band;
	}
	
	public void addTrack(Song song) {
		tracks.add(song);
	}
	
	public Iterator<Song> getTracks() {
		return tracks.iterator();
	}
	
	public String toString() {
		return "Album \"" + getTitle() + "\" by " + getBand() + " has " + tracks.size() + " track(s).";
	}
	
}
