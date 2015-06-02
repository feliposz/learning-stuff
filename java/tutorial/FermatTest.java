/*
 * Exercise 5 in chapter 4 of Think Java
 * Important. The exercise doesn't state it clearly, but a, b and c must be positive integers AND n must be greater than 2.
 */

public class FermatTest {
    public static void main(String[] args)
    {
        for (int a = 1; a < 10; a++) {
            for (int b = 1; b < 10; b++) {
                for (int c = 1; c < 10; c++) {
                    for (int n = 3; n < 10; n++) {
                        checkFermat(a, b, c, n);
                    }
                }
            }
        }
    }
    
    private static void checkFermat(int a, int b, int c, int n) {
        boolean testFermat = raiseToPow(a, n) + raiseToPow(b, n) == raiseToPow(c, n);
        
        if (n != 2) {
            System.out.print(a + "^" + n + " + " + b + "^" + n + " = " + c + "^" + n + " --> ");
            if (testFermat) {
                System.out.println("Holy smokes, Fermat was wrong!");
                System.exit(1);
            } else {
                System.out.println("No, that doesn't work.");
            }
        }
    }
    
    private static int raiseToPow(int base, int exp) {
        return (int)Math.pow(base, exp);
    }
    
}
