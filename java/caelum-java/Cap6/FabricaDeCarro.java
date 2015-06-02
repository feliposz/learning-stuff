class FabricaDeCarro {
    
    private FabricaDeCarro() { 
        // Construtor privado para suprimir o construtor p�blico padr�o
    }
    
    // Cria uma inst�ncia �nica
    private static FabricaDeCarro fabrica = new FabricaDeCarro();

    // Retorna a inst�ncia �nica (sempre a mesma)
    public static FabricaDeCarro getFabrica() {
        return fabrica;
    }
    
    // Funcionalidades do SingleTon
    
    public Carro getNovoCarro() {
        return new Carro();
    }
}
