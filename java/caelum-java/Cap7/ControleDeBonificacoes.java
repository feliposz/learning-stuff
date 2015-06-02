class ControleDeBonificacoes {
    private double totalDeBonificacaoes = 0.0;
    
    public void registra(Funcionario f) {
        totalDeBonificacaoes += f.getBonificacao();
    }
    
    public double getTotalDeBonificacoes() { return this.totalDeBonificacaoes; }
    
    
    public static void main(String args[]) {
        ControleDeBonificacoes controle = new ControleDeBonificacoes();
        
        Gerente f1 = new Gerente();
        f1.setSalario(5000.0);
        controle.registra(f1);
        
        Funcionario f2 = new Funcionario();
        f2.setSalario(1000.0);
        controle.registra(f2);
        
        System.out.println("Bonificacoes: " + controle.getTotalDeBonificacoes());
    }
}