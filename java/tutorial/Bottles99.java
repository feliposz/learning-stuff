/*
 * Class: Bottle99
 * Implemented by: Felipo Soranz
 * Implemented in: 2012/03/08
 *
 * Exercise 3 of chapter 4 of Think Java
 * There is a lot of "over-engineering" and over-commenting in it just for fun.
 *
 * Description:
 * This class prints the song 99 Bottles of beer with a configurable number of bottles.
 * It uses a recursive method to do that and a lot of sub-methods just because...
 */

public class Bottles99 {

    // Main method to test the Bottles99 class
    public static void main(String[] args) {
        // the default value for lazy people that won't use command line parameters
        final int NUM_BOTTLES = 9;
        int numBottles = 0;
        
        //for (int i = 0; i < args.length; i++) {
        //    System.out.println(i + " : " + args[i]);
        //}
        
        // Parse command line arguments
        if (args.length == 0) { // no command line arguments, default behaviour
            numBottles = NUM_BOTTLES;
        } else if (args.length == 1) {
            if (args[0].toUpperCase().compareTo("HELP") == 0) {
                printUsage();
                return;
            } else {
                try {
                    numBottles = Integer.parseInt(args[0]);
                } catch (NumberFormatException e) {
                    System.out.println("Please, provide a valid parameter.");
                    printUsage();
                    return;
                }
            }
        } else if (args.length > 1) {
            printUsage();
            return;
        }
        
        Bottles99 song = new Bottles99();
        if (numBottles >= 0) { // sanity check
            song.printLyrics(numBottles);
        } else {
            System.out.println("No song for you!");
        }
    }
    
    // To help the user to... use... the program.
    private static void printUsage() {
        System.out.println("usage: java Bottles99 [number of bottles] | help");
    }
    
    // ========================================================================
    // ---------------------------- Class stuff -------------------------------
    // ========================================================================

    // Recursive method to print the entire lyrics. Since each lyric (problem)
    // is a strophe + a smaller version of the lyric (problem)
    public void printLyrics(int numBottles) {
        if (numBottles == 0) {
            printNoBottles();
        } else {
            printSomeBottles(numBottles);
            printLyrics(numBottles - 1);
        }        
    }

    // This is the special case and it is treated "specially" rather than though a bunch of "ifs". Ex:
    // No bottles of beer on the wall, no bottles of beer, ya’ can’t take one down, 
    // ya’ can’t pass it around, ’cause there are no more bottles of beer on the wall!
    private void printNoBottles() {
        printBottlesOfBeer(0, true);
        printWall(false);
        printBottlesOfBeer(0, false);
        printTakePass(false);
        System.out.println("'cause there are no more bottles of beer on the wall!");
    }

    // This is the generic strophe. Ex:
    // 99 bottles of beer on the wall, 99 bottles of beer, ya’ take one down, 
    // ya’ pass it around, 98 bottles of beer on the wall.
    private void printSomeBottles(int numBottles) {
         printBottlesOfBeer(numBottles, true);
         printWall(false);
         printBottlesOfBeer(numBottles, false);
         printTakePass(true);
         printBottlesOfBeer(numBottles - 1, false);
         printWall(true);
    }

    // This prints the part of the verse that states how many bottles of beer
    // we're dealing with. Ex:
    // 99 bottles of beer
    // Params: caps - set to true if the beginning of a sentence.
    private void printBottlesOfBeer(int numBottles, boolean caps) {
        if (numBottles == 0) {
            if (caps)
                System.out.print("N");
            else
                System.out.print("n");
            System.out.print("o bottles of beer");
        } else if (numBottles == 1) {
            System.out.print("1 bottle of beer");
        } else {
            System.out.print(numBottles + " bottles of beer");
        }
    }
    
    // The small verse in the middle. Ex:
    // , ya’ take one down, ya’ pass it around, 
    // Params: caps - set to false if you can't pass it around!
    private void printTakePass(boolean can) {
        System.out.print(", ya' ");
        if (!can) System.out.print("can't ");
        System.out.print("take one down, ya' ");
        if (!can) System.out.print("can't ");
        System.out.print("pass it around, ");
    }
    
    // Another brick " on the wall" + ".\n" or ", "
    private void printWall(boolean paragraph) {
        System.out.print(" on the wall");
        if (paragraph) {
            System.out.println(".");
        } else {
            System.out.print(", ");
        }
    }
    
}