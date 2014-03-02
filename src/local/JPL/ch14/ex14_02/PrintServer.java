/*
 * Copyright(C) 2014 Yutaka Kato
 */
package local.JPL.ch14.ex14_02;

public class PrintServer implements Runnable {

    private final PrintQueue requests = new PrintQueue();
    private static final String MAIN_THREAD_TAG = "MAIN_THREAD";

    public PrintServer() {
        new Thread(this, MAIN_THREAD_TAG).start();
    }

    public void print(PrintJob job) {
        requests.add(job);
    }

    @Override
    public void run() {
        if (!Thread.currentThread().getName().equals(MAIN_THREAD_TAG)) {
            System.err.println("ERROR: Illegal thread.");
            return;
        }
        System.out.println("Thread name OK.");
        for (;;) {
            try {
                realPrint(requests.remove());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void realPrint(PrintJob job) {
        job.doPrint();
    }

    public static void main(String[] args) {
        PrintServer server1 = new PrintServer();
        server1.run();
        new Thread(server1);
    }
}
