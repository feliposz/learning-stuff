/* Project Euler - Problem 3 solution
 * Using the BigInteger class, since there are no 
 */
import java.math.BigInteger;

class Euler3 {
	public static void main(String[] args) {
		//BigInteger num = new BigInteger("13195");
		BigInteger num = new BigInteger("600851475143");
		BigInteger f;
		BigInteger last = BigInteger.ZERO;
		for (f = new BigInteger("2"); num.compareTo(BigInteger.ONE) > 0; f = f.add(BigInteger.ONE)) {
			//System.out.println("DEBUG => f: " + f + " last: " + last + " num: " + num);
			if (num.mod(f).compareTo(BigInteger.ZERO) == 0) {
				//System.out.println("Divisible by f: " + f + " num: " + num);
				while (num.mod(f).compareTo(BigInteger.ZERO) == 0 && num.compareTo(BigInteger.ONE) > 0) {
					num = num.divide(f);
				}
				last = new BigInteger(f.toString());
			}
		}
		System.out.println("last: " + last);
	}
}
