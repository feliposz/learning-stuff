package br.com.caelum.jdbc.teste;

import java.util.Calendar;
import java.util.GregorianCalendar;

import br.com.caelum.jdbc.dao.ContatoDao;
import br.com.caelum.jdbc.modelo.Contato;

public class TestaInserir {

	public static void main(String[] args) {
		// Cria um objeto contato que irá conter os dados a serem inseridos no banco
		Contato contato = new Contato();
		// Data padrão ocidental (calendário gregoriano
		Calendar calendar = new GregorianCalendar(1981, 5, 13);
		
		// Preenche os campos do objeto
		contato.setNome("Felipo");
		contato.setEmail("feliposz@gmail.com");
		contato.setEndereco("Ibiti");
		contato.setDataNascimento(calendar);
		
		// Utiliza a camada DAO (Data Access Objects) para gravar no banco
		ContatoDao dao = new ContatoDao();
		dao.adiciona(contato);
		
		System.out.println("Gravado!");
	}

}
