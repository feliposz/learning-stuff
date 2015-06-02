package br.com.felipo.banco.conta;

public class ValorInvalidoException extends RuntimeException {

	public ValorInvalidoException(String string) {
		super(string);
	}
	
	public ValorInvalidoException(double valor) {
		this("Valor inválido: " + valor);
	}

}
