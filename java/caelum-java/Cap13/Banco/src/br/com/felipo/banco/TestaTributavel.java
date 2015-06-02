package br.com.felipo.banco;
import br.com.felipo.banco.conta.ContaCorrente;
import br.com.felipo.banco.conta.SeguroDeVida;
import br.com.felipo.banco.conta.Tributavel;
import br.com.felipo.banco.conta.ValorInvalidoException;


public class TestaTributavel {

	public static void main(String[] args) throws ValorInvalidoException {
		ContaCorrente cc = new ContaCorrente();
		cc.deposita(100);
		System.out.println(cc.calculaTributos());
		
		Tributavel t = cc;
		System.out.println(t.calculaTributos());
		
		SeguroDeVida sdv = new SeguroDeVida();
		System.out.println(sdv.calculaTributos());
	}

}
