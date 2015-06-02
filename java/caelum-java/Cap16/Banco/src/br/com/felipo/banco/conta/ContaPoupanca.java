package br.com.felipo.banco.conta;

public class ContaPoupanca extends Conta implements IConta {

	public void atualiza(double taxa) {
		this.saldo += this.saldo * taxa * 3.0;
	}

}
