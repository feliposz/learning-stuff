import acm.program.ConsoleProgram;

public class LetterSubstitutionCipher extends ConsoleProgram {

	public void run() {
		println("This program implements a letter substitution cipher.");
		String key;
		do {
			key = readLine("Enter a valid key of 26 letters: ");
		} while (!validKey(key));
		String plainText = readLine("Plaintext: ");
		String encryptedText = encryptString(plainText, key);
		println("Encrypted: " + encryptedText);
		String decryptedText = decryptString(encryptedText, key);
		println("Decrypted: " + decryptedText);
	}
	
	public boolean validKey(String key) {
		// must have exactly 26 letters
		if (key.length() != 26)
			return false;
		for (int i = 0; i < 26; i++) {
			char c = key.charAt(i);
			// all must be uppercase letters
			if (c < 'A' || c > 'Z')
				return false;
			// check for repetitions (could equaly just check if string has all A-Z letters)
			for (int j = 0; j < i; j++) {
				if (key.charAt(j) == c)
					return false;
			}
		}
		return true;
	}
	
	public String encryptString(String str, String key) {
		String encrypted = ""; 
		for (int i = 0; i < str.length(); i++) {
			encrypted += encryptCharacter(str.charAt(i), key);
		}
		return encrypted;
	}

	public String decryptString(String str, String key) {
		String decrypted = ""; 
		for (int i = 0; i < str.length(); i++) {
			decrypted += decryptCharacter(str.charAt(i), key);
		}
		return decrypted;	
	}
	
	private char encryptCharacter(char c, String key) {
		if (c >= 'A' && c <= 'Z') {
			c = key.charAt(c - 'A');
		} else if (c >= 'a' && c <= 'z') {
			c = Character.toLowerCase(key.charAt(c - 'a'));
		}
		return c;
	}
	
	private char decryptCharacter(char c, String key) {
		if (c >= 'A' && c <= 'Z') {
			c = (char)(key.indexOf(c) + 'A');
		} else if (c >= 'a' && c <= 'z') {
			c = (char)(key.indexOf(Character.toUpperCase(c)) + 'a');
		}
		return c;
	}

}
