/*

_______________
|             |
| Funcionario |    (superclasse)
|_____________|
|_____________|
|_____________|
       /\
       |
_______|_______
|             |
|   Gerente   |    (subclasse)
|_____________|
|_____________|
|_____________|

Gerente é um (is-a) Funcionario

*/


class Gerente extends Funcionario{
    private int senha;
    public int getSenha() { return this.senha; }
    public void setSenha(int val) { this.senha = val; }

    int numeroDeFuncionariosGerenciados;
    
    public boolean autentica(int senha) {
        if (this.senha == senha) {
            System.out.println("Acesso permitido");
            return true;
        } else {
            System.out.println("Acesso negado");
            return false;
        }
    }
    
    public double getBonificacao() {
        return super.getBonificacao() + 1000.00;
    }
    
    public String toString() {
        return "Gerente (senha: " + this.senha
            + ", numeroDeFuncionariosGerenciados: " + this.numeroDeFuncionariosGerenciados 
            + ", base: " + super.toString() + ")";
    }
}
