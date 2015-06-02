import java.io.*;
import java.util.Properties;

class GravaPropriedades {
    public static void main(String[] args) throws IOException { 
        Properties config = new Properties();
        
        config.setProperty("numero", "123");
        config.setProperty("texto", "abcdef");
        config.setProperty("url", "www.nonono.com");
        
        config.store(new FileWriter("propriedades.cfg"), "gravado por GravaPropriedades");
    }
}