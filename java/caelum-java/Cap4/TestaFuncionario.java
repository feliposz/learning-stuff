class TestaFuncionario {
    public static void main(String[] args) {
    
        Funcionario f = new Funcionario();

        Data d = new Data();
        d.dia = 23;
        d.mes = 11;
        d.ano = 1995;
        
        f.nome = "Jorah Mormont";
        f.departamento = "Conselho";
        f.salario = 10000;
        f.dataEntrada = d;
        f.rg = "12.345.678-9";        
        
        f.mostra();
        
        f.recebeAumento(5000);
        f.mostra();
        
        System.out.println("Ganho anual = " + f.calculaGanhoAnual());
        
        f.demite();
        f.mostra();
        
        //Funcionario.salario = 1234;
        //System.out.println(funcionario.calculaGanhoAtual());
    }
}

/*
Exercício 6:

f [ * ]       d [ * ]
    |             |
    |             \_____________________________________
    |                                                   \
____V____________________________                       |                     *1 ----> String("Jorah Mormont")
|                               |                       |                     
|          Funcionario          |                       |                     *2 ----> String("Conselho")
|_______________________________|           ____________V__________           
| +nome: String [ *1 ]          |           |        Data         |           *3 ----> String("12.345.678-9")
| +departamento: String [ *2 ]  |           |_____________________|
| +salario: double = 15000      |           | +dia: int = 23      |
| +dataEntrada: Data = [ * ]----+---------->| +mes: int = 11      |
| +rg: String [ *3 ]            |           | +ano: int = 1995    |
| +ativo: boolean = false       |           |_____________________|
|_______________________________|           | +toString(): String |
| +recebeAumento(valor: double) |           |_____________________|
| +calculaGanhoAnual(): double  |
| +demite()                     |
| +toString(): String           |
|_______________________________|


Exercício 7:

Exception in thread "main" java.lang.NullPointerException
        at Funcionario.toString(Funcionario.java:42)
        at Funcionario.mostra(Funcionario.java:51)
        at TestaFuncionario.main(TestaFuncionario.java:17)

Exercício 8:

TestaFuncionario.java:27: error: non-static variable salario cannot be referenced from a static context
        Funcionario.salario = 1234;
                   ^
TestaFuncionario.java:28: error: cannot find symbol
        System.out.println(funcionario.calculaGanhoAtual());
                           ^
  symbol:   variable funcionario
  location: class TestaFuncionario
2 errors
        
*/
