package br.com.felipo.banco.conta;

/**
 * Conta base que oferece uma implementação padrão a ser usada pelos tipos específicos de conta.
 * 
 * @author Felipo
 *
 */
public class Conta implements Comparable<Conta> {

	private int numero;
	protected double saldo;
	private double limite;
	private String nome;
	
	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + numero;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		//if (getClass() != obj.getClass())
		//	return false;
		Conta other = (Conta) obj;
		if (numero != other.numero)
			return false;
		return true;
	}
	
	
	
	@Override
	public int compareTo(Conta outra) {
		//return Double.compare(this.saldo, outra.saldo);
		return Integer.compare(this.numero, outra.numero);
	}
	
	@Override
	public String toString() {
		return "Número: " + this.numero + " Titular: " + this.nome + " Saldo: " + this.saldo + "";
	}

	/**
	 * Efetua o depósito de um valor, mas não permite valores negativos.
	 * @param valor Valor que será depositado na conta.
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
	 * Realiza o saque de um determinado valor da conta. Não permite sacar além do limite definido na conta.
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
	 * @param destino Conta para a qual será feita a transferência.
	 * @param valor Valor a ser transferido.
	 * @throws SaldoInsuficienteException Se a conta de origem não tiver saldo suficiente.
	 * @throws ValorInvalidoException Se o valor transferido for negativo.
	 */
	public void transferePara(IConta destino, double valor) throws SaldoInsuficienteException, ValorInvalidoException {
		this.saca(valor);
		destino.deposita(valor);
	}

	public String getNome() {
		return this.nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
}
