/*
 * Copyright(C) 2013 Yutaka Kato
 */
package local.JPL.ch04.ex04_04;

import java.util.Iterator;

public interface Collection<E> {
    boolean add(E e);
    boolean addAll(Collection<? extends E> c);
    void clear();
    boolean contains(Object o);
    boolean isEmpty();
    abstract Iterator<E> iterator();
    boolean remove(Object o);
    boolean removeAll(Collection<?> c);
    boolean retainAll(Collection<?> c);
    int size();
    Object[] toArray();
    <T> T[] toArray(T[] a);
}
