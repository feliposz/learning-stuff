class TestaContaEstouro1 {
    public static void main(String args[]) {
        Conta c = new Conta();
        c.deposita(1000.0);
        c.setLimite(1000.0);
        c.saca(50000);
        System.out.println("Saldo: " + c.getSaldo());
    }
}
