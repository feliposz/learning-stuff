class TesteErro {
    public static void main(String args[]) {
        System.out.println("inicio main");
        try {
            metodo1();
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("erro: " + e);
        }
        System.out.println("fim main");
    }

    static void metodo1() {
        System.out.println("inicio metodo1");
        metodo2();
        System.out.println("fim metodo1");
    }

    static void metodo2() {
        System.out.println("inicio metodo2");
        int[] array = new int[10];
        for (int i = 0; i <= 15; i++) {
            array[i] = i;
            System.out.println(i);
        }
        System.out.println("fim metodo2");
    }
}
