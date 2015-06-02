package br.com.caelum.jdbc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.caelum.jdbc.ConnectionFactory;
import br.com.caelum.jdbc.modelo.Funcionario;

public class FuncionarioDao {

	private Connection connection;

	public FuncionarioDao() {
		this.connection = new ConnectionFactory().getConnection();
	}

	public void criarTabela() {
		// Resolvi manter o DDL para criação da tabela junto com os outros
		// comandos para acesso à mesma.
		String sql = " create table funcionarios "
				+ " ( id bigint not null auto_increment "
				+ " , nome varchar(255) " 
				+ " , usuario varchar(30) "
				+ " , senha varchar(30) " 
				+ " , primary key (id) " 
				+ " ) ";

		try {
			PreparedStatement stmt = this.connection.prepareStatement(sql);
			stmt.execute();
			stmt.close();
			System.out.println("Tabela funcionário criada.");
		} catch (SQLException e) {
			// Infelizmente esta mensagem é dependente do SGBD e precisaria ser alterada para outros sistemas.
			if (e.getMessage().contains("already exists")) {
				System.out
						.println("Tabela funcionário já existe, não foi necessário criá-la.");
			} else {
				throw new DaoException(e);
			}
		}
	}

	public void adiciona(Funcionario funcionario) {
		String sql = " insert into funcionarios (nome, usuario, senha) values (?, ?, ?) ";

		try {
			// Este é um método mais padronizado de obter as chaves geradas na
			// inserção do banco
			// Deve funcionar com diversos SGBD diferentes
			PreparedStatement stmt = this.connection.prepareStatement(sql,
					PreparedStatement.RETURN_GENERATED_KEYS);
			stmt.setString(1, funcionario.getNome());
			stmt.setString(2, funcionario.getUsuario());
			stmt.setString(3, funcionario.getSenha());
			stmt.executeUpdate();

			ResultSet rs = stmt.getGeneratedKeys();
			if (rs.next()) {
				funcionario.setId(rs.getLong(1));
			}

			stmt.close();
		} catch (SQLException e) {
			throw new DaoException(e);
		}
	}

	public void altera(Funcionario funcionario) {
		String sql = " update funcionarios set nome = ?, usuario = ?, senha = ? where id = ? ";

		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setString(1, funcionario.getNome());
			stmt.setString(2, funcionario.getUsuario());
			stmt.setString(3, funcionario.getSenha());
			stmt.setLong(4, funcionario.getId());
			stmt.executeUpdate();
			stmt.close();
		} catch (SQLException e) {
			throw new DaoException(e);
		}
	}

	public void remove(Funcionario funcionario) {
		String sql = " delete from funcionarios where id = ? ";

		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setLong(1, funcionario.getId());
			stmt.executeUpdate();
			stmt.close();
		} catch (SQLException e) {
			throw new DaoException(e);
		}
	}

	public Funcionario pesquisa(long id) {
		String sql = " select * from funcionarios where id = ? ";

		Funcionario funcionario = null;
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setLong(1, id);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				funcionario = new Funcionario();
				funcionario.setId(rs.getLong("id"));
				funcionario.setNome(rs.getString("nome"));
				funcionario.setUsuario(rs.getString("usuario"));
				funcionario.setSenha(rs.getString("senha"));
			}
		} catch (SQLException e) {
			throw new DaoException(e);
		}

		return funcionario;
	}

	public List<Funcionario> getLista() {
		String sql = " select * from funcionarios ";

		List<Funcionario> lista = new ArrayList<>();

		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				Funcionario funcionario = new Funcionario();
				funcionario.setId(rs.getLong("id"));
				funcionario.setNome(rs.getString("nome"));
				funcionario.setUsuario(rs.getString("usuario"));
				funcionario.setSenha(rs.getString("senha"));
				lista.add(funcionario);
			}
			stmt.close();
		} catch (SQLException e) {
			throw new DaoException(e);
		}

		return lista;
	}

}
