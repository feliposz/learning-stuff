
class Funcionario {

    Funcionario() { }
    
    Funcionario(String nome) {
        this();
        this.nome = nome;
    }

    // OBS: Não é recomendável criar setters/getters para todos os atributos, isto é apenas um exercício!!!
    
    // Cap6 Ex 2
    private String nome;
    public String getNome() { return this.nome; }
    public void setNome(String val) { this.nome = val; }

    private String departamento;
    public String getDepartamento() { return this.departamento; }
    public void setDepartamento(String val) { this.departamento = val; }

    private double salario;
    public double getSalario() { return this.salario; }
    public void setSalario(double val) { this.salario = val; }

    private String rg;
    public String getRg() { return this.rg; }
    public void setRg(String val) { this.rg = val; }
    
    private Data dataEntrada = null;
    public Data getDataEntrada() { return this.dataEntrada; }
    public void setDataEntrada(Data val) { this.dataEntrada = val; }

    private boolean ativo = true;
    public boolean getAtivo() { return this.ativo; }
    public void setAtivo(boolean val) { this.ativo = val; }
    
    void recebeAumento(double valor) {
        if (valor > 0) {
            this.salario += valor;
        }
    }
    
    double getGanhoAnual() {
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
    
    
    public static void main(String args[]) {
        Funcionario f = new Funcionario("Fulano");
        f.mostra();
    }

}
