/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package my.learnjava;

/**
 *
 * @author Felipo
 */
public class DataOnly {
    int i;
    float f;
    boolean b;

    int storage(String s) {
        return s.length() * 2;
    }
    public static void main(String[] args) {
        DataOnly d = new DataOnly();
        d.i = 47;
        d.f = 1.1f;
        d.b = false;
        System.out.println("Data stored in the object:");
        System.out.format("d.i = %d%nd.f = %f%nd.b = %b%n", d.i, d.f, d.b);
        String s = "Felipo Soranz";
        System.out.println("Content of string s is: " + s);
        System.out.println("Return of storage(s) is: " + d.storage(s));
    }
}
