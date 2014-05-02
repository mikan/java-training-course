/*
 * Copyright(C) 2014 Yutaka Kato
 */
package local.JPL.ch22.ex22_02;

import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

public class WhichChars {

    // private BitSet used = new BitSet();
    private Set<Character> used = new HashSet<>();

    public WhichChars(String str) {
        for (int i = 0; i < str.length(); i++) {
            // used.set(str.charAt(i));
            used.add(str.charAt(i));
        }
    }

    @Override
    public String toString() {
        Set<Character> used = new TreeSet<>(this.used);
        String desc = "[";
        // for (int i = used.nextSetBit(0); i >= 0; i = used.nextSetBit(i + 1)) {
        for (Character i : used) {
            desc += (char) i;
        }
        return desc + "]";
    }
}
