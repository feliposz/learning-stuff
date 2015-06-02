import java.util.Arrays;

class Casa {
    String cor;
    Porta portas[] = new Porta[1];
    int qtdePortas = 0;
    
    void pinta(String s) {
        this.cor = s;
        for (Porta porta : this.portas) {
            if (porta != null) {
                porta.pinta(s);
            }
        }
    }
    
    int quantasPortasEstaoAbertas() {
        int qtde = 0;
        for (Porta porta : this.portas) {
            if (porta != null && porta.estaAberta()) {
                qtde++;
            }
        }
        return qtde;
    }
    
    void adicionaPorta(Porta p) {
        if (this.qtdePortas >= this.portas.length) {
            this.portas = Arrays.copyOf(this.portas, this.portas.length * 2);
        }
        this.portas[this.qtdePortas++] = p;
    }
    
    void mostra() {
        System.out.println("Cor da casa=" + this.cor);
        for (int i = 0; i < this.portas.length; i++) {
            if (this.portas[i] != null) {
                System.out.println("Porta " + i + " = " + this.portas[i].toString());
            }
        }
        System.out.println("Portas abertas=" + this.quantasPortasEstaoAbertas());
    }
    
    public static void main(String[] args) {
        Casa c = new Casa();
        c.adicionaPorta(new Porta());
        c.adicionaPorta(new Porta());
        c.adicionaPorta(new Porta());
        c.pinta("Branco");
        c.mostra();
        c.portas[0].abre();
        c.portas[1].abre();
        c.portas[2].abre();
        c.mostra();
        c.portas[1].fecha();
        c.mostra();
        c.portas[1].abre();
        c.portas[0].fecha();
        c.portas[2].fecha();
        c.mostra();
    }
}
