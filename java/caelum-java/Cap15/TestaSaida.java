import java.util.*;
import java.io.*;

class TestaSaida {
    public static void main(String[] args) throws IOException {
        // sa�da bin�ria (byte)
        OutputStream os = new FileOutputStream("saida.txt", true); // append = true
        // sa�da de caracteres (char)
        OutputStreamWriter osw = new OutputStreamWriter(os);
        // sa�da com buffer (String)
        BufferedWriter bw = new BufferedWriter(osw);

        Date d = new Date();

        bw.write("Data/hora: " + d.toString());
        bw.newLine();
        bw.close();
    }
}