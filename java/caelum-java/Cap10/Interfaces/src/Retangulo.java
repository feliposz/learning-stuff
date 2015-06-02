
public class Retangulo implements AreaCalculavel {

	
	private int altura;
	private int largura;
	
	public Retangulo(int largura, int altura) {
		this.altura = altura;
		this.largura = largura;
	}

	@Override
	public double calculaArea() {
		return this.largura * this.altura;
	}

}
