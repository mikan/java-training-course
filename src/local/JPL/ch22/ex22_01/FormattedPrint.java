/*
 * Copyright(C) 2014 Yutaka Kato
 */
package local.JPL.ch22.ex22_01;

public class FormattedPrint {
    
    public static void main(String[] args) {
        double[] data = {-0.1, 1.1, 2.2, 1.0 / 3, 0.000000000001};
        new FormattedPrint().printFPArray(data, 80);
    }
    
    public void printFPArray(double[] array, int columns) {
        if (array == null)
            throw new NullPointerException();
        if (columns <= 0)
            throw new IllegalArgumentException();
        for (double d : array) {
            System.out.printf("%." + columns + "f%n", d);
        }
    }
}
