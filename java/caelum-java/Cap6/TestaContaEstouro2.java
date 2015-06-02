class TestaContaEstouro2 {
    public static void main(String args[]) {
        Conta c = new Conta();
        c.setLimite(100.0);
        c.deposita(-200.0);
        System.out.println("Saldo: " + c.getSaldo());
    }
}
