public abstract class Conta {

	protected double saldo;
	private double limite;

	void deposita(double valor) {
		this.saldo += valor;
	}

	public double getSaldo() {
		return this.saldo;
	}

	public void setLimite(double limite) {
		this.limite = limite;
	}

	abstract public void atualiza(double taxa);

	public boolean saca(double valor) {
		if (valor < this.saldo + this.limite) {
			this.saldo -= valor;
			return true;
		}
		return false;
	}

	public boolean transferePara(Conta destino, double valor) {
		if (this.saca(valor)) {
			destino.deposita(valor);
			return true;
		}
		return false;
	}
}
