package br.com.caelum.jdbc.teste;

import java.util.List;

import br.com.caelum.jdbc.Util;
import br.com.caelum.jdbc.dao.ContatoDao;
import br.com.caelum.jdbc.modelo.Contato;

public class TestaLista {

	public static void main(String[] args) {
		// Obtém uma lista de contatos do banco (repare no objeto DAO temporário)
		List<Contato> contatos = new ContatoDao().getLista("eli");
		
		// Pecorre a lista para imprimir cada contato
		for (Contato contato : contatos) {
			
			System.out.println("Nome: " + contato.getNome() +
					" E-mail: " + contato.getEmail() + 
					" Endereço: " + contato.getEndereco() + 
					" Nascimento: " + Util.formatarData(contato.getDataNascimento()));		
			
		}
	}

}
