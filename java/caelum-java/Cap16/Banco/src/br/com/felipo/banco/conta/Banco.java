package br.com.felipo.banco.conta;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Banco {

	private List<Conta> contas = new ArrayList<>();
	private Map<String, Conta> mapaContas = new HashMap<>();
	
	public void adiciona(Conta conta) {
		this.contas.add(conta);
		this.mapaContas.put(conta.getNome(), conta);
	}
	
	public Conta pega(int x) {
		return this.contas.get(x);
	}
	
	public Conta buscaPorNome(String nome) {
		return this.mapaContas.get(nome);
	}
	
	public int pegaQuantidadeDeContas() {
		return this.contas.size();
	}
	
}
