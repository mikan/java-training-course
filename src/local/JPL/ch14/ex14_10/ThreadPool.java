/*
 * Copyright(C) 2014 Yutaka Kato
 */
package local.JPL.ch14.ex14_10;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Simple Thread Pool class.
 * 
 * This class can be used to dispatch an Runnable object to be executed by a
 * thread.
 * 
 * [Instruction] Implement one constructor and three methods. Don't forget to
 * write a Test program to test this class. Pay attention to @throws tags in the
 * javadoc. If needed, you can put "synchronized" keyword to methods. All
 * classes for implementation must be private inside this class. Don't use
 * java.util.concurrent package.
 */
public class ThreadPool {

    // MEMO: https://www.ibm.com/developerworks/jp/java/library/j-jtp0730/
    private MyThread[] threads;
    private Queue<Runnable> queue;

    /**
     * Constructs ThreadPool.
     * 
     * @param queueSize the max size of queue
     * @param numberOfThreads the number of threads in this pool.
     * @throws IllegalArgumentException if either queueSize or numberOfThreads
     *             is less than 1
     */
    public ThreadPool(final int queueSize, final int numberOfThreads) {
        if (queueSize < 1 || numberOfThreads < 1)
            throw new IllegalArgumentException();
        threads = new MyThread[numberOfThreads];
        queue = new LinkedList<Runnable>();
        for (int i = 0; i < threads.length; i++) {
            threads[i] = new MyThread();
        }
    }

    /**
     * Starts threads.
     * 
     * @throws IllegalStateException if threads has been already started.
     */
    public void start() {
        for (int i = 0; i < threads.length; i++) {
            try {
                threads[i].start();
            } catch (IllegalThreadStateException e) {
                throw new IllegalStateException(e);
            }
        }
    }

    /**
     * Stop all threads and wait for their terminations.
     * 
     * @throws IllegalStateException if threads has not been started.
     */
    public void stop() {
        for (int i = 0; i < threads.length; i++) {
            if (threads[i].isAlive())
                try {
                    threads[i].join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            else
                throw new IllegalStateException();
        }
    }

    /**
     * Executes the specified Runnable object, using a thread in the pool. run()
     * method will be invoked in the thread. If the queue is full, then this
     * method invocation will be blocked until the queue is not full.
     * 
     * @param runnable Runnable object whose run() method will be invoked.
     * @throws NullPointerException if runnable is null.
     * @throws IllegalStateException if this pool has not been started yet.
     */
    public synchronized void dispatch(Runnable runnable) {
        if (runnable == null)
            throw new NullPointerException();
        synchronized (queue) {
            queue.add(runnable);
            queue.notify();
        }
    }

    private class MyThread extends Thread {

        @Override
        public void run() {
            Runnable runnable;
            while (true) {
                synchronized (queue) {
                    while (queue.isEmpty()) {
                        try {
                            queue.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        runnable = queue.poll();
                        runnable.run();
                    }
                }
            }
        }
    }
}
