import acm.program.*;
import java.util.*;

public class CountName extends ConsoleProgram {

	public void run() {
		Map<String, Integer> nameCount = new HashMap<String, Integer>();		
		while (true) {
			String name = readLine("Enter name: ");
			if (name.equals(""))
				break;
			Integer count = nameCount.get(name);
			nameCount.put(name, count == null ? 1 : count + 1);			
		}
		for (String name : nameCount.keySet()) {
			println("Entry [" + name + "] has count " + nameCount.get(name));  
		}
	}
	
}
