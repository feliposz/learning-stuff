// javac -cp G:\oraclexe\sqldeveloper\jdk\jre\lib\commons-lang3-3.3.2\commons-lang3-3.3.2.jar;. Cliente.java
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

class Cliente {
    String nome;
    String sobrenome;
    String cpf;
    
    public String toString() {
        return ReflectionToStringBuilder.toString(this);
    }    
}
