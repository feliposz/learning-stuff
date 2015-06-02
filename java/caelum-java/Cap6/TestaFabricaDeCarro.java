class TestaFabricaDeCarro {
    public static void main(String args[]) {
        FabricaDeCarro fabrica = FabricaDeCarro.getFabrica();
        Carro c1 = fabrica.getNovoCarro();
        Carro c2 = fabrica.getNovoCarro();
        Carro c3 = fabrica.getNovoCarro();
        
        System.out.println("c1 = " + c1.getId());
        System.out.println("c2 = " + c2.getId());
        System.out.println("c3 = " + c3.getId());

        FabricaDeCarro outraFabrica = FabricaDeCarro.getFabrica();
        
        Carro c4 = fabrica.getNovoCarro();
        Carro c5 = fabrica.getNovoCarro();
        Carro c6 = fabrica.getNovoCarro();
        
        System.out.println("c4 = " + c4.getId());
        System.out.println("c5 = " + c5.getId());
        System.out.println("c6 = " + c6.getId());
        
        System.out.println("É a mesma fábrica? " + (fabrica == outraFabrica));
        
    }
}