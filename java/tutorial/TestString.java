class TestString {
	
	static void printInitials(String fullName) {
		fullName = fullName.trim();
		String initials = fullName.substring(0, 1);
		int index = 1;
		do {
			index = fullName.indexOf(" ", index) + 1;
			if (index > 0)
				initials += fullName.charAt(index);
		} while (index > 0);
		System.out.println(initials);
	}

	public static void main(String[] args) {
		printInitials("Felipo Soranz");
		printInitials("Rafaella Capelari Silva Barros");
		printInitials("Olavo de Carvalho");
    }

}
