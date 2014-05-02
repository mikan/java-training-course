/*
 * Copyright(C) 2014 Yutaka Kato
 */
package local.JPL.ch22.ex22_03;

import java.util.BitSet;
import java.util.HashMap;
import java.util.Map;

public class WhichChars {

    private static final int UPPER_MASK = 0xFF00;
    private static final int LOWER_MASK = 0xFF;
    private Map<Integer, BitSet> map = new HashMap<>();

    public WhichChars(String str) {
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            BitSet used = map.get(c & UPPER_MASK);
            if (used == null)
                used = new BitSet();
            used.set(c & LOWER_MASK);
            map.put(c & UPPER_MASK, used);
        }
    }

    @Override
    public String toString() {
        String desc = "[";
        for (BitSet used : map.values()) {
            for (int i = used.nextSetBit(0); i >= 0; i = used.nextSetBit(i + 1)) {
                desc += (char) i;
            }
        }
        return desc + "]";
    }
}
