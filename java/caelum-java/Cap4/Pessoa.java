class Pessoa {
    String nome;
    int idade;
    
    void fazAniversario() {
        idade++;
    }
    
    public static void main(String[] args) {
        Pessoa p = new Pessoa();
        p.nome = "Daenerys Targaryan";
        p.idade = 13;
        p.fazAniversario();
        p.fazAniversario();
        p.fazAniversario();
        System.out.println("Nome: " + p.nome);
        System.out.println("Idade: " + p.idade);        
    }
}
