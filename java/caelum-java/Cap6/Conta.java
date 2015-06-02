class Conta {
    private int numero;
    private String dono;
    private double saldo;
    private double limite;
    
    private static int totalDeContas;
    
    public static int getTotalDeContas() {
        return Conta.totalDeContas;
    }
    
    // N�o � considerado boa pr�tica definir getters e setters para todos os atributos
    // S� se deve criar estes m�todos se houver mesmo necessidade.
    // � prefer�vel s� expor a altera��o dos atributos atrav�s de m�todos de neg�cio espec�ficos (como deposita, saca e transferePara, abaixo)
    public double getSaldo() { return this.saldo; }
    public void setSaldo(double saldo) { this.saldo = saldo; }
    public double getLimite() { return this.limite; }
    public void setLimite(double limite) { this.limite = limite; }
    public String getDono() { return this.dono; }
    public void setDono(String dono) { this.dono = dono; }
    public int getNumero() { return this.numero; }
    public void setNumero(int numero) { this.numero = numero; }
    
    public void saca(double valor) {
        if (!debita(valor)) {
            System.out.println("N�o posso sacar fora do limite!");
        }
    }

    boolean debita(double valor) {
        if (valor > this.saldo + this.limite) {
            return false;
        } else {
            this.saldo -= valor;
            return true;
        }
    }

    void deposita(double quantidade) {
        if (quantidade < 0.0) {
            System.out.println("N�o � poss�vel depositar valores negativos.");
        } else {
            this.saldo += quantidade;
        }
    }

    boolean transferePara(Conta destino, double valor) {
        if (this.debita(valor)) {
            destino.deposita(valor);
            return true;
        } else {
            return false;
        }
    }
    
    Conta() {
        System.out.println("Construindo uma conta.");
        Conta.totalDeContas++;
    }
    
    Conta(String dono) {
        this();
        this.dono = dono;
    }
    
    Conta(int numero, String dono) {
        this(dono);
        this.numero = numero;
    }

    public static void main (String[] args) {
    
        System.out.println("Total de contas: " + Conta.getTotalDeContas());
    
        Conta minhaConta;
        minhaConta = new Conta(123, "Tywin Lannister");

        minhaConta.deposita(1000000.0);

        System.out.println("Saldo atual para " + minhaConta.dono + " �: " + minhaConta.getSaldo());
        if (!minhaConta.debita(200)) {
            System.out.println("N�o � poss�vel sacar este valor");
        }
        System.out.println("Saldo atual para " + minhaConta.dono + " �: " + minhaConta.getSaldo());
        minhaConta.deposita(500);
        System.out.println("Saldo atual para " + minhaConta.dono + " �: " + minhaConta.getSaldo());
        if (!minhaConta.debita(2000000)) {
            System.out.println("N�o � poss�vel sacar este valor");
        }
        System.out.println("Saldo atual para " + minhaConta.dono + " �: " + minhaConta.getSaldo());
        
        System.out.println("Total de contas: " + Conta.getTotalDeContas());
    }
}
