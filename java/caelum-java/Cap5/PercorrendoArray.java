class PercorrendoArray {

    static void imprimeArray(int[] array) {
        for (int x : array) { // for-each
            System.out.println(x);
        }
    }


    public static void main(String[] args) {
        int[] idades = new int[10];
        for (int i = 0; i < idades.length; i++) { // percorrendo um array com um índice
            idades[i] = i * 10;
        }
        imprimeArray(idades);
    }
}
