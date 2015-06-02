import java.util.Arrays;

class Banco {
    private Conta[] contas = new Conta[1];
    private int qtdeContas = 0;

    public void adiciona(Conta c) {
        if (this.qtdeContas == this.contas.length) {
            this.contas = Arrays.copyOf(this.contas, this.contas.length * 2);
        }
        this.contas[this.qtdeContas++] = c;
    }

    public Conta pegaConta(int x) {
        return this.contas[x];
    }

    public int pegaTotalDeContas() {
        return this.qtdeContas;
    }

    public static void main(String args[]) {
        Banco b = new Banco();
        b.adiciona(new Conta());
        b.adiciona(new ContaCorrente());
        b.adiciona(new ContaPoupanca());
        b.adiciona(new ContaInvestimento());

        int qtde = b.pegaTotalDeContas();
        for (int i = 0; i < qtde; i++) {
            b.pegaConta(i).deposita(1000.0);
        }

        AtualizadorDeContas adc = new AtualizadorDeContas(0.01);
        for (int i = 0; i < qtde; i++) {
            adc.roda(b.pegaConta(i));
        }

    }
}