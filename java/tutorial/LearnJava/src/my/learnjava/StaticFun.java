/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package my.learnjava;

/**
 *
 * @author Felipo
 */
public class StaticFun {
    static void incr() { StaticTest.i++; }

    public static void main(String[] args) {
        StaticTest st1 = new StaticTest();
        StaticTest st2 = new StaticTest();
        st1.i++;
        StaticTest.i++;
        StaticFun.incr();
        StaticFun sf = new StaticFun();
        sf.incr();
    }
}

class StaticTest {
    static int i = 47;
}