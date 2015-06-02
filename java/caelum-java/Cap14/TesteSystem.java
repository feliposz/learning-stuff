import java.io.PrintStream;

class TesteSystem {
    public static void main(String args[]) throws java.io.IOException {
        PrintStream o = System.out;
        o.println("Digite um caracter, o seu código será retornado na saída:");
        int i = System.in.read();
        System.exit(i);
    }
}
