import java.io.*;

class TestaEntrada2 {
    public static void main(String[] args) throws IOException {
        // abre um arquivo no modo bin�rio
        InputStream is = new FileInputStream("TestaEntrada2.java");
        // converte a stream bin�ria para caracter
        InputStreamReader isr = new InputStreamReader(is); 
        // converte uma stream sem buffer para uma stream com buffer
        BufferedReader br = new BufferedReader(isr); 
        
        //  is  -> isr  ->  br
        // byte -> char -> String
        
        String s;
        int numLinha = 1;
        // l� uma linha de cada vez enquanto n�o chega no fim do arquivo
        while ((s = br.readLine()) != null) { 
            System.out.printf("%3d: %s\n", numLinha++, s);
        }
        br.close();
    }
}
