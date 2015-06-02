import acm.program.*;


public class StringExamples extends ConsoleProgram {
	
	public boolean isPalindrome(String str) {
		for (int i = 0, j = str.length() - 1; i < j; i++, j--) {
			if (str.charAt(i) != str.charAt(j))
				return false;
		}
		return true;
	}
	
	public String reverseString(String str) {
		String reversed = "";
		for (int i = str.length() - 1; i >= 0; i--) {			
			reversed += str.charAt(i);
		}
		return reversed;
	}
	
	public int countUppercase(String str) {
		int count = 0;
		for (int i = 0; i < str.length(); i++) {
			if (Character.isUpperCase(str.charAt(i))) {
				count++;
			}
		}
		return count;
	}
	
	public String replaceFirstOccurrence(String str, String orig, String repl) {
		int index = str.indexOf(orig);
		if (index != -1) {
			str = str.substring(0, index) + repl + str.substring(index + orig.length());
		}
		return str;
	}
	
	public void run() {
		String[] words = { "ovo", "osso", "natan", "palindrome", "10101", "12131", "capa", "abba",
				"string", "run", "integer", "NaN", "null", "Felipo Soranz", "Felipo opileF", "limbo"};
		for (int i = 0; i < words.length; i++) {
			println("Is " + words[i] + " a palindrome? " + isPalindrome(words[i]));
			if (!isPalindrome(words[i])) {
				println("The reverse of it is " + reverseString(words[i]));
			}
			println("It has " + countUppercase(words[i]) + " uppercase letters.");
			println("Replacing 'li' for 'ru' gives " + replaceFirstOccurrence(words[i], "li", "ru"));
		}
	}
}
