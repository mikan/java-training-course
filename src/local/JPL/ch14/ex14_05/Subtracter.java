/*
 * Copyright(C) 2014 Yutaka Kato
 */
package local.JPL.ch14.ex14_05;

public class Subtracter implements Runnable {
    
    private static int current = 30000;
    private static Object lock = new Object();

    public static void main(String[] args) {
        new Thread(new Subtracter()).start();
        new Thread(new Subtracter()).start();
        new Thread(new Subtracter()).start();
    }
    
    @Override
    public void run() {
        for (int i = 0; i < 10000; i++) {
            subAndPrint(1);
        }
    }

    public void subAndPrint(int num) {
        synchronized (lock) {
            current -= num;
            System.out.println("current num.: " + current);            
        }
    }
}
