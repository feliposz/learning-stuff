import java.io.Console;

class TesteConsole {
    public static void main(String args[]) {
        Console console = System.console();
        
        if (console == null) {
            return;
        }
        
        console.printf("Digite alguma coisa: ");
        String coisa = console.readLine();
        console.printf("Você digitou: '%s'\n", coisa);
        
        console.printf("Frase secreta: ");
        String segredo1 = new String(console.readPassword());
        console.printf("Outra frase secreta: ");
        String segredo2 = new String(console.readPassword());
        
        //console.printf("segredo1 = '%s'\nsegredo2 = '%s'\n", segredo1, segredo2);
        
        if (segredo1.equals(segredo2)) {
            console.printf("Você digitou a mesma frase.\n");
        } else {
            console.printf("Você digitou frases diferentes.\n");
        }
       
    }
}
