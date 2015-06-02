class Cap3Desafio {
    public static void main(String[] args) {
        int a = 0, b = 1;
        while (b-a < 100) {
            System.out.println(a);
            b = a + b;
            a = b - a;
        }
    }
}
