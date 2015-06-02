import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;


public class RecebeArquivo {

	public static void main(String[] args) throws IOException {
		ServerSocket servidor = new ServerSocket(12346);
		Socket cliente = servidor.accept();
		InputStream arquivo = cliente.getInputStream();
		
		OutputStream saida = new FileOutputStream("destino.txt");
		
		byte[] buffer = new byte[1024];
		while (arquivo.read(buffer) > 0){
			saida.write(buffer);
		}
		
		saida.close();
		arquivo.close();
		servidor.close();
	}

}
