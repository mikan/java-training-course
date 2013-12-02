/*
 * Copyright(C) 2013 Yutaka Kato
 */
package local.JPL.ch04.ex04_02;

public class TestSort {

    static Double[] testData = { 0.3, 1.3e-2, 7.9, 3.17 };

    public static void main(String[] args) {
        SortHarness<Double> bsort = new SimpleSortHarness();
        SortMetrics metrics = bsort.sort(testData, (SortInterface) bsort);
        System.out.println("Metrics: " + metrics);
        for (double d : testData) {
            System.out.println("\t" + d);
        }
    }
}
