public class Gerente extends Funcionario implements Autenticavel {
	@Override
	public double getBonificacao() {
		return this.salario * 1.4 + 1000;
	}
	
	private int senha;

	public boolean autentica(int senha) {
		return senha == this.senha;
	}

	public void setSenha(int senha) {
		this.senha = senha;
	}
	
}
