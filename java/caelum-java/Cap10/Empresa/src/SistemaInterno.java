
public class SistemaInterno {

	void login(Autenticavel f) {
		int senha = 123;
		boolean ok = f.autentica(senha);
		if (ok) {
			System.out.println("Login realizado");
		} else {
			System.out.println("Acesso negado");
		}
	}
}
