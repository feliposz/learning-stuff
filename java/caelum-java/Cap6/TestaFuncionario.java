class TestaFuncionario {
    public static void main(String[] args) {
    
        Funcionario f = new Funcionario("Jorah");

        Data d = null;
        try {
             d = new Data(23,11,1995);
        } catch (Exception e) {}
        
        f.setNome("Jorah Mormont");
        f.setDepartamento("Conselho");
        f.setSalario(10000);
        f.setDataEntrada(d);
        f.setRg("12.345.678-9");        
        
        f.mostra();
        
        f.recebeAumento(5000);
        f.mostra();
        
        System.out.println("Ganho anual = " + f.getGanhoAnual());
        
        f.demite();
        f.mostra();
        
    }
}

