/*
 * Copyright(C) 2014 Yutaka Kato
 */
package local.JPL.ch14.ex14_06;

/**
 * Run two message thread and timer thread.
 */
public class Launcher {

    /**
     * The main method.
     * 
     * @param args not in use
     */
    public static void main(String[] args) {
        MessageThread messageThread1 = new MessageThread(15, "every 15 sec.");
        messageThread1.start();
        MessageThread messageThread2 = new MessageThread(7, "every 7 sec.");
        messageThread2.start();
        TimerThread timer = new TimerThread(messageThread1, messageThread2);
        timer.start();
    }
}
