package br.com.felipo.banco.conta;

public class ContaCorrente implements ContaTributavel {

	private ContaBase base = new ContaBase();

	public void atualiza(double taxa) {
		base.saldo += base.saldo * taxa * 2.0;
	}

	@Override
	public void saca(double valor) throws SaldoInsuficienteException {
		base.saca(valor + 0.10);
	}

	@Override
	public double calculaTributos() {
		return this.getSaldo() * 0.01;
	}

	@Override
	public void deposita(double valor) throws ValorInvalidoException {
		base.deposita(valor);
	}

	@Override
	public double getSaldo() {
		return base.getSaldo();
	}

	@Override
	public void setLimite(double limite) {
		base.setLimite(limite);
	}

	@Override
	public void transferePara(Conta destino, double valor) throws SaldoInsuficienteException, ValorInvalidoException {
		base.transferePara(destino, valor);
	}
}
