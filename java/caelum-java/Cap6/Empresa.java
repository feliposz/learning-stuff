class Empresa {
    
    private String cnpj;
    public String getCnpj() { return this.cnpj; }
    public void setCnpj(String val) { this.cnpj = val; }

    private String nomeFantasia;
    public String getNomeFantasia() { return this.nomeFantasia; }
    public void setNomeFantasia(String val) { this.nomeFantasia = val; }

    private Funcionario[] empregados;
    private int qtde;
    
    Empresa(int tamanhoInicial) {
        this.empregados = new Funcionario[tamanhoInicial];
        this.qtde = 0;
    }
    
    void adiciona(Funcionario f) {
        System.out.println("Adicionando funcionário " + this.qtde);
        if (this.qtde >= this.empregados.length) {
            realocarEmpregados();
        }
        this.empregados[this.qtde++] = f;
    }
    
    void realocarEmpregados() { // Ex 7
        int tamanhoAtual = this.empregados.length, tamanhoNovo = tamanhoAtual * 2;
        System.out.println("Realocando: " + tamanhoAtual + " -> " + tamanhoNovo);        
        Funcionario[] empregadosNova = new Funcionario[tamanhoNovo];
        for (int i = 0; i < this.empregados.length; i++) {
            empregadosNova[i] = this.empregados[i];
        }
        this.empregados = empregadosNova;
        
        // import java.lang.Arrays;
        // this.empregados = Arrays.copyOf(this.empregados, this.empregados.length * 2); 
    }
    
    void mostra() {
        System.out.println("Total de empregados:" + this.qtde);
        for (int i = 0; i < this.qtde; i++) {
            this.empregados[i].mostra(); // Ex 5
        }
    }
    
    public Funcionario getFuncionario(int posicao) {
        return this.empregados[posicao];
    }
    
    boolean contem(Funcionario f) { // Ex 6
        for (Funcionario u : this.empregados) {
            if (u == f) {
                return true;
            }
        }
        return false;
    }
}
