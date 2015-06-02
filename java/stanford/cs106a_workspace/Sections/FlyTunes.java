import acm.program.*;
import java.util.*;

import flyTunes.Album;
import flyTunes.Song;

public class FlyTunes extends ConsoleProgram {

	private static final int ADD_SONG = 1;
	private static final int ADD_ALBUM = 2;
	private static final int ADD_SONGS_TO_ALBUM = 3;
	private static final int LIST_ALBUMS = 4;
	private static final int LIST_SONGS = 5;
	private static final int LIST_SONGS_ON_ALBUM = 6;
	private static final int CHANGE_PRICE = 7;
	private static final int EXIT = 8;
	
	private ArrayList<Song> songs = new ArrayList<Song>();
	private HashMap<String, Album> albums = new HashMap<String, Album>();
	
	public void run() {
		int selection = 0;		
		while (selection != EXIT) {
			selection = getSelection();
			processSelection(selection);
		}		
		println("Good bye!");		
	}
	
	private int getSelection() {
		println("Welcome to FlyTunes!");
		println("1) Add a song");
		println("2) Add an album");
		println("3) Add a song to an album");
		println("4) List albums");
		println("5) List songs");
		println("6) List songs on an album");
		println("7) Change the price for a song");
		println("8) Exit");
		return readInt("Enter your selection: ");
	}
	
	private void processSelection(int selection) {
		switch (selection) {
		case ADD_SONG:
			addSong();
			break;
		case ADD_ALBUM:
			addAlbum();
			break;
		case ADD_SONGS_TO_ALBUM:
			addSongsToAlbumMenu();
			break;
		case LIST_ALBUMS:
			listAlbums();
			break;
		case LIST_SONGS:
			listSongs();
			break;
		case LIST_SONGS_ON_ALBUM:
			listSongsOnAlbum();
			break;
		case CHANGE_PRICE:
			changePrice();
			break;
		case EXIT:
			break;
		default:
			println("Invalid selection, please select another option.");
			break;
		}	
	}
	
	private void addSong() {
		println("Adding a song.");
		String title = readLine("Enter the title: ");
		String band = readLine("Enter the name of the band: ");
		Double price = readDouble("Enter the price: ");
		songs.add(new Song(title, band, price));
	}
	
	private void addAlbum() {
		println("Adding an album.");
		String title = readLine("Enter the title: ");
		String band = readLine("Enter the name of the band: ");
		Album album = new Album(title, band);
		albums.put(title, album);
		addSongsToAlbum(album);
	}
	
	private void addSongsToAlbumMenu() {
		println("Adding songs to an album.");
		String title = readLine("Enter the album title: ");
		Album album = albums.get(title);
		if (album != null) {
			addSongsToAlbum(album);
		} else {
			println("Album not found: " + title);
		}
	}
	
	private void addSongsToAlbum(Album album) {
		while (true) {
			String title = readLine("Enter a song title (Enter to quit): ");
			if (title.equals("")) {
				break;
			}
			Song song = findSong(title);
			if (song == null) {
				String band = readLine("Enter the name of the band: ");
				Double price = readDouble("Enter the price: ");
				song = new Song(title, band, price);
				println("Song added to songs' list and to album.");
				songs.add(song);
			} else { 
				println("Song already exists in song's list. Added to album");
			}		
			album.addTrack(song);
		}
	}
	
	private Song findSong(String title) {
		for (int i = 0; i < songs.size(); i++) {
			Song song = songs.get(i);
			if (song.getTitle().equals(title)) {
				return songs.get(i);
			}
		}
		return null;
	}
	
	private void listAlbums() {
		println("Listing albums.");
		for (String title : albums.keySet()) {
			println(albums.get(title).toString());
		}
	}
	
	private void listSongs() {
		println("Listing songs.");
		for (int i = 0; i < songs.size(); i++) {
			println(songs.get(i).toString());
		}
	}
	
	private void listSongsOnAlbum() {
		println("Listing songs on an album.");
		String title = readLine("Enter the album title: ");
		Album album = albums.get(title);
		if (album != null) {
			Iterator<Song> iterSongs = album.getTracks();
			while (iterSongs.hasNext()) {
				println(iterSongs.next().toString());
			}
		} else {
			println("Album not found: " + title);
		}
	}
	
	private void changePrice() {
		println("Changing the price on a song.");
		String title = readLine("Enter the song's title: ");
		Song song = findSong(title);
		if (song == null) {
			println("Song not found: " + title);
		} else {
			Double price = readDouble("Enter the new price: ");
			song.setPrice(price);
		}
	}
	
}
