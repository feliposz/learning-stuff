import java.io.*;
import java.util.Properties;

class CarregaPropriedades {
    public static void main(String[] args) throws IOException {
        Properties config = new Properties();
        
        config.load(new FileReader("propriedades.cfg"));
        
        config.list(System.out);
    }
}