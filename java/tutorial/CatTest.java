import java.io.*;

public class CatTest {

	public static void cat(File file) {
	    RandomAccessFile input = null;
	    String line = null;
	
	    try {
	        input = new RandomAccessFile(file, "r");
	        while ((line = input.readLine()) != null) {
	            System.out.println(line);
	        }
	        return;
	    } catch (FileNotFoundException e) {
	    	System.err.println("FileNotFoundException: " + e.getMessage());
	    } catch (IOException e) {
	    	System.err.println("IOException: " + e.getMessage());
	    } finally {
	        if (input != null) {
	            try {
	            	input.close();
	            }  catch (IOException e) {
	    	    	System.err.println("IOException: " + e.getMessage());
	    	    }
	        }
	    }
	}

	public static void main(String[] args) {
		File f = new File("InFile.txt");
		cat(f);
	}
}