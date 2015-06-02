package br.com.caelum.jdbc.teste;

import br.com.caelum.jdbc.Util;
import br.com.caelum.jdbc.dao.ContatoDao;
import br.com.caelum.jdbc.modelo.Contato;
 

public class TestaPesquisa {

	public static void main(String[] args) {
		ContatoDao dao = new ContatoDao();
		Contato contato = dao.pesquisar(1);

		if (contato != null) {
			System.out.println("Contato: " + contato.getNome() + " Email: "
					+ contato.getEmail() + " Endereço: "
					+ contato.getEndereco() + " Nascimento: "
					+ Util.formatarData(contato.getDataNascimento()));
		} else {
			System.out.println("Não encontrado!");
		}

	}

}
