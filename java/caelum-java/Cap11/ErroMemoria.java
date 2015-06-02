class ErroMemoria {
    public static void main(String args[]) {
        int[] numeros = new int[1000];
        
        while (true) {
            System.out.println("tamanho: " + numeros.length);
            numeros = new int[numeros.length * 2];
        }
        
        /*
        Exception in thread "main" java.lang.OutOfMemoryError: Java heap space
                at ErroMemoria.main(ErroMemoria.java:7)
         */
    }
}