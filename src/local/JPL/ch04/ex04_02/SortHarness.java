/*
 * Copyright(C) 2013 Yutaka Kato
 */
package local.JPL.ch04.ex04_02;

public abstract class SortHarness<T extends Comparable<T>> {
    private T[] values;
    private final SortMetrics curMetrics = new SortMetrics();

    public final SortMetrics sort(T[] data, SortInterface impl) {
        values = data;
        curMetrics.init();
        impl.doSort();
        return getMetrics();
    }

    public final SortMetrics getMetrics() {
        return curMetrics.clone();
    }

    /** 拡張したクラスが要素の数を知るため */
    protected final int getDataLength() {
        return values.length;
    }

    /** 拡張したクラスが要素を調べるため */
    protected final Object probe(int i) {
        curMetrics.probeCnt++;
        return values[i];
    }

    /** 拡張したクラスが要素を比較するため */
    protected final int compare(int i, int j) {
        curMetrics.compareCnt++;
        T o1 = values[i];
        T o2 = values[j];
        if (o1.equals(o2))
            return 0;
        else
            return o1.compareTo(o2);
    }

    /** 拡張したクラスが要素を交換するため */
    protected final void swap(int i, int j) {
        curMetrics.swapCnt++;
        T tmp = values[i];
        values[i] = values[j];
        values[j] = tmp;
    }
}
