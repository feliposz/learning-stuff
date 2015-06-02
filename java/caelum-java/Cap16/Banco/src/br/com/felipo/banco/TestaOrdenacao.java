package br.com.felipo.banco;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import br.com.felipo.banco.conta.Conta;
import br.com.felipo.banco.conta.ContaCorrente;
import br.com.felipo.banco.conta.ContaPoupanca;

public class TestaOrdenacao {

	public static void main(String[] args) {
		List<Conta> contas = new ArrayList<>();
		// List<Conta> contas = new LinkedList<>();
		Random rnd = new Random();

		for (int i = 0; i < 6; i++) {
			Conta c = new ContaCorrente();
			c.setNumero(rnd.nextInt(1000));
			c.deposita(rnd.nextDouble() * 1000.0);
			contas.add(c);
		}

		System.out.println("Antes:");
		exibirFor(contas);

		Collections.sort(contas);

		System.out.println("Depois de ordenar:");
		exibirIter(contas);

		Collections.reverse(contas);

		System.out.println("Depois de inverter:");
		exibir(contas);

		Collections.rotate(contas, 1);

		System.out.println("Depois de rotacionar 1 vez:");
		exibir(contas);

		Collections.shuffle(contas);

		System.out.println("Depois de embaralhar:");
		exibir(contas);

		System.out.println("contas = " + contas);
	}

	static void exibir(List<Conta> contas) {
		// foreach para percorrer uma coleção
		for (Conta conta : contas) {
			System.out.println("conta: " + conta);
		}
	}

	static void exibirIter(List<Conta> contas) {
		// Antes do Java 5, usava-se um iterator.
		// Internamente, é isso que o foreach faz.
		
		Iterator<Conta> i = contas.iterator();
		while (i.hasNext()) {
			Conta conta = i.next();
			System.out.println("conta: " + conta);	
		}
	}

	static void exibirFor(List<Conta> contas) {
		for (int i = 0; i < contas.size(); i++) {
			System.out.println("conta " + i + ": " + contas.get(i));
		}
	}
	
}
