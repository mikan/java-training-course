/*
 * Copyright(C) 2014 Yutaka Kato
 */
package local.JPL.ch21.ex21_04;

import java.util.ListIterator;
import java.util.NoSuchElementException;

public class ShortStrings2 implements ListIterator<String> {
    
    private ListIterator<String> strings;
    private String nextShort;
    private String prevShort;
    private final int maxLen;
    private int index;
    
    public ShortStrings2(ListIterator<String> strings, int maxLen) {
        this.strings = strings;
        this.maxLen = maxLen;
        nextShort = null;
        prevShort = null;
        index = 0;
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
        index++;
        return n;
    }

    @Override
    public boolean hasPrevious() {
        if (prevShort != null)
            return true;
        while (strings.hasPrevious()) {
            prevShort = strings.previous();
            if (prevShort.length() <= maxLen)
                return true;
        }
        prevShort = null;
        return false;
    }

    @Override
    public String previous() {
        if (prevShort == null && !hasPrevious())
            throw new NoSuchElementException();
        String n = prevShort;
        prevShort = null;
        index--;
        return n;
    }

    @Override
    public int nextIndex() {
        return index + 1;
    }

    @Override
    public int previousIndex() {
        return index - 1;
    }

    @Override
    public void remove() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void set(String e) {
        strings.set(e);
    }

    @Override
    public void add(String e) {
        strings.add(e);
    }
}