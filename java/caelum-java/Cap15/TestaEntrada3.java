import java.io.*;

class TestaEntrada3 {
    public static void main(String[] args) throws IOException {
        // Lê da entrada padrão, no lugar de um arquivo
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); 
        
        String s;
        while ((s = br.readLine()) != null) { 
            if (s.equals("42")) {
                break;
            } else {
                System.out.println(s);
            }            
        }
        br.close();
    }
}
