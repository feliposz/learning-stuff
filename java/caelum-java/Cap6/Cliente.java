class Cliente {
    private String nome;
    private String sobrenome;
    private String cpf;
    
    public String toString() {
        return "Cliente (nome: " + this.nome 
            + " sobrenome: " + this.sobrenome 
            + " cpf: " + this.cpf
            + ")";
    }
    
    public void mudaCpf(String cpf) {
        if (Validacao.validaCpf(cpf)) {
            this.cpf = cpf;
        } else {
            System.out.println("CPF Inválido!");
        }
    }
    
    Cliente() {
        System.out.println("Construindo um cliente.");
    }
    
    Cliente(String cpf) {
        this();
        this.mudaCpf(cpf);
    }
}
