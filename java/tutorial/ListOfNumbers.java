import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ListOfNumbers {

	private List<Integer> list;
	private static final int SIZE = 10;
	
	public ListOfNumbers() {
		list = new ArrayList<Integer>(SIZE);
		for (int i=0; i<SIZE; i++) {
			list.add(new Integer(i));
		}
	}
	
	public void writeList() {
		PrintWriter out = null;
		try {
			System.out.println("writeList(): Entering try statement");
			out = new PrintWriter(new FileWriter("OutFile.txt"));
			for (int i=0; i<list.size(); i++) {
				out.println("Value at: " + i + " = " + list.get(i));
			}
		} catch (ArrayIndexOutOfBoundsException e) {
			System.err.println("writeList(): ArrayIndexOutOfBoundsExceptions: " + e.getMessage());
		} catch (IOException e) {
			System.err.println("writeList(): Caught IOException: " + e.getMessage());
		} finally {
			if (out != null) {
				System.out.println("writeList(): Closing PrintWriter.");
				out.close();
			} else {
				System.out.println("writeList(): PrintWriter not open.");
			}
		}
	}
	
	public void readList() {
		BufferedReader in = null;
		try {
			System.out.println("readList(): Entering try statement");
			in = new BufferedReader(new FileReader("InFile.txt"));
			while (in.ready()) {
				list.add(Integer.parseInt(in.readLine()));
			}
			in.close();
		} catch (FileNotFoundException e) {
			System.err.println("readList(): FileNotFoundException: " + e.getMessage());
		} catch (IOException e) {
			System.err.println("readList(): IOException: " + e.getMessage());
		} catch (NumberFormatException e) {			
			System.err.println("readList(): NumberFormatException: " + e.getMessage());
		}
	}
	
	public void writeList2() throws ArrayIndexOutOfBoundsException, IOException {
		PrintWriter out = null;
		out = new PrintWriter(new FileWriter("OutFile.txt"));
		for (int i=0; i<SIZE; i++) {
			out.println("Value at: " + i + " = " + list.get(i));
		}
		out.close();
	}

	public static void main(String[] args)
	{
		ListOfNumbers lon = new ListOfNumbers();
		lon.readList();
		lon.writeList();
		/*try {
			lon.writeList2();
 		} catch (ArrayIndexOutOfBoundsException e) {
			System.err.println("main(): ArrayIndexOutOfBoundsExceptions: " + e.getMessage());
		} catch (IOException e) {
			System.err.println("main(): Caught IOException: " + e.getMessage());
		}*/
	}
}