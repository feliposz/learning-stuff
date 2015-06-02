public class Diretor extends Funcionario implements Autenticavel {

	private int senha;

	public boolean autentica(int senha) {
		return senha == this.senha;
	}

	public void setSenha(int senha) {
		this.senha = senha;
	}

	@Override
	public double getBonificacao() {
		return this.salario * 1.4 + 1000;
	}

}
