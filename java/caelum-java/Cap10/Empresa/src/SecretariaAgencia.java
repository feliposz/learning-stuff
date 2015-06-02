
public class SecretariaAgencia extends Secretaria {

	@Override
	public double getBonificacao() {
		return this.salario * 1.15 + 100.0;
	}

}
