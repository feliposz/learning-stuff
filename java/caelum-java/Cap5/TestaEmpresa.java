class TestaEmpresa {
    public static void main(String[] args) {
        Empresa emp = new Empresa();
        
        
        for (int i = 0; i < 20; i++) {
            Data d = new Data();
            d.dia = i%28 + 1;
            d.mes = i%12 + 1;
            d.ano = 1997 + i;
            
            Funcionario f = new Funcionario();
            f.nome = "Funcionario " + i;
            f.departamento = "Departamento " + (i*5);
            f.salario = 20000.00 - (i * 500);
            f.dataEntrada = d;
            f.rg = "000" + i + "000";
            
            emp.adiciona(f);
        }       

        emp.mostra();
    }
}