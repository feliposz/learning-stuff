package br.com.felipo.banco;

import br.com.felipo.banco.conta.Conta;
import br.com.felipo.banco.conta.ContaCorrente;
import br.com.felipo.banco.conta.ContaPoupanca;

public class TestaComparacaoConta {
	public static void main(String[] args) {
		Conta cp = new ContaPoupanca();
		Conta cc = new ContaCorrente();
		
		cp.setNumero(123);
		cc.setNumero(123);
		
		System.out.println("cp == cc = " + (cp == cc));
		System.out.println("cp.equals(cc) = " + (cp.equals(cc)));
		
		cc.setNumero(456);
		
		System.out.println("cp == cc = " + (cp == cc));
		System.out.println("cp.equals(cc) = " + (cp.equals(cc)));
	}
}
