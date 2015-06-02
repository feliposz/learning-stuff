class EmpregadoDaFaculdade {
    String nome;
    double salario;
    
    double getGastos() {
        return this.salario;
    }
    String getInfo() {
        return "nome: " + this.nome + " com salário " + this.salario;
    }
}