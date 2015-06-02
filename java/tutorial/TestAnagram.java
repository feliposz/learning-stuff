class TestAnagram {
	static boolean testAnagram(String str1, String str2) {
		for (int i = 0; i < str1.length(); i++) {
			if (!Character.isLetter(str1.charAt(i)))
				continue;
			if (str2.indexOf(str1.charAt(i)) == -1)
				return false;
		}
		return true;
	}
	
	public static void main(String[] args) {
		String[] words = { "parliament", "partial men", "software", "swear oft", "neo", "one" };
		
		for (int i=0; i<words.length; i++) {
			for (int j=0; j<i; j++) {
				if (testAnagram(words[i], words[j]))
					System.out.format("%s is an anagram of %s%n", words[i], words[j]);
				/*else
					System.out.format("%s is NOT an anagram of %s%n", words[i], words[j]);
				*/
			}
		}
	}
}
