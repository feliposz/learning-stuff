class TesteArgs {
    public static void main(String[] args) {
        System.out.println("Foram inseridos " + args.length + " argumentos. São eles: ");
        for (String arg : args) {
            System.out.println(arg);
        }
    }
}