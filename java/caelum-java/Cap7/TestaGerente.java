class TestaGerente {
    public static void main(String args[]) {
        Gerente gerente = new Gerente();
        gerente.setNome("João da Silva");
        gerente.setSenha(4231);
        gerente.setSalario(5000.0);
        System.out.println("gerente: " + gerente.toString());
        System.out.println("bonificacao: " + gerente.getBonificacao());
        
        Funcionario funcionario = gerente;
        funcionario.setSalario(5100.00);
        System.out.println("funcionario: " + funcionario.toString());
        System.out.println("bonificacao: " + funcionario.getBonificacao());
    }
}
