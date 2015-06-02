class Reversed implements java.lang.CharSequence {

    String reversedString;

    public Reversed(String original) {
	StringBuilder sb = new StringBuilder(original);
        reversedString = sb.reverse().toString();
    }

    public char charAt(int index) throws IndexOutOfBoundsException {
        return reversedString.charAt(index);
    }

    public int length() {
        return reversedString.length();
    }    

    public CharSequence subSequence(int start, int end) throws IndexOutOfBoundsException {
        return reversedString.subSequence(start, end);
    }

    public String toString() {
        return reversedString;
    }
}