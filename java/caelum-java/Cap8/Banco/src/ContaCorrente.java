public class ContaCorrente extends Conta {
	public void atualiza(double taxa) {
		super.atualiza(taxa * 2.0);
	}

	public boolean saca(double valor) {
		return super.saca(valor + 0.10);
	}
}
