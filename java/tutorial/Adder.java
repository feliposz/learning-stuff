class Adder {
	public static void main(String[] args) {
		int total = 0;
		if (args.length > 1) {
			try {
				for (int i = 0; i < args.length; i++) {
					total += Integer.parseInt(args[i]);
				}
				System.out.println(total);
			} catch (NumberFormatException e) {
				System.err.println("Please, enter only integer numbers as input");
			}
		} else {
			System.err.println("Not enough arguments to add.");
		}
	}
}
