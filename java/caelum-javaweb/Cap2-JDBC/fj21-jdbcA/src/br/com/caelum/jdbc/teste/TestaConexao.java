package br.com.caelum.jdbc.teste;

import java.sql.Connection;
import java.sql.SQLException;

import br.com.caelum.jdbc.ConnectionFactory;

public class TestaConexao {

	/**
	 * @param args
	 * @throws SQLException 
	 */
	public static void main(String[] args) throws SQLException {
		//Connection conn = new ConnectionFactory().getConnection();
		Connection conn = new ConnectionFactory().getConnectionAccess();
		//Connection conn = new ConnectionFactory().getConnectionMySQL();
		//Connection conn = new ConnectionFactory().getConnectionOracle();		
		
		System.out.println("Conexão obtida: " + conn);
		
		if (conn instanceof oracle.jdbc.driver.OracleConnection) {
			System.out.println("Conexão: Oracle");
		} else if (conn instanceof com.mysql.jdbc.JDBC4Connection){
			System.out.println("Conexão: MySQL");
		} else {
			System.out.println("Conexão: Outro tipo -> " + conn.getClass().getName());
		}
		
		conn.close();
	}
	
	/**
	 * Para rodar o teste do Access foi necessário forçar o Eclipse 
	 * a rodar a aplicação como 32-bits numa JRE diferente.
	 * 
	 * Inserir uma JVM alternativa no eclipse:
	 * Window > Preferences > Add...
	 * (seguir o Wizard e selecionar o caminho para a JVM 32-bits)
	 * 
	 * Rodar a aplicação:
	 * Run > Run Configurations... > JRE > Alternate JRE
	 * (selecione a JRE adicionada anteriormente)
	 */

}
