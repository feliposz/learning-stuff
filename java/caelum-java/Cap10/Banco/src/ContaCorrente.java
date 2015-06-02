public class ContaCorrente implements ContaTributavel {

	private ContaBase base = new ContaBase();

	public void atualiza(double taxa) {
		base.saldo += base.saldo * taxa * 2.0;
	}

	@Override
	public boolean saca(double valor) {
		return base.saca(valor + 0.10);
	}

	@Override
	public double calculaTributos() {
		return this.getSaldo() * 0.01;
	}

	@Override
	public void deposita(double valor) {
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
	public boolean transferePara(Conta destino, double valor) {
		return base.transferePara(destino, valor);
	}
}
