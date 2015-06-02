import acm.program.ConsoleProgram;
import java.util.*;

public class UniqueNames extends ConsoleProgram {

	public void run() {
		ArrayList<String> list = new ArrayList<String>();
		
		while (true) {
			String name = readLine("Enter a name: ");
			if (name.length() == 0)
				break;
			if (list.indexOf(name) < 0)
				list.add(name);
		}
		
		println("Unique name list contains:");
		for (int i = 0; i < list.size(); i++) {
			println(list.get(i));
		}
	}
	
}
