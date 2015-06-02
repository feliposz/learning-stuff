/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package my.learnjava;

/**
 *
 * @author Felipo
 */
public class Finalized {

    @Override
    protected void finalize() throws Throwable {
        System.out.println("finalize()");
        super.finalize();        
    }

    public static void main(String[] args) {
        Finalized f = new Finalized();
        f = null;
        System.gc();
        System.out.println("The End.");
    }
}
