package flyTunes;

public class Song {

	private String title;
	private String band;
	private double price;
	
	public Song(String newTitle, String newBand, double newPrice) {
		title = newTitle;
		band = newBand;
		price = newPrice;
	}
	
	public String getTitle() {
		return title;
	}
	
	public String getBand() {
		return band;
	}

	public double getPrice() {
		return price;
	}
	
	public void setPrice(double newPrice) {
		price = newPrice;
	}
	
	public String toString() {
		return "Song \"" + getTitle() + "\" by " + getBand() + " selling at $" + getPrice();  
	}
	
}
