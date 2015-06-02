package br.com.caelum.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

//import oracle.jdbc.pool.OracleDataSource;

public class ConnectionFactory {

	public Connection getConnection() {
		return this.getConnectionMySQL();
	}
	
	public Connection getConnectionMySQL() {
		try {

			// Foi necessário copiar a LIB do MySQL Connector na pasta 
			// WebContent\WEB-INF\lib\ e adicioná-la ao Build Path
			
			// Para funcionar no TOMCAT:			
			DriverManager.registerDriver(new com.mysql.jdbc.Driver ());
			
			// Isto não funcionou sozinho no TOMCAT:
			// Class.forName("com.mysql.jdbc.Driver");
			
			return DriverManager.getConnection(
					"jdbc:mysql://localhost/fj21");
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public Connection getConnectionAccess() {
		try {
			// Obtém o caminho completo para o arquivo de uma forma automática
			// O arquivo deve estar no mesmo diretório em que a aplicação é executada
			// AVISO: Isto não funciona por alguma incompatibilidade 
			// entre as versões 32-bit/64-bit do driver ODBC.
			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
			String path = new java.io.File("TesteJava.accdb").getAbsolutePath();
			String connectionString = "JDBC:ODBC:Driver=Microsoft Access Driver (*.mdb, *.accdb); DBQ=" + path;
			return DriverManager.getConnection(connectionString);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} catch (ClassNotFoundException e) {
			throw new RuntimeException(e);
		}
	}
	
	/*
	public Connection getConnectionOracle() {
		try {
			// Segundo o manual da Oracle, o uso do DriverManager é obsoleto (?!)
			OracleDataSource ods = new OracleDataSource();
			ods.setURL("jdbc:oracle:thin:felipo/felipo@//localhost:1521/xe");
			return ods.getConnection();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	*/
	
	// TODO: Testar outros tipos de banco de dados (Access? ODBC?)
}
