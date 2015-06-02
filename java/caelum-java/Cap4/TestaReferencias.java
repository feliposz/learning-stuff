class TestaReferencias {
    public static void main(String[] args) {
        Conta c1 = new Conta();
        c1.deposita(100);
        
        Conta c2 = c1;
        c2.deposita(200);
        
        System.out.println("c1.saldo = " + c1.saldo);
        System.out.println("c2.saldo = " + c2.saldo);
        
        Conta a = new Conta();
        a.dono = "Duke";
        a.saldo = 227;
        
        Conta b = new Conta();
        b.dono = "Duke";
        b.saldo = 227;
        
        if (a == b) {
            System.out.println("Contas iguais a == b");
        } else {
            System.out.println("Contas diferentes a != b");
        }
        
        if (a.transferePara(b, 50)) {
            System.out.println("Transferência realizada");
        } else {
            System.out.println("Transferência não realizada");
        }
        System.out.println("a.saldo = " + a.saldo);
        System.out.println("b.saldo = " + b.saldo);
    }
}