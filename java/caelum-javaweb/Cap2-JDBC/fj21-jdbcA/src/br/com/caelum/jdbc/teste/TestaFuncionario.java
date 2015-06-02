package br.com.caelum.jdbc.teste;

import java.util.List;

import br.com.caelum.jdbc.Util;
import br.com.caelum.jdbc.dao.FuncionarioDao;
import br.com.caelum.jdbc.modelo.Funcionario;

public class TestaFuncionario {

	public static void main(String[] args) {
		FuncionarioDao dao = new FuncionarioDao();
		dao.criarTabela();
		
		Funcionario novoFuncionario = new Funcionario();
		novoFuncionario.setNome(Util.randomString(10));
		novoFuncionario.setUsuario(Util.randomString(10));
		novoFuncionario.setSenha(Util.randomString(10));
		dao.adiciona(novoFuncionario);
		System.out.println("Funcion�rio criado - " + novoFuncionario);
		
		Funcionario pesquisado = dao.pesquisa(novoFuncionario.getId());
		System.out.println("Funcion�rio pesquisado - " + pesquisado);
		
		pesquisado.setNome(Util.randomString(10));
		pesquisado.setUsuario(Util.randomString(10));
		pesquisado.setSenha(Util.randomString(10));
		dao.altera(pesquisado);
		
		Funcionario alterado = dao.pesquisa(pesquisado.getId());		
		System.out.println("Funcion�rio alterado - " + alterado);
		
		System.out.println("Listagem de funcion�rios:");
		List<Funcionario> lista = dao.getLista();
		for (Funcionario funcionario : lista) {
			System.out.println("> " + funcionario);			
		}
		
		dao.remove(alterado);
		
		Funcionario removido = dao.pesquisa(alterado.getId());
		if (removido == null) {
			System.out.println("Funcion�rio removido - " + alterado.getId());
		} else {
			System.out.println("N�o foi removido - " + removido);
		}		
	}
}
