/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package my.learnjava;

/**
 *
 * @author Felipo
 */
public class AllTheColorsOfTheRainbow {
    int anIntegerRepresentingColors;

    void changeTheHueOfThecolor(int newHue) {
        anIntegerRepresentingColors = newHue;
    }

    public static void main(String[] args) {
        AllTheColorsOfTheRainbow atcotr = new AllTheColorsOfTheRainbow();

        atcotr.changeTheHueOfThecolor(10);
        System.out.println(atcotr.anIntegerRepresentingColors);
    }
}
