/*
 * Exercise 4 in chapter 4 of Think Java
 */

public class Narf {

    public static void zoop(String fred, int bob) {
        System.out.println(fred); // 4(just for, 5), 9 (breakfast,4)
        if (bob == 5) {
            ping("not "); // 5
        } else {
            System.out.println("!"); // 10
        }
    }

    public static void main(String[] args) {
        int bizz = 5; // 1
        int buzz = 2; // 2
        zoop("just for", bizz); // 3
        clink(2*buzz); // 6
    }

    public static void clink(int fork) {
        System.out.print("It's "); // 7
        zoop("breakfast ", fork) ; // 8
    }

    public static void ping(String strangStrung) {
        System.out.println("any " + strangStrung + "more "); //5.1
    }
}

// just for
// any not more
// It's breakfast
// !
