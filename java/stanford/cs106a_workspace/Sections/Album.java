public class Album {
	
	private String title;
	private String band;
	private int stock;
	
	public Album(String title, String band, int stock) {
		this.title = title;
		this.band = band;
		this.stock = stock;
	}
	
	public String getTitle() {
		return title;
	}
	
	public String getBand() {
		return band;
	}
	
	public int getStock() {
		return stock;
	}
	
	public String toString() {
		return "Album: \"" + getTitle() + "\" by " + getBand() + 
			" has " + getStock() + " available in stock."; 
	}
	
}
