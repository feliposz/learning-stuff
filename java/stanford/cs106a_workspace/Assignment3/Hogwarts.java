/* 
 * File: Hogwarts.java 
 * ------------------- 
 * This program is just testing your understanding of parameter passing. 
 */ 
import acm.program.*;

public class Hogwarts extends ConsoleProgram {
	 public void run() { 
	  bludger(2001); 
	 } 
	 private void bludger(int y) { 
	  int x = y / 1000; //2
	  int z = (x + y);  //2003
	  x = quaffle(z, y); //2003,2001=1000
	  println("bludger: x = " + x + ", y = " + y + ", z = " + z);
	  //3:println("bludger: x = 1001 y = 2001 z = 2003");
	 } 
	 private int quaffle(int x, int y) { 
	  int z = snitch(x + y, y); //4004,2001=1001
	  y /= z; //2001/1000=1
	  println("quaffle: x = " + x + ", y = " + y + ", z = " + z); 
	  //2:println("quaffle: x = 2003 y = 1 z = 1001");
	  return z; //1000
	 } 
	 private int snitch(int x, int y) { 
	  y = x / (x % 10); //4004 / (4004%10) = 4004 / 4 = 1001
	  println("snitch: x = " + x + ", y = " + y);
	  //1: println("snitch: x = 4004 y = 1001");
	  return y; 
	 } 
}
