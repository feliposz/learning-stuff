import java.io.*;

class MyFileReader {

	public static void main(String[] args) throws IOException, FileNotFoundException {
		BufferedReader br = new BufferedReader(
				new FileReader("myfile.txt")
			);

		while (true) {
			String line = br.readLine();
			if (line == null) break;
			System.out.println(line);
		}
		
		br.close();
	}
	
}