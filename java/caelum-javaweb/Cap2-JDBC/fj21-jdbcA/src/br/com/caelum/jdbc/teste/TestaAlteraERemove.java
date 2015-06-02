package br.com.caelum.jdbc.teste;

import java.util.Calendar;
import java.util.List;

import br.com.caelum.jdbc.dao.ContatoDao;
import br.com.caelum.jdbc.modelo.Contato;

public class TestaAlteraERemove {

	public static void main(String[] args) {
		ContatoDao dao = new ContatoDao();
		
		Contato novoContato = new Contato();
		novoContato.setNome("XXXYYYZZZ");
		novoContato.setEmail("x@y.z");
		novoContato.setEndereco("X: Y, Z");
		novoContato.setDataNascimento(Calendar.getInstance());
		dao.adiciona(novoContato);

		System.out.println("Adicionado: " + novoContato.getId());
		
		List<Contato> contatos = dao.getLista(novoContato.getNome()); 
		Contato pesquisado = contatos.get(0);
		
		System.out.println("Pesquisado: " + pesquisado.getNome());
		
		pesquisado.setNome("AAABBBCCC");
		pesquisado.setEmail("a@b.c");
		pesquisado.setEndereco("A: B, C");
		
		dao.altera(pesquisado);
		
		Contato alterado = dao.pesquisar(pesquisado.getId());
		
		System.out.println("Alterado: " + alterado.getNome());
		
		dao.remove(alterado);
		
		System.out.println("Excluído: " + alterado.getId());

	}

}
