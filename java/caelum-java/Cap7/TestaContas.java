class TestaContas {
    public static void main(String args[]) {
    
        // Atenção para o polimorfismo!!!
        Conta c = new Conta();
        Conta cc = new ContaCorrente();
        Conta cp = new ContaPoupanca();
        Conta ci = new ContaInvestimento();

        c.deposita(1000.0);
        cc.deposita(1000.0);
        cp.deposita(1000.0);
        ci.deposita(1000.0);

        c.atualiza(0.01);
        cc.atualiza(0.01);
        cp.atualiza(0.01);
        ci.atualiza(0.01);

        c.saca(1);
        cc.saca(1);
        cp.saca(1);
        ci.saca(1);

        System.out.println("Conta = " + c.getSaldo());
        System.out.println("Conta corrente = " + cc.getSaldo());
        System.out.println("Conta poupança = " + cp.getSaldo());
        System.out.println("Conta investimento = " + ci.getSaldo());
    }
}