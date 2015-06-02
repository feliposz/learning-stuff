package br.com.caelum.jdbc.teste;

import java.util.List;

import br.com.caelum.jdbc.Util;
import br.com.caelum.jdbc.dao.ContatoDao;
import br.com.caelum.jdbc.modelo.Contato;

public class TestaLista {

	public static void main(String[] args) {
		// Obt�m uma lista de contatos do banco (repare no objeto DAO tempor�rio)
		List<Contato> contatos = new ContatoDao().getLista("eli");
		
		// Pecorre a lista para imprimir cada contato
		for (Contato contato : contatos) {
			
			System.out.println("Nome: " + contato.getNome() +
					" E-mail: " + contato.getEmail() + 
					" Endere�o: " + contato.getEndereco() + 
					" Nascimento: " + Util.formatarData(contato.getDataNascimento()));		
			
		}
	}

}
