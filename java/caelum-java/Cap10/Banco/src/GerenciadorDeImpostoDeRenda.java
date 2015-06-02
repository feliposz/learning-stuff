public class GerenciadorDeImpostoDeRenda {
	private double total;

	public double getTotal() {
		return total;
	}

	void adiciona(Tributavel t) {
		System.out.println("Adicionando tribut�vel: " + t);
		total += t.calculaTributos();
	}
}
