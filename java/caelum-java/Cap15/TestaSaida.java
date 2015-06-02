import java.util.*;
import java.io.*;

class TestaSaida {
    public static void main(String[] args) throws IOException {
        // saída binária (byte)
        OutputStream os = new FileOutputStream("saida.txt", true); // append = true
        // saída de caracteres (char)
        OutputStreamWriter osw = new OutputStreamWriter(os);
        // saída com buffer (String)
        BufferedWriter bw = new BufferedWriter(osw);

        Date d = new Date();

        bw.write("Data/hora: " + d.toString());
        bw.newLine();
        bw.close();
    }
}