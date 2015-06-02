class ProfessorDaFaculdade extends EmpregadoDaFaculdade {
    int horasDeAula;
    double getGastos() {
        return super.getGastos() + this.horasDeAula * 10;
    }
    String getInfo() {
        String informacaoBasica = super.getInfo();
        String informacao = informacaoBasica + " horas de aula: " + this.horasDeAula;
        return informacao;
    }
}
