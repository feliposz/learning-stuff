<Query Kind="Program" />

void Main()
{
	Booly.And(new Truey(), new Truey()).Dump();
	Booly.And(new Truey(), new Falsey()).Dump();
	Booly.And(new Falsey(), new Truey()).Dump();
	Booly.And(new Falsey(), new Falsey()).Dump();
}

// Define other methods and classes here

abstract class Booly
{
	public abstract Booly Not(); 
	public static Booly And(Truey a, Truey b) { return new Truey(); }
	public static Booly And(Booly a, Booly b) { return new Falsey(); }
}
class Truey : Booly { public override Booly Not() { return new Falsey(); } }
class Falsey : Booly { public override Booly Not() { return new Truey(); } }