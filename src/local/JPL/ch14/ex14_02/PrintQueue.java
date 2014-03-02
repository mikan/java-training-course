/*
 * Copyright(C) 2014 Yutaka Kato
 */
package local.JPL.ch14.ex14_02;

public class PrintQueue {

    private SingleLinkQueue<PrintJob> queue = new SingleLinkQueue<>();

    public synchronized void add(PrintJob j) {
        queue.add(j);
        notifyAll();
    }

    public synchronized PrintJob remove() throws InterruptedException {
        while (queue.size() == 0)
            wait();
        return queue.remove();
    }
}
