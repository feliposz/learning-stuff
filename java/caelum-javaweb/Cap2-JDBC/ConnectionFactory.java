import java.sql.*;

// Esta classe implementa um mecanismo simples de obtenção de conexões

public class ConnectionFactory {

    // O método pode ser chamado pelo programador e já retorna uma conexão pronta para uso.
    // O programador não precisa se preocupar com detalhes de como conectar ao banco, tratamento de erros, etc.
    public Connection getConnection() {
        try {
            // Numa aplicação de produção, talvez seja ideal implementar alguma forma de pooling.
            return DriverManager.getConnection("jdbc:mysql://localhost/fj21");
        } catch (SQLException e) {
            // A exceção neste caso é relançada como uma exceção runtime comum,
            // para evitar o acomplamento de quem utilizar a ConnectionFactory com
            // a API do JDBC
            throw new RuntimeException(e);
        }
    }
    
    // Esse método é apenas para exemplificar uma forma centralizada de tratar o fechamento das conexões.
    // Eu mesmo implementei, então não sei se é a forma ideal de tratá-lo.
    // Coloquei como static só para não ter que instanciar a Factory novamente.
    public static void close(Connection conn) {
        try {
            conn.close();
        } catch (SQLException e) {
            // (idem, veja acima)
            throw new RuntimeException(e);
        }
    }
    
    
    public static void main(String[] args) {
        
        // Teste e exemplo de uso
        Connection conn = new ConnectionFactory().getConnection();
        
        System.out.println("Obteve uma conexão com sucesso.");
        
        // Exemplo de uso do método para fechar uma conexão
        ConnectionFactory.close(conn);
    }
}