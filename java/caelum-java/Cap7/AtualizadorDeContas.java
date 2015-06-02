class AtualizadorDeContas {
    private double saldoTotal = 0.0;
    private double selic;
    
    AtualizadorDeContas(double selic) {
        this.selic = selic;
    }
    
    public void roda(Conta c) {
        System.out.println("Saldo anterior: " + c.getSaldo());
        c.atualiza(selic);
        System.out.println("Saldo final: " + c.getSaldo());
        saldoTotal += c.getSaldo();
    }

    public double getSaldoTotal() {
        return this.saldoTotal;
    }
    
    public static void main(String[] args) {
        Conta c = new Conta();
        Conta cc = new ContaCorrente();
        Conta cp = new ContaPoupanca();
        Conta ci = new ContaInvestimento();

        c.deposita(1000.0);
        cc.deposita(1000.0);
        cp.deposita(1000.0);
        ci.deposita(1000.0);

        AtualizadorDeContas adc = new AtualizadorDeContas(0.01);
        adc.roda(c);
        adc.roda(cc);
        adc.roda(cp);
        adc.roda(ci);

        System.out.println("Saldo total: " + adc.getSaldoTotal());
    }
}