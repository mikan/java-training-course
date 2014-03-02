/*
 * Copyright(C) 2014 Yutaka Kato
 */
package local.JPL.ch14.ex14_04;

public class StaticAdder implements Runnable {
    
    private static int current = 0;

    public static void main(String[] args) {
        new Thread(new StaticAdder()).start();
        new Thread(new StaticAdder()).start();
        new Thread(new StaticAdder()).start();
    }
    
    @Override
    public void run() {
        for (int i = 0; i < 10000; i++) {
            addAndPrint(1);
        }
    }

    public static void addAndPrint(int num) {
        current += num;
        System.out.println("current num.: " + current);
    }
}
