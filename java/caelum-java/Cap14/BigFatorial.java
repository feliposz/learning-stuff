import java.math.BigInteger;
import java.math.BigDecimal;

class BigFatorial {
    public static void main(String[] args) {
    
        BigInteger a = BigInteger.ZERO; // a = 0
        BigInteger b = BigInteger.ONE; // b = 1
        BigInteger c = a.add(b); // c = a + b
        BigDecimal phi = BigDecimal.ZERO; // phi = 0.0
        
        for (int i = 0; i < 300; i++) {
            if (!a.equals(BigInteger.ZERO)) {
                int scale = b.toString().length();
                if (scale < 10) scale = 10;
                // phi = round(b / a, scale)
                phi = new BigDecimal(b).divide(new BigDecimal(a), scale, BigDecimal.ROUND_HALF_UP);
            }
            System.out.println(i + "! = " + a);
            System.out.println("phi = " + phi);
            a = b;
            b = c;
            c = a.add(b); // c = a + b
        }
    }
}
