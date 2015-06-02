package br.com.felipo.banco;

import br.com.felipo.banco.conta.Banco;
import br.com.felipo.banco.conta.Conta;
import br.com.felipo.banco.conta.ContaCorrente;
import br.com.felipo.banco.conta.ContaPoupanca;

public class TesteBanco {
	public static void main(String[] args) {
		Banco banco = new Banco();
		
		Conta c1 = new ContaCorrente();
		c1.setNome("Tywin");
		banco.adiciona(c1);

		Conta c2 = new ContaCorrente();
		c2.setNome("Stannis");
		banco.adiciona(c2);

		Conta c3 = new ContaPoupanca();
		c3.setNome("Mace");
		banco.adiciona(c3);
		
		banco.pega(0).deposita(1000.0);
		
		Conta c = banco.buscaPorNome("Tywin");
		System.out.println(c);
		
		System.out.println(banco.pegaQuantidadeDeContas());
		
		
	}
}
