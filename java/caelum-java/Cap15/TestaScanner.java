import java.util.*;

class TestaScanner {
    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            if (sc.hasNextInt()) {
                System.out.println("int: " + sc.nextInt());
            } else if (sc.hasNextLong()) {
                System.out.println("long: " + sc.nextLong());
            } else if (sc.hasNextFloat()) {
                System.out.println("float: " + sc.nextFloat());
            } else if (sc.hasNextDouble()) {
                System.out.println("double: " + sc.nextDouble());
            } else {
                System.out.println("next: " + sc.next());
            }
        }
    }
}