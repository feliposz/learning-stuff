public class Principal {
	public static void main(String[] args) {
		Conta conta = new ContaCorrente();
		conta.deposita(100.0);
		System.out.println("C: " + conta.getSaldo());
		
		Conta contaCorrente = new ContaCorrente();
		Conta contaPoupanca = new ContaPoupanca();
		
		contaCorrente.deposita(200.0);
		contaPoupanca.deposita(200.0);
		
		AtualizadorDeContas adc = new AtualizadorDeContas(0.01);
		adc.roda(conta);
		adc.roda(contaCorrente);
		adc.roda(contaPoupanca);
		
		contaCorrente.transferePara(contaPoupanca, 100.0);
		contaPoupanca.transferePara(conta, 10.0);
		
		System.out.println("C: " + conta.getSaldo());
		System.out.println("CC: " + contaCorrente.getSaldo());
		System.out.println("CP: " + contaPoupanca.getSaldo());
	}
}
