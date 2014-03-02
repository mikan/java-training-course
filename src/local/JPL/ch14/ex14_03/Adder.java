/*
 * Copyright(C) 2014 Yutaka Kato
 */
package local.JPL.ch14.ex14_03;

public class Adder implements Runnable {
    
    private int current = 0;

    public static void main(String[] args) {
        new Thread(new Adder()).start();
        new Thread(new Adder()).start();
        new Thread(new Adder()).start();
    }
    
    @Override
    public void run() {
        for (int i = 0; i < 10000; i++) {
            addAndPrint(1);
        }
    }

    public void addAndPrint(int num) {
        current += num;
        System.out.println("current num.: " + current);
    }
}
