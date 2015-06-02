/*
_________________________________
|                               |
|          Funcionario          |
|_______________________________|
| +nome: String                 |
| +departamento: String         |
| +salario: double              |
| +dataEntrada: Data            |
| +rg: String                   |
| +ativo: boolean               |
|_______________________________|
| +recebeAumento(valor: double) |
| +calculaGanhoAnual(): double  |
| +demite()                     |
| +toString(): String           |
|_______________________________|

*/

class Funcionario {
    String nome;
    String departamento;
    double salario;
    Data dataEntrada;
    String rg;
    boolean ativo = true;
    
    void recebeAumento(double valor) {
        this.salario += vastlor;
    }
    
    double calculaGanhoAnual() {
        return 12.0 * this.salario;
    }
    
    void demite() {
        this.ativo = false;
    }
    
    public String toString() {
        return "Funcionario: " + this.nome
            + " Departamento: " + this.departamento
            + " Salário: " + this.salario
            + " Data Entrada: " + this.dataEntrada.toString()
            + " RG: " + this.rg
            + " Ativo: " + (this.ativo ? "Sim" : "Não");
    }
    
    void mostra() {
        System.out.println(this.toString());
    }

}
