public interface Conta {
	void deposita(double valor);

	public double getSaldo();

	void setLimite(double limite);

	void atualiza(double taxa);

	boolean saca(double valor);

	boolean transferePara(Conta destino, double valor);
}
