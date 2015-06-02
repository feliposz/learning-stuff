
public class TestaSaque {
	public static void main(String[] args) throws ValorInvalidoException {
		ContaCorrente cc = new ContaCorrente();
		
		cc.deposita(100.0);
		cc.setLimite(100.0);
		
		try { 
			cc.saca(1000.0);
		} catch (SaldoInsuficienteException e) {
			System.out.println(e.getMessage());
		}
	}
}
