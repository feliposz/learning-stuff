class TesteString {
    public static void main(String args[]) {
        String[] palavras = "Aprender Java at� que � legal!".split(" ");

        for (String palavra : palavras) {
            System.out.println(palavra);
        }

        int comp = "banana".compareTo("ma��");
        if (comp < 0) {
            System.out.println("banana vem antes de ma��");
        } else if (comp > 0) {
            System.out.println("banana vem depois de ma��");
        } else {
            System.out.println("banana � igual a ma�� (WAT!?)");
        }
        
        String fruta = "abacaxi";
        fruta = fruta.toUpperCase().replace("A", "@");
        System.out.println("fruta = " + fruta);
        
        StringBuilder sb = new StringBuilder();        
        for (int i = 0; i < 100; i++ ) {
            if (i > 0) {
                sb.append(", ");
            }
            sb.append(i);
        }        
        System.out.println(sb.toString());
        
        String cidade = "  Sorocaba  ";
        System.out.println("substr -> " + cidade.contains("oca"));
        System.out.println("trim -> " + cidade.trim());
        System.out.println("isEmpty -> " + cidade.isEmpty());
        System.out.println("length -> " + cidade.length());
        
        for (int i = 0; i < cidade.length(); i++) {
            System.out.println(cidade.charAt(i));
        }
        
        inverter(cidade);
        inverter("Socorram-me, subi no �nibus em Marrocos");
        inverter("anotaram a data da maratona");
        
        inverterPalavras("Socorram-me, subi no �nibus em Marrocos");
        inverterPalavras("anotaram a data da maratona");
    }
    
    static void inverter(String s) {
        for (int i = s.length() - 1; i >= 0; i--) {
            System.out.print(s.charAt(i));
        }
        System.out.println();    
    }
    
    static void inverterPalavras(String s) {
        String[] palavras = s.split(" ");
        StringBuilder sb = new StringBuilder();
        for (int i = palavras.length - 1; i >= 0 ; i--) {
            sb.append(palavras[i]);
            if (i > 0) {
                sb.append(" ");
            }
        }
        System.out.println(sb);
    }
}