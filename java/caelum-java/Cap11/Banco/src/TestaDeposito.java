
public class TestaDeposito {
	public static void main(String[] args) {
		ContaPoupanca cp = new ContaPoupanca();
		try {
			cp.deposita(-100);
		} catch (ValorInvalidoException e) {
			System.out.println(e.getMessage());
		}
	}
}
