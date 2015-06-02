class ArrayContas {
    public static void main(String[] args) {
        Conta[] minhasContas;
        minhasContas = new Conta[10];
        
        //System.out.println(minhasContas[0].saldo); // NullPointerException
        
        for (int i = 0; i < 10; i++) {
            minhasContas[i] = new Conta();
            minhasContas[i].saldo = (i+1) * 1000;
        }
        
        for (int i = 0; i < 10; i++) {
            System.out.println("Conta " + i + " possui saldo " + minhasContas[i].saldo);
        }
    }
}