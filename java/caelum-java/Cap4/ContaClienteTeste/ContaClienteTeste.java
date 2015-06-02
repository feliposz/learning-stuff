// javac -cp G:\oraclexe\sqldeveloper\jdk\jre\lib\commons-lang3-3.3.2\commons-lang3-3.3.2.jar;. ContaClienteTeste.java && java ContaClienteTeste
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

class ContaClienteTeste {
    public static void main(String[] args) {
        Conta minhaConta = new Conta();
        Cliente c = minhaConta.titular;
        c = new Cliente();
        c.nome = "Duke";
        c.sobrenome = "Nukem";
        c.cpf = "123.456.789-01";
        System.out.println("minhaConta = " + minhaConta.toString());
        System.out.println("c = " + c.toString());
        System.out.println("ReflectionToStringBuilder = " + ReflectionToStringBuilder.toString(minhaConta));
    }
}
