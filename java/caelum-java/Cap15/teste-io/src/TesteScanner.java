import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class TesteScanner {

	public static void main(String[] args) throws IOException {
		//Scanner sc = new Scanner(new FileInputStream("arquivo.txt"));
		Scanner sc = new Scanner(System.in);
		BufferedWriter bw = new BufferedWriter(new FileWriter(
				"saida.txt"));

		while (sc.hasNextLine()) {
			bw.write(sc.nextLine());
			bw.newLine();
		}
		bw.close();
		sc.close();
	}

}
