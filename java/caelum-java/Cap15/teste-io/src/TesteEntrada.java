import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class TesteEntrada {

	public static void main(String[] args) throws IOException {
		// InputStream is = System.in;
		BufferedReader br = new BufferedReader(new InputStreamReader(
				new FileInputStream("arquivo.txt")));

		System.out.println("Digite sua mensagem:");

		String msg;

		do {
			msg = br.readLine();
			if (msg != null) {
				System.out.println("Sua mensagem: " + msg);
			}
		} while (msg != null);

		br.close();
	}

}
