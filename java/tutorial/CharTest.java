class CharTest {

  public static void main(String[] args) {
    for (char ch = 'à'; ch <= 'ÿ'; ch++) {
    	System.out.println(ch + " -> " + Character.toUpperCase(ch));
    }
  }

}