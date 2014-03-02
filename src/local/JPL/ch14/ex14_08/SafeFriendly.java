/*
 * Copyright(C) 2014 Yutaka Kato
 */
package local.JPL.ch14.ex14_08;

public class SafeFriendly {
    private SafeFriendly partner;
    private String name;
    private static Object lock = new Object();

    public SafeFriendly(String name) {
        this.name = name;
    }

    public synchronized void hug() {
        synchronized (lock) {
            System.out.println(Thread.currentThread().getName() + " in " + name
                    + ".hug() trying to invoke " + partner.name + ".hugBack()");
            partner.hugBack();
        }
    }

    private synchronized void hugBack() {
        synchronized (lock) {
            System.out.println(Thread.currentThread().getName() + " in " + name
                    + ".hugBack()");
        }
    }

    public void becomeFriend(SafeFriendly partner) {
        this.partner = partner;
    }

    public static void main(String[] args) {
        final SafeFriendly jareth = new SafeFriendly("jareth");
        final SafeFriendly cory = new SafeFriendly("cory");

        jareth.becomeFriend(cory);
        cory.becomeFriend(jareth);

        new Thread(new Runnable() {
            public void run() {
                jareth.hug();
            }
        }, "Thread1").start();

        new Thread(new Runnable() {
            public void run() {
                cory.hug();
            }
        }, "Thread2").start();
    }
}