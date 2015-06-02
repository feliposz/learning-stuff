package br.com.felipo.banco;

import java.util.HashMap;
import java.util.Map;

import br.com.felipo.banco.conta.Conta;
import br.com.felipo.banco.conta.ContaCorrente;
import br.com.felipo.banco.conta.ContaPoupanca;

public class TestaMapa {

	public static void main(String[] args) {
		Conta c1 = new ContaCorrente();
		c1.deposita(10000);
		Conta c2 = new ContaPoupanca();
		c2.deposita(3000);
		
		Map<String, Conta> mapaDeContas = new HashMap<String, Conta>();
		
		mapaDeContas.put("diretor", c1);
		mapaDeContas.put("gerente", c2);
		
		Conta contaDoDiretor = mapaDeContas.get("diretor");
		System.out.println(contaDoDiretor.getSaldo());
	}

}
