/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package my.learnjava;

/**
 *
 * @author Felipo
 */
public class Arguments {
    public static void main(String[] args) {
        System.out.println("Arguments test program. Printing passed command-line arguments:");
        for (int i = 0; i < 3 && i < args.length; i++) {
            System.out.format("Argument #%d: %s%n", i, args[i]);
        }
    }
}
