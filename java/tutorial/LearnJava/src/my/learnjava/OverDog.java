/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package my.learnjava;

/**
 *
 * @author Felipo
 */
public class OverDog {

    OverDog() {
        this("this is it!");
        System.out.println("OverDog()");
    }
    OverDog(String s)
    {
        System.out.println("OverDog(\"" + s + "\")");
    }
    void bark(int i) {
        System.out.println("bark(int)");
    }
    void bark(float f) {
        System.out.println("bark(float)");
    }
    void bark(int i, float f) {
        System.out.println("bark(int, float)");
    }
    void bark(float f, int i) {
        System.out.println("bark(float, int)");
    }
    void bark(char c) {
        System.out.println("bark(char)");
    }
    void bark(Object o) {
        System.out.println("bark(Object)");
    }
    void bark(String s) {
        System.out.println("bark(String)");
    }

    void first() {
        System.out.println("first()");
        second();
        this.second();
    }

    void second() {
        System.out.println("second()");
    }

    public static void main(String[] args) {
        OverDog myDog = new OverDog();
        myDog.bark(1);
        myDog.bark(2.0f);
        myDog.bark(3, 4.0f);
        myDog.bark(5.0f, 6);
        myDog.bark('7');
        myDog.bark(myDog);
        myDog.bark("8");
        myDog.first();
    }
}
