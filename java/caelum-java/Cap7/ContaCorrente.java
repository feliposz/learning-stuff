class ContaCorrente extends Conta {
    
    public void atualiza(double taxa) {
        super.atualiza(taxa * 2.0);
    }
    
    public void saca(double valor) {
        super.saca(valor + 0.10);
    }
}
