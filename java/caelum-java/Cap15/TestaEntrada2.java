import java.io.*;

class TestaEntrada2 {
    public static void main(String[] args) throws IOException {
        // abre um arquivo no modo binário
        InputStream is = new FileInputStream("TestaEntrada2.java");
        // converte a stream binária para caracter
        InputStreamReader isr = new InputStreamReader(is); 
        // converte uma stream sem buffer para uma stream com buffer
        BufferedReader br = new BufferedReader(isr); 
        
        //  is  -> isr  ->  br
        // byte -> char -> String
        
        String s;
        int numLinha = 1;
        // lê uma linha de cada vez enquanto não chega no fim do arquivo
        while ((s = br.readLine()) != null) { 
            System.out.printf("%3d: %s\n", numLinha++, s);
        }
        br.close();
    }
}
