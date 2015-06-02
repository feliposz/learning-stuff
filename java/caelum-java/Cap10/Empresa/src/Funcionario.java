public abstract class Funcionario {
	protected double salario;

	public void setSalario(double salario) {
		this.salario = salario;
	}

	abstract public double getBonificacao();
	
	public void mostra() {
		System.out.println("Funcion�rio - sal�rio: " + salario 
				+ ", bonifica��o: " + getBonificacao());
	}
}
