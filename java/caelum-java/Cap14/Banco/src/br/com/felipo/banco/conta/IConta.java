package br.com.felipo.banco.conta;

/**
 * Interface implementada pelas contas.
 * 
 * @author Felipo
 *
 */
public interface IConta {
	void deposita(double valor) throws ValorInvalidoException;

	public double getSaldo();

	void setLimite(double limite);

	void atualiza(double taxa);

	void saca(double valor) throws SaldoInsuficienteException;

	void transferePara(IConta destino, double valor) throws SaldoInsuficienteException, ValorInvalidoException;
}
