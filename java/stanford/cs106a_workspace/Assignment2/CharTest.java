import acm.program.*;

public class CharTest extends ConsoleProgram {
	public void run() {
		println("Test.");
		for (char ch = 'à'; ch <= 'ý'; ch++) {
			println(ch + " -> " + Character.toUpperCase(ch));
		}
	}
}

