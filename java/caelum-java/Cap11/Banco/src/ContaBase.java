public class ContaBase {

	protected double saldo;
	private double limite;

	public void deposita(double valor) throws ValorInvalidoException {
		if (valor < 0) {
			throw new ValorInvalidoException(valor);
		} else {
			this.saldo += valor;
		}
	}

	public double getSaldo() {
		return this.saldo;
	}

	public void setLimite(double limite) {
		this.limite = limite;
	}

	public void saca(double valor) throws SaldoInsuficienteException {
		if (valor < this.saldo + this.limite) {
			this.saldo -= valor;
		} else {
			throw new SaldoInsuficienteException("Saldo insuficiente, tente um valor menor.");
		}
	}

	public void transferePara(Conta destino, double valor) throws SaldoInsuficienteException, ValorInvalidoException {
		this.saca(valor);
		destino.deposita(valor);
	}
}
