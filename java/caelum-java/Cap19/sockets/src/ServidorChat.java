import java.io.IOException;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// TODO: Reestruturar esse código para separar melhor as responsabilidades

public class ServidorChat {
	public static void main(String[] args) throws IOException {
		ServerSocket servidor = new ServerSocket(12347);
		System.out.println("Servidor iniciado na porta 12347.");

		while (true) {
			TratadorClienteChat tratador = new TratadorClienteChat(
					servidor.accept());
			Thread t = new Thread(tratador);
			t.start();
		}
	}
}

class TratadorClienteChat implements Runnable {

	private Socket cliente;

	private static List<PrintStream> saidaClientes = new ArrayList<PrintStream>();

	TratadorClienteChat(Socket cliente) {
		this.cliente = cliente;
	}

	@Override
	public void run() {

		System.out.println("Cliente conectou.");

		Scanner entrada;
		try {
			PrintStream saida = new PrintStream(this.cliente.getOutputStream());
			saidaClientes.add(saida);
			entrada = new Scanner(this.cliente.getInputStream());
			while (entrada.hasNext()) {
				replicar(entrada.nextLine());
			}
			saidaClientes.remove(saida);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	private void replicar(String mensagem) {
		System.out.println("Clientes conectados: " + saidaClientes.size());
		System.out.println("Mensagem do cliente: " + mensagem);
		for (PrintStream saida : saidaClientes) {
			saida.println(mensagem);		
		}
	}

}