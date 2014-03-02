/*
 * Copyright(C) 2014 Yutaka Kato
 */
package local.JPL.ch14.ex14_02;

import java.util.LinkedList;
import java.util.Queue;

public class SingleLinkQueue<E> {

    private Queue<E> queue = new LinkedList<>();
    
    public void add(E element) {
        queue.add(element);
    }

    public E remove() {
        return queue.remove();
    }
    
    public int size() {
        return queue.size();
    }
}
