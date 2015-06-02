package br.com.felipo.banco.sistema;
import br.com.felipo.banco.conta.IConta;


public class AtualizadorDeContas {
	private double selic;
	private double saldoTotal;

	public AtualizadorDeContas(double selic) {
		this.selic = selic;
	}

	public void roda(IConta c) {
		System.out.println("Saldo anterior: " + c.getSaldo());
		c.atualiza(selic);
		saldoTotal += c.getSaldo();
		System.out.println("Saldo final: " + c.getSaldo());
	}

	public double getSaldoTotal() {
		return saldoTotal;
	}
}
