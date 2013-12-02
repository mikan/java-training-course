/*
 * Copyright(C) 2013 Yutaka Kato
 */
package local.JPL.ch04.ex04_04;

public interface List<E> extends Collection<E> {
    E get(int index);
    E set(int index, E element);
    int indexOf(Object o);
    int lastIndexOf(Object o);
    List<E> subList(int fromIndex, int toIndex);
}
