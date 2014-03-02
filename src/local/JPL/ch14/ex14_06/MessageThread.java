/*
 * Copyright(C) 2014 Yutaka Kato
 */
package local.JPL.ch14.ex14_06;

/**
 * Repeat the message at specified intervals.
 */
public class MessageThread extends Thread {

    private int count = 1;
    private final int interval;
    private final String message;

    public MessageThread(int interval, String message) {
        this.interval = interval;
        this.message = message;
    }

    @Override
    public void run() {
        while (true)
            showMessage();
    }

    public synchronized void showMessage() {
        if (count == interval) {
            count = 1;
            System.out.println(message);
        } else {
            count++;
        }
        try {
            wait();
        } catch (InterruptedException e) {
            System.err.println("MessageThread interrputed.");
        }
        return;
    }

    public synchronized void tick() {
        notify();
    }
}
