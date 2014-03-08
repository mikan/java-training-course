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
@SuppressWarnings("serial")
public class ThreadPoolQueue<T> extends LinkedList<T> {

    private final int size;

    public ThreadPoolQueue(int size) {
        if (size < 1) {
            throw new IllegalArgumentException("Illegal size of queue.");
        }
        this.size = size;
    }

    /**
     * Appends the specified element to the end of this list.
     * 
     * @param e element to be appended to this list
     * @return true, or false if interrupted on waiting for polling
     */
    @Override
    public synchronized boolean add(T e) {
        if (size() >= size)
            try {
                wait(); // Wait for poll()
            } catch (InterruptedException e1) {
                return false;
            }
        boolean result = super.add(e);
        notifyAll(); // Notify for poll()
        return result;
    }

    /**
     * Retrieves and removes the head of this list.
     * 
     * @return the head of this list, or null if interrupted on waiting for adding
     */
    @Override
    public synchronized T poll() {
        if (isEmpty())
            try {
                wait(); // Wait for add()
            } catch (InterruptedException e1) {
                return null;
            }
        T e = super.poll();
        notifyAll(); // Notify add()
        return e;
    }
    
    public synchronized void stop() {
        notifyAll();
    }
}
