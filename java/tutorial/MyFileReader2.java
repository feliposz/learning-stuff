import java.io.*;
import java.util.*;

class MyFileReader2 {

	private static BufferedReader getUserFile(String prompt) {
		BufferedReader br = null;
		Scanner sc = new Scanner(System.in);
		while (br == null) {
			try {
				System.out.print(prompt);
				String fileName = sc.nextLine();
				br = new BufferedReader(new FileReader(fileName));
			} catch (IOException ex) {
				System.out.println("Bad file!");
			}
		}
		return br;
	}

	public static void main(String[] args) throws Exception {		
		BufferedReader br = getUserFile("Please, enter a file name: ");
		
		try {
			while (true) {
				String line = br.readLine();
				if (line == null) break;
				System.out.println(line);
			}
			br.close();
		} catch (IOException ex) {
			throw new Exception(ex);
		}
	}

}
