public class Presidente extends Funcionario {

	@Override
	public double getBonificacao() {
		return this.salario * 1.5 + 2000.0;
	}

}
