import acm.program.ConsoleProgram;


public class CaesarCipher extends ConsoleProgram {

	public void run() {
		println("This program uses Caesar's cipher for encryption.");
		int key = readInt("Enter encryption key: ");
		String plainText = readLine("Plaintext: ");
		String encryptedText = encryptCaesarCipher(plainText, key);
		println(encryptedText);
	}

	private String encryptCaesarCipher(String plainText, int key) {
		String encryptedText = "";
		
		for (int i = 0; i < plainText.length(); i++) {
			char c = plainText.charAt(i);
			if (c >= 'A' && c <= 'Z') {
				c = (char) ((c - 'A' + key) % 26 + 'A');
			} else if (c >= 'a' && c <= 'z') {
				c = (char) ((c - 'a' + key) % 26 + 'a');
			}
			encryptedText += c;
		}
		
		return encryptedText;
	}
	
}
