class Empresa {
    Funcionario[] empregados;
    String cnpj;
    String nomeFantasia;
    int qtde;
    
    Empresa() {
        this.empregados = new Funcionario[1];
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
    
    boolean contem(Funcionario f) { // Ex 6
        for (Funcionario u : this.empregados) {
            if (u == f) {
                return true;
            }
        }
        return false;
    }
}
