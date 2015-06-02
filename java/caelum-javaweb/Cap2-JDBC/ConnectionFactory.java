import java.sql.*;

// Esta classe implementa um mecanismo simples de obten��o de conex�es

public class ConnectionFactory {

    // O m�todo pode ser chamado pelo programador e j� retorna uma conex�o pronta para uso.
    // O programador n�o precisa se preocupar com detalhes de como conectar ao banco, tratamento de erros, etc.
    public Connection getConnection() {
        try {
            // Numa aplica��o de produ��o, talvez seja ideal implementar alguma forma de pooling.
            return DriverManager.getConnection("jdbc:mysql://localhost/fj21");
        } catch (SQLException e) {
            // A exce��o neste caso � relan�ada como uma exce��o runtime comum,
            // para evitar o acomplamento de quem utilizar a ConnectionFactory com
            // a API do JDBC
            throw new RuntimeException(e);
        }
    }
    
    // Esse m�todo � apenas para exemplificar uma forma centralizada de tratar o fechamento das conex�es.
    // Eu mesmo implementei, ent�o n�o sei se � a forma ideal de trat�-lo.
    // Coloquei como static s� para n�o ter que instanciar a Factory novamente.
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
        
        System.out.println("Obteve uma conex�o com sucesso.");
        
        // Exemplo de uso do m�todo para fechar uma conex�o
        ConnectionFactory.close(conn);
    }
}