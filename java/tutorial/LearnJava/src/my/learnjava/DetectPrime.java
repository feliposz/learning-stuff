/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package my.learnjava;

/**
 *
 * @author Felipo
 */
public class DetectPrime {
    public static void main(String[] args) {
        int i, j, count;

        final int max = 100;

        for (i = 1; i < max; i++) {
            count = 0;
            for (j = 1; j < max; j++) {
                if (i%j == 0)
                    count++;
            }
            if (count <= 2) {
                System.out.println(i);
            }
        }
    }
}
