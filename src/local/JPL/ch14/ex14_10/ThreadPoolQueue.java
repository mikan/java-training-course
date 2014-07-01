/*
 * Copyright(C) 2014 Yutaka Kato
 */
package local.JPL.ch14.ex14_10;

import java.util.LinkedList;

/**
 * Blocking queue for thread pool.
 * 
 * @param <T> Type of element
 */
public class ThreadPoolQueue<T> {

    private final int size;
    private LinkedList<T> list;

    public ThreadPoolQueue(int size) {
        if (size < 1) {
            throw new IllegalArgumentException("Illegal size of queue.");
        }
        this.size = size;
        list = new LinkedList<T>();
    }

    /**
     * Appends the specified element to the end of this list.
     * 
     * @param e element to be appended to this list
     * @return true, or false if interrupted on waiting for polling
     */
    public synchronized boolean add(T e) {
        while (list.size() >= size)
            try {
                wait(); // Wait poll()
            } catch (InterruptedException e1) {
                return false;
            }
        boolean result = list.add(e);
        notifyAll(); // Notify poll()
        return result;
    }

    /**
     * Retrieves and removes the head of this list.
     * 
     * @return the head of this list, or null if interrupted on waiting for adding
     */
    public synchronized T poll() {
        if (list.isEmpty())
            try {
                wait(); // Wait add()
            } catch (InterruptedException e1) {
                return null;
            }
        T e = list.poll();
        notifyAll(); // Notify add()
        return e;
    }
    
    public synchronized void stop() {
        notifyAll();
    }
}
