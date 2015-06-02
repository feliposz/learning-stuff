class TestaContaEstouro3 {
    public static void main(String args[]) {
        Conta c = new Conta();
        c.setLimite(100.0);
        c.deposita(100.0);
        
        c.saca(1000.0);
        
        System.out.println("Saldo: " + c.getSaldo());
    }
}
