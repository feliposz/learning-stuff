class TestaEmpresa {
    public static void main(String[] args) {
        Empresa emp = new Empresa(3);
        
        
        for (int i = 0; i < 10; i++) {
            Data d = null;
            try { d = new Data(i%28 + 1, i%12 + 1, 1997 + i); } catch (Exception e) {}
           
            Funcionario f = new Funcionario("Funcionario " + i);
            f.setDepartamento("Departamento " + (i*5));
            f.setSalario(20000.00 - (i * 500));
            f.setDataEntrada(d);
            f.setRg("000" + i + "000");
            
            emp.adiciona(f);
        }

        emp.mostra();
    }
}