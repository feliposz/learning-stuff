package br.com.felipo.banco.conta;

/**
 * Conta base que oferece uma implementa��o padr�o a ser usada pelos tipos espec�ficos de conta.
 * 
 * @author Felipo
 *
 */
public class Conta {

	private int numero;
	protected double saldo;
	private double limite;
	
	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}
	
	@Override
	public boolean equals(Object obj) {
		Conta c = (Conta) obj;
		return this.numero == c.numero;
	}

	/**
	 * Efetua o dep�sito de um valor, mas n�o permite valores negativos.
	 * @param valor Valor que ser� depositado na conta.
	 * @throws ValorInvalidoException Se o valor for negativo.
	 */
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

	/**
	 * Realiza o saque de um determinado valor da conta. N�o permite sacar al�m do limite definido na conta.
	 * 
	 * @param valor Valor a ser sacado da conta.
	 * @throws SaldoInsuficienteException Se o valor do saque for maior que o saldo e o limite da conta. 
	 */
	public void saca(double valor) throws SaldoInsuficienteException {
		if (valor < this.saldo + this.limite) {
			this.saldo -= valor;
		} else {
			throw new SaldoInsuficienteException("Saldo insuficiente, tente um valor menor.");
		}
	}

	/**
	 * Transfere de uma conta para outra, respeitando se a conta de origem possui saldo.
	 * @param destino Conta para a qual ser� feita a transfer�ncia.
	 * @param valor Valor a ser transferido.
	 * @throws SaldoInsuficienteException Se a conta de origem n�o tiver saldo suficiente.
	 * @throws ValorInvalidoException Se o valor transferido for negativo.
	 */
	public void transferePara(IConta destino, double valor) throws SaldoInsuficienteException, ValorInvalidoException {
		this.saca(valor);
		destino.deposita(valor);
	}
}
