import java.sql.*;

class JDBCExemplo {
    public static void main(String args[]) throws SQLException {
        Connection conexao = DriverManager.getConnection(
            "jdbc:mysql://localhost/test");
        System.out.println("Hello, database!");
        conexao.close();
    }
}
