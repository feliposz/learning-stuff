class FabricaDeCarro {
    
    private FabricaDeCarro() { 
        // Construtor privado para suprimir o construtor público padrão
    }
    
    // Cria uma instância única
    private static FabricaDeCarro fabrica = new FabricaDeCarro();

    // Retorna a instância única (sempre a mesma)
    public static FabricaDeCarro getFabrica() {
        return fabrica;
    }
    
    // Funcionalidades do SingleTon
    
    public Carro getNovoCarro() {
        return new Carro();
    }
}
