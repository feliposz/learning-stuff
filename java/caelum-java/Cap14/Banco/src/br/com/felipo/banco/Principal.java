package br.com.felipo.banco;
import br.com.felipo.banco.conta.IConta;
import br.com.felipo.banco.conta.ContaCorrente;
import br.com.felipo.banco.conta.ContaPoupanca;
import br.com.felipo.banco.conta.SaldoInsuficienteException;
import br.com.felipo.banco.conta.SeguroDeVida;
import br.com.felipo.banco.conta.ValorInvalidoException;
import br.com.felipo.banco.sistema.AtualizadorDeContas;
import br.com.felipo.banco.sistema.GerenciadorDeImpostoDeRenda;

public class Principal {
	public static void main(String[] args) throws ValorInvalidoException {
		IConta conta = new ContaCorrente();
		conta.deposita(100.0);
		System.out.println("C: " + conta.getSaldo());

		ContaCorrente contaCorrente = new ContaCorrente();
		ContaPoupanca contaPoupanca = new ContaPoupanca();

		contaCorrente.deposita(200.0);
		contaPoupanca.deposita(200.0);

		AtualizadorDeContas adc = new AtualizadorDeContas(0.01);
		adc.roda(conta);
		adc.roda(contaCorrente);
		adc.roda(contaPoupanca);

		try {
			contaCorrente.transferePara(contaPoupanca, 100.0);
			contaPoupanca.transferePara(conta, 10.0);
			System.out.println("Transferências realizadas.");
		} catch (SaldoInsuficienteException e) {
			e.printStackTrace();
		}

		System.out.printf("C: %.2f\n", conta.getSaldo());
		System.out.printf("CC: %.2f\n", contaCorrente.getSaldo());
		System.out.printf("CP: %.2f\n", contaPoupanca.getSaldo());

		SeguroDeVida seguro = new SeguroDeVida();

		GerenciadorDeImpostoDeRenda ir = new GerenciadorDeImpostoDeRenda();
		ir.adiciona(contaCorrente);
		ir.adiciona(seguro);
		System.out.printf("IR: %.2f", ir.getTotal());
	}
}
