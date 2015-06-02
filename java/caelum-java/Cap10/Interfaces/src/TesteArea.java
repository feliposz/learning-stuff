
public class TesteArea {
	public static void main(String[] args) {
		Quadrado q = new Quadrado(5);
		Retangulo r = new Retangulo(3, 2);
		Circulo c = new Circulo(3.0);
		System.out.println(q.calculaArea());
		System.out.println(r.calculaArea());
		System.out.println(c.calculaArea());
	}
}
