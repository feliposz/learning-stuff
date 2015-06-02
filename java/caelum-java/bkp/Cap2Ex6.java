class Cap2Ex6 {
    public static void main(String[] args) {
        int a = 0;
        int b = 1;
        while (true) {
           System.out.println(a);
           if (a > 100) {
               break;
           }
           int c = a + b;
           a = b;
           b = c;
        }
    }
}