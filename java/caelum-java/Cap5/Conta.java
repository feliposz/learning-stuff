class Conta {
    int numero;
    String dono;
    double saldo;
    double limite;

    // ...

    boolean saca(double valor) {
        if (this.saldo < valor) {
            return false;
        } else {
            this.saldo -= valor;
            return true;
        }
    }

    void deposita(double quantidade) {
        this.saldo += quantidade;
    }

    boolean transferePara(Conta destino, double valor) {
        if (this.saca(valor)) {
            destino.deposita(valor);
            return true;
        } else {
            return false;
        }
    }

    public static void main (String[] args) {
        Conta minhaConta;
        minhaConta = new Conta();

        minhaConta.dono = "Tywin Lannister";
        minhaConta.saldo = 1000000.0;

        System.out.println("Saldo atual para " + minhaConta.dono + " é: " + minhaConta.saldo);
        if (!minhaConta.saca(200)) {
            System.out.println("Não é possível sacar este valor");
        }
        System.out.println("Saldo atual para " + minhaConta.dono + " é: " + minhaConta.saldo);
        minhaConta.deposita(500);
        System.out.println("Saldo atual para " + minhaConta.dono + " é: " + minhaConta.saldo);
        if (!minhaConta.saca(2000000)) {
            System.out.println("Não é possível sacar este valor");
        }
        System.out.println("Saldo atual para " + minhaConta.dono + " é: " + minhaConta.saldo);
    }
}
