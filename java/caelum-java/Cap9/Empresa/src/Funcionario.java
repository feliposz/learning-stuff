public abstract class Funcionario {
	protected double salario;

	public void setSalario(double salario) {
		this.salario = salario;
	}

	abstract public double getBonificacao();
	
	public void mostra() {
		System.out.println("Funcionário - salário: " + salario 
				+ ", bonificação: " + getBonificacao());
	}
}
