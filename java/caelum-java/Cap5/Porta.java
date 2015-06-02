class Porta {
    boolean aberta = false;
    String cor = "Sem cor";
    double dimensaoX = 1.2;
    double dimensaoY = 2.5;
    double dimensaoZ = 0.15;
    
    void abre() {
        this.aberta = true;
    }
    
    void fecha() {
        this.aberta = false;
    }
    
    void pinta(String s) {
        this.cor = s;
    }
    
    boolean estaAberta() {
        return aberta;
    }
    
    public String toString() {
        return "aberta:" + this.aberta   
            + " cor:" + this.cor      
            + " dimensaoX:" + this.dimensaoX
            + " dimensaoY:" + this.dimensaoY
            + " dimensaoZ:" + this.dimensaoZ;    
    }
    
    void mostra() {
        System.out.println(this.toString());
    }
    
    public static void main(String[] args) {
        Porta p = new Porta();
        p.mostra();
        p.abre();
        p.mostra();
        p.pinta("Vermelho");
        p.mostra();
        p.pinta("Amarelo");
        p.mostra();
        p.pinta("Verde");
        p.mostra();
        p.fecha();
        p.mostra();
        System.out.println("Aberta? " + p.estaAberta());
    }
}
