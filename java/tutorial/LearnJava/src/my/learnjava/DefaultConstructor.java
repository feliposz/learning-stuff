/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package my.learnjava;

/**
 *
 * @author Felipo
 */
public class DefaultConstructor {
    DefaultConstructor() {
        System.out.println("DefaultConstructor() called.");
    }

    DefaultConstructor(String s) {
        System.out.println("DefaultConstructor(\"" + s + "\") called");
    }

    public static void main(String[] args) {
        DefaultConstructor dc = new DefaultConstructor();
        DefaultConstructor dc2 = new DefaultConstructor("Bom dia.");
        DefaultConstructor[] arrayOfDc = new DefaultConstructor[5];
        for (int i=0; i<arrayOfDc.length; i++) {
            arrayOfDc[i] = new DefaultConstructor(String.valueOf(i));
        }
        String[] arrayOfStrings = { "banana", "laranja", "limao", "abacate" };
        for (int i=0; i<arrayOfStrings.length; i++) {
            System.out.format("String[%d] = \"%s\"%n", i, arrayOfStrings[i]);
        }
    }
}
