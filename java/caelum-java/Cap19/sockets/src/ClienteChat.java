import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

//TODO: Reestruturar esse código para separar melhor as responsabilidades

public class ClienteChat {
	public static void main(String[] args) throws UnknownHostException,
			IOException {
		Socket cliente = new Socket("127.0.0.1", 12347);

		System.out.println("Conectado ao servidor.");
		
		BufferedReader entrada = new BufferedReader(new InputStreamReader(
				cliente.getInputStream()));
		
		Thread t = new Thread(new TratadorMensagens(entrada));
		t.start();
		
		PrintStream saida = new PrintStream(cliente.getOutputStream());
		Scanner teclado = new Scanner(System.in);
		while (teclado.hasNextLine()) {
			saida.println(teclado.nextLine());
		}
		teclado.close();
		cliente.close();
	}
}

class TratadorMensagens implements Runnable {

	private BufferedReader entrada;

	TratadorMensagens(BufferedReader entrada) {
		this.entrada = entrada;
	}

	@Override
	public void run() {
		String mensagem;
		try {
			while ((mensagem = this.entrada.readLine()) != null) {
				System.out.println("> " + mensagem);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}