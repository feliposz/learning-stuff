class Conta {
    private double saldo;
    
    public double getSaldo() {
        return this.saldo;
    }
    
    public void deposita(double valor) {
        if (valor > 0) {
            this.saldo += valor;
        }
    }
    
    public void saca(double valor) {
        if (valor <= this.saldo) {
            this.saldo -= valor;
        }
    }
    
    public void atualiza(double taxa) {
        this.saldo += this.saldo * taxa;
    }
}
