/*
 * Copyright(C) 2014 Yutaka Kato
 */
package local.JPL.ch14.ex14_06;

/**
 * Print the count every second.
 */
public class TimerThread extends Thread {

    private final MessageThread[] messageThreads;

    public TimerThread(MessageThread... messageThreads) {
        this.messageThreads = messageThreads;
    }

    @Override
    public void run() {
        int current = 0;
        while (true) {
            System.out.println("current=" + ++current);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                System.err.println("MessageThread interrputed.");
                break;
            }
            for (MessageThread thread : messageThreads)
                thread.tick();
        }
    }
}
