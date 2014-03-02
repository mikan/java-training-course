/*
 * Copyright(C) 2014 Yutaka Kato
 */
package local.JPL.ch14.ex14_01;

/**
 * Print name of running thread(s)
 */
public class ThreadName {

    public static void main(String[] args) {
        System.out.println("id=" + Thread.currentThread().getId() + " name="
                + Thread.currentThread().getName());
        Thread t1 = new Thread();
        Thread t2 = new Thread();
        t1.start();
        t2.start();
        System.out.println("id=" + t1.getId() + " name=" + t1.getName());
        System.out.println("id=" + t2.getId() + " name=" + t2.getName());
    }

}
