/*
 * Copyright(C) 2013 Yutaka Kato
 */
package local.JPL.ch04.ex04_02;

public class SimpleSortHarness extends SortHarness<Double> implements SortInterface {

    @Override
    public void doSort() {
        for (int i = 0; i < getDataLength(); i++) {
            for (int j = i + 1; j < getDataLength(); j++) {
                if (compare(i, j) > 0) {
                    swap(i, j);
                }
            }
        }
    }
}
