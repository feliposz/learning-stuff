import java.io.*;

public class CopyBytes {
	public static void main(String[] args) throws IOException {
		FileInputStream fis = null;
		FileOutputStream fos = null;
		try {
			fis = new FileInputStream("xanadu.txt");
			fos = new FileOutputStream("outagain.txt");
			int c;
			while ((c = fis.read()) != -1) {
				fos.write(c);
			}
		} finally {
			if (fis != null)
				fis.close();
			if (fos != null)
				fos.close();
		}
	}
}