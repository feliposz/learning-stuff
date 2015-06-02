import java.io.*;
import java.util.*;

public class ScanSum {
	public static void main(String[] args) throws IOException {
		Scanner s = null;
		double total = 0;
		try {
			s = new Scanner(new BufferedReader(new FileReader("usnumbers.txt")));
			s.useLocale(Locale.US);			
			while (s.hasNext()) {
				if (s.hasNextDouble()) {
					total += s.nextDouble();
				} else {
					s.next();
				}
			}			
		} finally {
			if (s != null)
				s.close();
		}
		System.out.println(total);
	}
}