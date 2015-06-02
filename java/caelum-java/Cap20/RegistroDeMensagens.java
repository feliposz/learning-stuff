import java.util.*;

public class RegistroDeMensagens {
    public static void main(String[] args) throws InterruptedException {
        //Collection<String> mensagens = new ArrayList<String>(30000);
        Collection<String> mensagens = new Vector<String>();
        //Collection<String> mensagens = new LinkedList<String>();
        
        Thread t1 = new Thread(new ProduzMensagens(0, 10000, mensagens));
        Thread t2 = new Thread(new ProduzMensagens(10000, 20000, mensagens));
        Thread t3 = new Thread(new ProduzMensagens(20000, 30000, mensagens));
        
        t1.start();
        t2.start();
        t3.start();
        
        t1.join();
        t2.join();
        t3.join();
        
        System.out.println("Threads produtoras de mensagens finalizadas!");
        
        for (int i = 0; i < 15000; i++) {
            if (!mensagens.contains("Mensagem " + i)) {
                throw new IllegalStateException("não encontrei a mensagem: " + i);
            }
        }
        
        if (mensagens.contains(null)) {
            throw new IllegalStateException("não devia ter null aqui dentro!");
        }
        
        System.out.println("Fim da execução com sucesso!");
        
    }
}

class ProduzMensagens implements Runnable {
    private int comeco;
    private int fim;
    private Collection<String> mensagens;
    
    public ProduzMensagens(int comeco, int fim, Collection<String> mensagens) {
        this.comeco = comeco;
        this.fim = fim;
        this.mensagens = mensagens;
    }
    
    public void run() {
        for (int i = comeco; i < fim; i++) {
            mensagens.add("Mensagem " + i);
        }
    }
}
