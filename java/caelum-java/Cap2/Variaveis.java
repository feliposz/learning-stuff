class Variaveis {
    public static void main(String[] args) {
        int idade = 33;
        System.out.println("idade = " + idade);

        int idadeAnoQueVem = idade + 1;
        System.out.println("idadeAnoQueVem = " + idadeAnoQueVem);

        int quatro = 2 + 2;
        System.out.println("quatro = " + quatro);

        int tres = 5 - 2;
        System.out.println("tres = " + tres);

        int oito = 4 * 2;
        System.out.println("oito = " + oito);

        int dezesseis = 64 / 4;
        System.out.println("dezesseis = " + dezesseis);

        int um = 5 % 2;
        System.out.println("um = " + um);

        double pi = 3.14;
        System.out.println("pi = " + pi);

        double x = 5 * 10;
        System.out.println("x = " + x);

        boolean verdade = true;
        System.out.println("verdade = " + verdade);

        boolean menorDeIdade = idade < 18;
        System.out.println("menorDeIdade = " + menorDeIdade);

        char letra = 'a';
        System.out.println("letra = " + letra);

        int i = 5;
        System.out.println("i = " + i);

        int j = i;
        System.out.println("j = " + j);

        i = i + 1;
        System.out.println("i = " + i);

        byte b = 127;
        System.out.println("b = " + b);

        short s = 32000;
        System.out.println("s = " + s);

        long l = 2000000000;
        System.out.println("l = " + l);

        float f1 = (float)0.987654321;
        System.out.println("f1 = " + f1);

        float f2 = (float)98765.4321;
        System.out.println("f2 = " + f2);

	char k = (char) 65.3; // double -> char!!! 
        System.out.println("k = " + k);

        if (idade < 18) { 
            System.out.println("menor de idade");
        } else {
            System.out.println("maior de idade");
        }    

        int cont = 0;
        while (cont < 3) {
            System.out.println("cont = " + cont);
            cont = cont + 1; 
        }   
        
        for (int m = 0; m < 3; m++) {
            System.out.println("m = " + m);
        }

	// byte -> short -> int -> long -> float (?) -> double
        byte _b = 1;
        short _s = _b;
        int _i = _s;
        long _l = _i;
        float _f = _l;
        double _d = _f;


    }
}