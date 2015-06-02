import java.util.*;

public class TestaPerformance {
    
    public static void main (String[] args) {
        Collection<Integer> l = new ArrayList<>();
        Random rnd = new Random();
        int total = 100000;
        
        long insercao = System.currentTimeMillis();
        
        for (int i = 0; i < total; i++) {
            l.add(rnd.nextInt());
        }
        
        long pesquisa = System.currentTimeMillis();

        for (int i = 0; i < total; i++) {
            l.contains(rnd.nextInt());
        }
        
        long fim = System.currentTimeMillis();
        
        System.out.println("Classe: " + l.getClass().getName());
        System.out.println("Elementos: " + total);        
        System.out.println("Tempo inser��o: " + (pesquisa - insercao));
        System.out.println("Tempo pesquisa: " + (fim - pesquisa));
        
    }
}

/*
Classe: java.util.ArrayList
Elementos: 100000
Tempo inser��o: 15
Tempo pesquisa: 8311

Classe: java.util.HashSet
Elementos: 100000
Tempo inser��o: 27
Tempo pesquisa: 9
*/
