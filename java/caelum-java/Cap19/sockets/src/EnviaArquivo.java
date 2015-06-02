
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class EnviaArquivo {

	public static void main(String[] args) throws UnknownHostException, IOException {
		Socket cliente = new Socket("127.0.0.1", 12346);
		InputStream arquivo = new FileInputStream("origem.txt");

		OutputStream saida = cliente.getOutputStream();

		byte[] buffer = new byte[1024];
		
		while (arquivo.read(buffer) > 0) {
			saida.write(buffer);
		}
		
		arquivo.close();
		cliente.close();		
	}

}
