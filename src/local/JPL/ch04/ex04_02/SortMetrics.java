/*
 * Copyright(C) 2013 Yutaka Kato
 */
package local.JPL.ch04.ex04_02;

public class SortMetrics implements Cloneable {
    public long probeCnt;
    public long compareCnt;
    public long swapCnt;

    public void init() {
        probeCnt = swapCnt = compareCnt = 0;
    }

    public String toString() {
        return probeCnt + " probes " + compareCnt + " compares " + swapCnt
                + " swaps";
    }

    /** このクラスは clone をサポートしている。 */
    public SortMetrics clone() {
        // デフォルトの仕組みで十分
        try {
            return (SortMetrics) super.clone();
        } catch (CloneNotSupportedException e) {
            // 起こり得ない。このクラスと Object は複製できる。
            throw new InternalError(e.toString());
        }
    }
}
