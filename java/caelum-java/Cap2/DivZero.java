class DivZero {
    public static void main(String[] args) {
	System.out.print("1.0 / 0.0 = ");
        System.out.println(1.0 / 0.0); // Infinity
	System.out.println("1 / 0 = ...");
        System.out.println(1 / 0);
           //Exception in thread "main" java.lang.ArithmeticException: / by zero
           //        at DivZero.main(DivZero.java:3)
    }
}