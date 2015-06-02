class Funcionario {
    private String nome;
    public String getNome() { return this.nome; }
    public void setNome(String val) { this.nome = val; }

    private String cpf;
    public String getCpf() { return this.cpf; }
    public void setCpf(String val) { this.cpf = val; }

    protected double salario;
    public double getSalario() { return this.salario; }
    public void setSalario(double val) { this.salario = val; }


    public double getBonificacao() {
        return this.salario * 0.10;
    }

    public String toString() {
        return "Funcionario (nome: " + this.nome
            + ", cpf: " + this.cpf
            + ", salario: " + this.salario + ")";
    }
}
