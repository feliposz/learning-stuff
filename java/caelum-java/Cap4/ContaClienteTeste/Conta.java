// javac -cp G:\oraclexe\sqldeveloper\jdk\jre\lib\commons-lang3-3.3.2\commons-lang3-3.3.2.jar;. Conta.java
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

class Conta {
    int numero = 1234;
    double saldo = 1000;
    double limite = 1000;
    Cliente titular = new Cliente();
    
    public String toString() {
        return ReflectionToStringBuilder.toString(this);
    }
}
