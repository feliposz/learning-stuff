import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import java.io.IOException;

class Servidor {

    public static void main(String[] args) throws IOException {

        ServerSocket servidor = new ServerSocket(12345);
        System.out.println("Porta 12345 aberta");

        while (true) {
            Socket cliente = servidor.accept();
            TratamentoConexao tratamento = new TratamentoConexao(cliente);
            Thread t = new Thread(tratamento);
            t.start();
        }

        //servidor.close();

    }
}

class TratamentoConexao implements Runnable {

    private Socket cliente;

    TratamentoConexao(Socket cliente) {
        this.cliente = cliente;
    }

    public void run()  {
        try {
            System.out.println("Nova conexão com o cliente " +
                this.cliente.getInetAddress().getHostAddress());

            Scanner s = new Scanner(this.cliente.getInputStream());
            while (s.hasNextLine()) {
                System.out.println(s.nextLine());
            }
            s.close();
            this.cliente.close();
        } catch (IOException e) {
            System.out.println(e);
        }
    }
}