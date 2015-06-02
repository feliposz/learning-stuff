public class Principal {
	public static void main(String[] args) {
		Funcionario f = new Diretor();

		f.setSalario(1000.0);
		
		f.mostra();

		ControleDeBonificacao cdb = new ControleDeBonificacao();
		
		cdb.registra(f);
		System.out.println("Total = " + cdb.getTotalDeBonificacaoes());
	}
}
