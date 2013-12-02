/*
 * Copyright(C) 2013 Yutaka Kato
 */
package local.JPL.ch04.ex04_03;

public interface LinkedList<T> {
    public T getBody();
    public LinkedList<T> getNext();
    public void setNext(LinkedList<T> next);
    public boolean hasNext();
    public int size();
}
