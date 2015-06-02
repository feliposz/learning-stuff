class GeradorDeRelatorio {
    public void adiciona(EmpregadoDaFaculdade f) {
        System.out.println(f.getInfo());
        System.out.println(f.getGastos());
    }
    
    public static void main(String args[]) {
        GeradorDeRelatorio relatorio = new GeradorDeRelatorio();
        
        EmpregadoDaFaculdade[] empregados = new EmpregadoDaFaculdade[3];
        
        empregados[0] = new EmpregadoDaFaculdade();
        empregados[0].nome = "Zé";
        empregados[0].salario = 1000.0;
        empregados[1] = new ProfessorDaFaculdade();
        empregados[1].nome = "João";
        empregados[1].salario = 2000.0;
        ((ProfessorDaFaculdade)empregados[1]).horasDeAula = 30;
        empregados[2] = new Reitor();
        empregados[2].nome = "Joaquim";
        empregados[2].salario = 5000.0;
        
        for (EmpregadoDaFaculdade empregado : empregados) {
            relatorio.adiciona(empregado);
        }
    }
}

