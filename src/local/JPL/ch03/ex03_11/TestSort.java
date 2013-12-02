/*
 * Copyright(C) 2013 Yutaka Kato
 */
package local.JPL.ch03.ex03_11;

public class TestSort {

    static double[] testData = { 0.3, 1.3e-2, 7.9, 3.17 };

    public static void main(String[] args) {
        SortDouble bsort = new SimpleSortDouble();
        SortMetrics metrics = bsort.sort(testData);
        System.out.println("Metrics: " + metrics);
        for (double d : testData) {
            System.out.println("\t" + d);
        }
    }
}
