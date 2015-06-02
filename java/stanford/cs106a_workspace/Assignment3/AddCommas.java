import acm.program.ConsoleProgram;

public class AddCommas extends ConsoleProgram {

	public void run() {
		while(true) {
			String digits = readLine("Enter a numeric string (empty string for other tests): ");
			if (digits.length() == 0) break;
			println(addCommasToNumericString(digits));
		}
		
		println("removeAllOccurrences:");
		println(removeAllOccurrences("This is a test", 't'));
		println(removeAllOccurrences("Summer is here!", 'e'));
		println(removeAllOccurrences("---0---", '-'));
	}
	
	private String addCommasToNumericString(String digits) {
		String result = "";
		// concatenate digits from right to left except for last digit
		for (int j = 1, i = digits.length() - 1; i > 0; j++, i--) {
			char c = digits.charAt(i);
			result = c + result;
			if (j % 3 == 0) // add comma separator every 3 digits
				result = ',' + result;
		}
		// add last digit sepparately for special case
		result = digits.charAt(0) + result;
		return result;
	}
	
	private String removeAllOccurrences(String str, char ch) {
		String result = "";
		for (int i = 0; i < str.length(); i++) {
			char c = str.charAt(i);
			if (c != ch)
				result += c;
		}
		return result;
	}
	
}
