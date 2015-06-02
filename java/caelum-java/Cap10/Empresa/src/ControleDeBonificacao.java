
public class ControleDeBonificacao {
	
	private double totalDeBonificacaoes;

	public void registra(Funcionario f) {
		System.out.println("Adicionando bonificação do funcionário: " + f);
		this.totalDeBonificacaoes += f.getBonificacao();
	}
	
	public double getTotalDeBonificacaoes() {
		return totalDeBonificacaoes;
	}
}
