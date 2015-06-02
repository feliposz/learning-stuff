import java.util.*;

class TestaSet {
    public static void main(String[] args) {
        //Set<String> frutas = new HashSet<>();
        //Set<String> frutas = new LinkedHashSet<>();
        Set<String> frutas = new TreeSet<>();
        
        frutas.add("laranja");
        frutas.add("maçã");
        frutas.add("banana");
        frutas.add("abacaxi");
        frutas.add("goiaba");
        frutas.add("melancia");
        frutas.add("morango");
        frutas.add("manga");
        
        if (frutas.contains("goiaba")) {
            System.out.println("Encontrado: goiaba");
        }
        
        if (!frutas.contains("cebola")) {
            System.out.println("Não encontrado: cebola");
        }
        
        for (String fruta : frutas) {
            System.out.println("fruta: " + fruta);
        }
        
        System.out.println("frutas = " + frutas);
        
        if (frutas instanceof TreeSet) {
            TreeSet ts = (TreeSet) frutas;
            System.out.println("primeira fruta: " + ts.first());
            System.out.println("última fruta: " + ts.last());
        }
    }
}