package br.com.felipo.banco;
import br.com.felipo.banco.conta.ContaPoupanca;
import br.com.felipo.banco.conta.ValorInvalidoException;


public class TestaDeposito {
	public static void main(String[] args) {
		ContaPoupanca cp = new ContaPoupanca();
		try {
			cp.deposita(-100);
		} catch (ValorInvalidoException e) {
			System.out.println(e.getMessage());
		}
	}
}
