package br.com.felipo.banco.conta;

public class ContaCorrente extends Conta implements ContaTributavel {


	public void atualiza(double taxa) {
		this.saldo += this.saldo * taxa * 2.0;
	}

	@Override
	public void saca(double valor) throws SaldoInsuficienteException {
		super.saca(valor + 0.10);
	}

	@Override
	public double calculaTributos() {
		return this.getSaldo() * 0.01;
	}

}
