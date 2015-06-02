class Casa {
    String cor;
    Porta porta1 = new Porta(), porta2 = new Porta(), porta3 = new Porta();
    
    void pinta(String s) {
        this.cor = s;
        porta1.pinta(s);
        porta2.pinta(s);
        porta3.pinta(s);
    }
    
    int quantasPortasEstaoAbertas() {
        return (porta1.estaAberta() ? 1 : 0)
            + (porta2.estaAberta() ? 1 : 0)
            + (porta3.estaAberta() ? 1 : 0);
    }
    
    
    void mostra() {
        System.out.println("Cor da casa=" + this.cor);
        System.out.println("Porta 1=" + this.porta1.toString());
        System.out.println("Porta 2=" + this.porta2.toString());
        System.out.println("Porta 3=" + this.porta3.toString());
        System.out.println("Portas abertas=" + this.quantasPortasEstaoAbertas());
    }
    
    public static void main(String[] args) {
        Casa c = new Casa();
        c.pinta("Branco");
        c.mostra();
        c.porta1.abre();
        c.porta2.abre();
        c.porta3.abre();
        c.mostra();
        c.porta2.fecha();
        c.mostra();
        c.porta2.abre();
        c.porta1.fecha();
        c.porta3.fecha();
        c.mostra();
    }
}
