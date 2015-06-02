class ConversaoString {

    public static void main(String[] args) {
    
        int i = 10;
        double d = -2.34567;


        // Forma mais simples de converter (concatenação)
        String si = "" + i;

        // Outra forma de converter, usando formatação com 3 casas decimais e 10 casas no total
        String sd = String.format("%10.3f", d);

        System.out.println("si = " + si);
        System.out.println("sd = " + sd);

        int i2 = Integer.parseInt("256"); // Converter de string para inteiro
        double d2 = Double.parseDouble("14.35"); // Converter de string para double

        System.out.println("i2 = " + i2);
        System.out.println("d2 = " + d2);

        Integer boxedInt = new Integer(123); // Boxing direto
        Integer autoBoxedInt = 456; // Boxing automático (Java 5.0 em diante)

        //         unboxing explícito    unboxing automático
        int soma = boxedInt.intValue() + autoBoxedInt;
        System.out.println("soma = " + soma);
        
        try {
            int i3 = Integer.parseInt("abc");
        } catch (NumberFormatException e) {
            System.out.println(e);
        }

        try {
            double d3 = Double.parseDouble("14.3x5");
        } catch (NumberFormatException e) {
            System.out.println(e);
        }
        
        System.out.println(converteInteiro("900") + converteInteiro("80") + converteInteiro("7"));
    }
    
    static int converteInteiro(String s) {
        int valor = 0;        
        for (char c: s.toCharArray()) {
            valor = valor * 10 + Character.getNumericValue(c) - Character.getNumericValue('0'); // c - '0' também funciona!
        }        
        return valor;
    }
}
