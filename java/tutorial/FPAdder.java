class FPAdder {
	public static void main(String[] args) {
		float total = 0;
		if (args.length > 1) {
			try {
				for (int i = 0; i < args.length; i++) {
					total += Float.parseFloat(args[i]);
				}
				System.out.format("%.2f", total);
			} catch (NumberFormatException e) {
				System.err.println("Please, enter only floating point numbers as input");
			}
		} else {
			System.err.println("Not enough arguments to add.");
		}
	}
}
