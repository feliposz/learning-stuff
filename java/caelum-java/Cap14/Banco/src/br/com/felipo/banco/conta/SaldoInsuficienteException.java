package br.com.felipo.banco.conta;

public class SaldoInsuficienteException extends Exception {

	public SaldoInsuficienteException(String message) {
		super(message);
	}
}
