
public class ControleDeBonificacao {
	
	private double totalDeBonificacaoes;

	public void registra(Funcionario f) {
		System.out.println("Adicionando bonifica��o do funcion�rio: " + f);
		this.totalDeBonificacaoes += f.getBonificacao();
	}
	
	public double getTotalDeBonificacaoes() {
		return totalDeBonificacaoes;
	}
}
