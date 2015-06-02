class TestaDuasContas {
    public static void main(String[] args) {
        Conta minhaConta;
        minhaConta = new Conta();
        minhaConta.saldo = 20000;
        System.out.println("Conta: " + minhaConta.saldo);
        
        Conta meuSonho;
        meuSonho = new Conta();
        meuSonho.saldo = 200000;
        System.out.println("Sonho: " + meuSonho.saldo);
    } 
}
