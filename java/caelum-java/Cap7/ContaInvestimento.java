class ContaInvestimento extends Conta {
    public void atualiza(double taxa) {
        super.atualiza(taxa * 4.0);
    }
}