class Carro {
    private static int idSeq;
    
    private int id;
    
    Carro() {
        this.id = idSeq++;
    }
    
    public int getId() {
        return this.id;
    }
}
