/*
 * Copyright(C) 2014 Yutaka Kato
 */
package local.JPL.ch14.ex14_10;

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

    private WorkerThread[] threads;
    private ThreadPoolQueue<Runnable> queue;
    private boolean started;

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
        threads = new WorkerThread[numberOfThreads];
        queue = new ThreadPoolQueue<Runnable>(queueSize);
        for (int i = 0; i < threads.length; i++)
            threads[i] = new WorkerThread();
    }

    /**
     * Starts threads.
     * 
     * @throws IllegalStateException if threads has been already started.
     */
    public void start() {
        if (started)
            throw new IllegalStateException("Already started.");
        for (int i = 0; i < threads.length; i++) {
            try {
                threads[i] = new WorkerThread();
                threads[i].start();
            } catch (IllegalThreadStateException e) {
                throw new IllegalStateException(e);
            }
        }
        started = true;
    }

    /**
     * Stop all threads and wait for their terminations.
     * 
     * @throws IllegalStateException if threads has not been started.
     */
    public void stop() {
        for (WorkerThread thread : threads) {
            if (thread.isAlive()) {
                thread.stopRequest();
                try {
                    thread.join();
                } catch (InterruptedException e) {
                    System.err.println("InterruptedException on stop()");
                }
            } else {
                throw new IllegalStateException();
            }
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
        if (!started)
            throw new IllegalStateException("Not statrted.");
        queue.add(runnable);
    }

    /**
     * Worker thread.
     * 
     * @see https://www.ibm.com/developerworks/jp/java/library/j-jtp0730/
     */
    private class WorkerThread extends Thread {

        private boolean stopping;

        @Override
        public void run() {
            Runnable runnable = null;
            while (!stopping) {
                runnable = queue.poll();
                if (runnable != null)
                    runnable.run();
            }
        }

        private void stopRequest() {
            stopping = true;
            queue.stop();
        }
    }
}
