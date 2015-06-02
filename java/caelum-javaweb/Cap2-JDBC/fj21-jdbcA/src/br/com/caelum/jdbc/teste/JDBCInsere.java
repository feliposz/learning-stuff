package br.com.caelum.jdbc.teste;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Calendar;

import br.com.caelum.jdbc.ConnectionFactory;

// Exemplo de como dar um INSERT direto no banco de dados com SQL (compare com a TestaInserir que faz uso de DAO)
public class JDBCInsere {

	public static void main(String[] args) throws SQLException {

		// Obt�m a conex�o
		

		// O bloco try aqui � para garantir que a conex�o seja fechada
		// N�o � necess�rio o finally, pois assim que o bloco termina, ele � destru�do
		// e a conex�o � fechada.
		try (Connection con = new ConnectionFactory().getConnectionMySQL()) {

			// Gera a query que ser� usada
			// OBS: Repare que n�o s�o concatenados os valores DENTRO da query
			String sql = " INSERT INTO contatos "
					+ " (nome, email, endereco, dataNascimento ) "
					+ " VALUES ( ?, ?, ?, ? ) ";

			// No lugar disso, � preparado um "statement" (comando) a ser
			// executado com os par�metros subtitu�veis
			PreparedStatement stmt = con.prepareStatement(sql);
			// NOTA: Nunca use a classe Statement diretamente, pois voc�
			// precisar� tratar uma s�rie de problemas como evitar SQL injection.

			// Os par�metros s�o passados de forma posicional
			stmt.setString(1, "Caelum");
			stmt.setString(2, "contato@caelum.com.br");
			stmt.setString(3, "R. Vergueiro 3185 cj57");
			stmt.setDate(4, new java.sql.Date( // Data e hora atual em
												// milisegundos � convertida
												// para uma data que o SQL
												// entende
					Calendar.getInstance().getTimeInMillis()));

			// Executa o comando
			stmt.execute();
			stmt.close();

			System.out.println("Gravado!");
			
		} catch (SQLException e) {
			System.out.println(e);
		}
	}
}
