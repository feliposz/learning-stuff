class Conta {
    private int numero;
    private String dono;
    private double saldo;
    private double limite;
    
    private static int totalDeContas;
    
    public static int getTotalDeContas() {
        return Conta.totalDeContas;
    }
    
    // Não é considerado boa prática definir getters e setters para todos os atributos
    // Só se deve criar estes métodos se houver mesmo necessidade.
    // É preferível só expor a alteração dos atributos através de métodos de negócio específicos (como deposita, saca e transferePara, abaixo)
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
            System.out.println("Não posso sacar fora do limite!");
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
            System.out.println("Não é possível depositar valores negativos.");
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

        System.out.println("Saldo atual para " + minhaConta.dono + " é: " + minhaConta.getSaldo());
        if (!minhaConta.debita(200)) {
            System.out.println("Não é possível sacar este valor");
        }
        System.out.println("Saldo atual para " + minhaConta.dono + " é: " + minhaConta.getSaldo());
        minhaConta.deposita(500);
        System.out.println("Saldo atual para " + minhaConta.dono + " é: " + minhaConta.getSaldo());
        if (!minhaConta.debita(2000000)) {
            System.out.println("Não é possível sacar este valor");
        }
        System.out.println("Saldo atual para " + minhaConta.dono + " é: " + minhaConta.getSaldo());
        
        System.out.println("Total de contas: " + Conta.getTotalDeContas());
    }
}
