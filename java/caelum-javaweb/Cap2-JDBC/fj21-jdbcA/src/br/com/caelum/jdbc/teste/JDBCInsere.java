package br.com.caelum.jdbc.teste;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Calendar;

import br.com.caelum.jdbc.ConnectionFactory;

// Exemplo de como dar um INSERT direto no banco de dados com SQL (compare com a TestaInserir que faz uso de DAO)
public class JDBCInsere {

	public static void main(String[] args) throws SQLException {

		// Obtém a conexão
		

		// O bloco try aqui é para garantir que a conexão seja fechada
		// Não é necessário o finally, pois assim que o bloco termina, ele é destruído
		// e a conexão é fechada.
		try (Connection con = new ConnectionFactory().getConnectionMySQL()) {

			// Gera a query que será usada
			// OBS: Repare que não são concatenados os valores DENTRO da query
			String sql = " INSERT INTO contatos "
					+ " (nome, email, endereco, dataNascimento ) "
					+ " VALUES ( ?, ?, ?, ? ) ";

			// No lugar disso, é preparado um "statement" (comando) a ser
			// executado com os parâmetros subtituíveis
			PreparedStatement stmt = con.prepareStatement(sql);
			// NOTA: Nunca use a classe Statement diretamente, pois você
			// precisará tratar uma série de problemas como evitar SQL injection.

			// Os parâmetros são passados de forma posicional
			stmt.setString(1, "Caelum");
			stmt.setString(2, "contato@caelum.com.br");
			stmt.setString(3, "R. Vergueiro 3185 cj57");
			stmt.setDate(4, new java.sql.Date( // Data e hora atual em
												// milisegundos é convertida
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
