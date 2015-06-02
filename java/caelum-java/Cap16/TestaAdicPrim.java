import java.util.*;

class TestaAdicPrim {
    public static void main(String[] args) {
        List<Integer> l = new ArrayList<>();
        //List<Integer> l = new LinkedList<>();
        int total = 30000;
        
        long insercao = System.currentTimeMillis();
        
        for (int i = 0; i < total; i++) {
            l.add(0, i);
        }
        
        long busca = System.currentTimeMillis();

        for (int i = 0; i < total; i++) {
            l.get(i);
        }
        
        long fim = System.currentTimeMillis();
        
        System.out.println("Classe: " + l.getClass().getName());
        System.out.println("Tempo inser��o: " + (busca - insercao));
        System.out.println("Tempo busca: " + (fim - busca));
    }
}

/*
Classe: java.util.LinkedList
Tempo inser��o: 9
Tempo busca: 383

Classe: java.util.ArrayList
Tempo inser��o: 105
Tempo busca: 1
*/