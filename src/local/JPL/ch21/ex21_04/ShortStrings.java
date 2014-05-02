/*
 * Copyright(C) 2014 Yutaka Kato
 */
package local.JPL.ch21.ex21_04;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class ShortStrings implements Iterator<String> {

    private Iterator<String> strings;
    private String nextShort;
    private final int maxLen;

    public ShortStrings(Iterator<String> strings, int maxLen) {
        this.strings = strings;
        this.maxLen = maxLen;
        nextShort = null;
    }

    @Override
    public boolean hasNext() {
        if (nextShort != null)
            return true;
        while (strings.hasNext()) {
            nextShort = strings.next();
            if (nextShort.length() <= maxLen)
                return true;
        }
        nextShort = null;
        return false;
    }

    @Override
    public String next() {
        if (nextShort == null && !hasNext())
            throw new NoSuchElementException();
        String n = nextShort;
        nextShort = null;
        return n;
    }

    @Override
    public void remove() {
        throw new UnsupportedOperationException();
    }
}
