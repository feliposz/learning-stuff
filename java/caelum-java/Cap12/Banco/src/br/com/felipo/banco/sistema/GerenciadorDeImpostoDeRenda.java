package br.com.felipo.banco.sistema;
import br.com.felipo.banco.conta.Tributavel;

public class GerenciadorDeImpostoDeRenda {
	private double total;

	public double getTotal() {
		return total;
	}

	public void adiciona(Tributavel t) {
		System.out.println("Adicionando tributável: " + t);
		total += t.calculaTributos();
	}
}
