import java.io.*;

public class CopyLines {
	public static void main(String[] args) throws IOException {
		BufferedReader br = null;
		PrintWriter pw = null;
		try {
			br = new BufferedReader(new FileReader("xanadu.txt"));
			pw = new PrintWriter(new FileWriter("outagain.txt"));
			String s;
			while ((s = br.readLine()) != null) {
				pw.println(s);
			}
		} finally {
			if (br != null)
				br.close();
			if (pw != null)
				pw.close();
		}
	}
}