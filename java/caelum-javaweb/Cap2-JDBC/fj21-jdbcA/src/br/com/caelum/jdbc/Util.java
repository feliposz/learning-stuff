package br.com.caelum.jdbc;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

/*
 * Uma classe com funções úteis para tratamento de dados em toda aplicação 
 */
public class Util {

	// Formatação padrão
	private static DateFormat dataSimples = new SimpleDateFormat("dd/MM/yyyy");
	
	// Formata uma data para uma string padrão
	public static String formatarData(Calendar data) {
		return dataSimples.format(new Date(data.getTimeInMillis()));
	}
	
	// How to generate a random alpha-numeric string?
	// http://stackoverflow.com/a/157202/1391232

	private static final String AB = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	private static Random rnd = new Random();

	public static String randomString( int len ) 
	{
	   StringBuilder sb = new StringBuilder( len );
	   for( int i = 0; i < len; i++ ) 
	      sb.append( AB.charAt( rnd.nextInt(AB.length()) ) );
	   return sb.toString();
	}
}
