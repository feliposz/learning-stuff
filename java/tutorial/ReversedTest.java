class ReversedTest {
    public static void main(String[] args) {
        String str = "Wicked Witch of the West";
        Reversed r = new Reversed(str);
        System.out.println("Original: " + str.toString());
        System.out.println("Reversed: " + r.toString());
        System.out.println("Char at 5 of Original: " + str.charAt(5));
        System.out.println("Char at 5 of Reversed: " + r.charAt(5));
        System.out.println("Length of Original: " + str.length());
        System.out.println("Length of Reversed: " + r.length());
        System.out.println("Subsequence of Original: " + str.subSequence(5,10));
        System.out.println("Subsequence of Reversed: " + r.subSequence(5,10));
    }
}