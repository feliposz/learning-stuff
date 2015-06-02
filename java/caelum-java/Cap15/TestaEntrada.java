import java.io.*;

class TestaEntrada {
    public static void main(String[] args) throws IOException {
        InputStream is = new FileInputStream("TestaEntrada.java");
        byte[] byteArray = new byte[20];
        int size;
        
        while ((size = is.read(byteArray)) > 0) {
            System.out.print("> ");
            for (int i = 0; i < size; i++) {
                System.out.print(byteArray[i] + " ");
            }
            System.out.println();
        }
    }
}