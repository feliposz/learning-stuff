package br.com.caelum.jdbc.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import br.com.caelum.jdbc.ConnectionFactory;
import br.com.caelum.jdbc.modelo.Contato;

// Esta classe encapsula toda a l�gica para acesso a dados
// O mundo "exterior" n�o precisa saber das instru��es SQL embutidas aqui
public class ContatoDao {

	// Ao criar o objeto, prepara a conex�o ao banco
	private Connection connection;

	public ContatoDao() {
		this.connection = new ConnectionFactory().getConnection();
	}

	// Encapsula o c�digo para inserir um contato no banco
	// Um "modelo" JavaBean de um contato � passado como par�metro

	public void adiciona(Contato contato) {

		String sqlInsert, sqlId;

		if (this.connection instanceof oracle.jdbc.driver.OracleConnection) {
			sqlInsert = " insert into contatos "
					+ " (id, nome, email, endereco, dataNascimento) "
					+ " values (seq_contatos.nextval, ?, ?, ?, ?) ";

			sqlId = " select seq_contatos.currval from dual ";
		} else if (this.connection instanceof com.mysql.jdbc.JDBC4Connection) {
			sqlInsert = " insert into contatos "
					+ " (nome, email, endereco, dataNascimento) "
					+ " values (?, ?, ?, ?) ";

			sqlId = " select last_insert_id() ";
		} else {
			sqlInsert = " insert into contatos "
					+ " (nome, email, endereco, dataNascimento) "
					+ " values (?, ?, ?, ?) ";

			sqlId = null;
		}

		PreparedStatement stmt;
		try {
			// Prepara o comando para execu��o
			stmt = connection.prepareStatement(sqlInsert);

			// Substitui os par�metros por valores reais
			// (a partir do JavaBean "contato")
			stmt.setString(1, contato.getNome());
			stmt.setString(2, contato.getEmail());
			stmt.setString(3, contato.getEndereco());
			stmt.setDate(4, new Date(contato.getDataNascimento()
					.getTimeInMillis()));

			// Executa o comando
			stmt.execute();
			stmt.close();

			// Esta foi a forma mais simples que encontrei para
			// determinar o ID gerado no INSERT acima.
			// OBSERVA��O: Decidi manter este exemplo aqui para refer�ncia.
			// H� um m�todo melhor, independente de SGBD sendo usado na classe FuncionarioDao, m�todo adiciona()
			if (sqlId != null) {
				stmt = connection.prepareStatement(sqlId);
				ResultSet rs = stmt.executeQuery();
				if (rs.next()) {
					contato.setId(rs.getLong(1));
				}
				stmt.close();
			}
	
			
		} catch (SQLException e) {
			throw new DaoException(e);
		}

	}

	// Captura a sa�da de um SELECT e transforma em uma lista de objetos
	// "Contato"
	public List<Contato> getLista(String filtroNome) {
		List<Contato> contatos = new ArrayList<>();

		String sql = " select * from contatos where nome like ? ";

		try {
			PreparedStatement stmt = connection.prepareStatement(sql);

			stmt.setString(1, "%" + filtroNome + "%");

			// Executa a query returnando um conjunto (ResultSet) de dados
			ResultSet rs = stmt.executeQuery();

			// Percorre o "cursor" at� chegar ao fim (s� � poss�vel avan�ar no
			// cursor, n�o recuar)
			while (rs.next()) {
				// Gera um novo objeto
				Contato contato = new Contato();

				// Preenche o objeto com os dados conforme dados atuais do
				// cursor SQL
				contato.setId(rs.getLong("id"));
				contato.setNome(rs.getString("nome"));
				contato.setEmail(rs.getString("email"));
				contato.setEndereco(rs.getString("endereco"));

				// � necess�rio converter a data para uma inst�ncia de Calendar
				Calendar data = Calendar.getInstance();
				data.setTime(rs.getDate("dataNascimento"));
				contato.setDataNascimento(data);

				// Insere na lista
				contatos.add(contato);
			}

			stmt.close();

			// Devolve a lista criada
			return contatos;

		} catch (SQLException e) {
			throw new DaoException(e);
		}
	}

	public Contato pesquisar(long id) {
		Contato contato = null;

		String sql = " select * from contatos where id = ? ";

		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			try {
				stmt.setLong(1, id);
			} catch (SQLException e) {
				// O Access n�o suporta Long, ent�o tive que fazer esta "adapta��o"
				stmt.setInt(1, (int)id);
			}

			ResultSet rs = stmt.executeQuery();

			// S� deve retornar uma linha.
			// Se n�o encontrar, retornar� nulo. (deveria dar erro?)
			if (rs.next()) {
				contato = new Contato();
				contato.setId(rs.getLong("id"));
				contato.setNome(rs.getString("nome"));
				contato.setEmail(rs.getString("email"));
				contato.setEndereco(rs.getString("endereco"));
				Calendar data = Calendar.getInstance();
				data.setTime(rs.getDate("dataNascimento"));
				contato.setDataNascimento(data);
			}

			stmt.close();
		} catch (SQLException e) {
			throw new DaoException(e);
		}
		return contato;
	}

	// Todos os dados (inclusive o ID a ser alterado) devem ser preenchidos no
	// objeto contato
	public void altera(Contato contato) {
		String sql = " update contatos "
				+ " set nome = ?, email = ?, endereco = ?, dataNascimento = ? "
				+ " where id = ? ";

		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setString(1, contato.getNome());
			stmt.setString(2, contato.getEmail());
			stmt.setString(3, contato.getEndereco());
			stmt.setDate(4, new Date(contato.getDataNascimento()
					.getTimeInMillis()));
			try {
				stmt.setLong(5, contato.getId());
			} catch (SQLException e) {
				// O Access n�o suporta Long, ent�o tive que fazer esta "adapta��o"
				stmt.setInt(5, contato.getId().intValue());
			}
			stmt.execute();
		} catch (SQLException e) {
			throw new DaoException(e);
		}
	}

	// O objeto contato deve conter o ID a ser exclu�do
	public void remove(Contato contato) {
		String sql = " delete from contatos where id = ? ";

		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			try {
				stmt.setLong(1, contato.getId());
			} catch (SQLException e) {
				// O Access n�o suporta Long, ent�o tive que fazer esta "adapta��o" 
				stmt.setInt(1, contato.getId().intValue());
			}
			stmt.execute();
		} catch (SQLException e) {
			throw new DaoException(e);
		}
	}
}
