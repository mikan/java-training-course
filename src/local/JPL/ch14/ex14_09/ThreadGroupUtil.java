/*
 * Copyright(C) 2014 Yutaka Kato
 */
package local.JPL.ch14.ex14_09;

public class ThreadGroupUtil {

    public void printEternal(final int interval, final ThreadGroup group) {
        new Thread() {
            @Override
            public void run() {
                while (true) {
                    print(group, "");
                    System.out.println();
                    try {
                        Thread.sleep(interval * 1000);
                    } catch (InterruptedException e) {
                        break;
                    }
                }
            }
        }.start();
    }

    private void print(ThreadGroup group, String prefix) {
        System.out.println(prefix + "[" + group.getName() + "]");
        Thread[] threads = new Thread[group.activeCount()];
        int numThreads = group.enumerate(threads, false);
        for (int i = 0; i < numThreads; i++) {
            System.out.println(prefix + "\t(" + threads[i].getName() + ")");
        }
        ThreadGroup[] groups = new ThreadGroup[group.activeGroupCount()];
        int numGroups = group.enumerate(groups, false);
        for (int i = 0; i < numGroups; i++) {
            print(groups[i], prefix += "\t");
        }
    }

    public static void main(String[] args) {
        // new ThreadGroupUtil().printEternal(3000, Thread.currentThread()
        // .getThreadGroup());
        ThreadGroup group = new ThreadGroup("parent");
        Thread thread1 = new MyThread(group, "thread1", 3);
        Thread thread2 = new MyThread(group, "thread2", 4);
        Thread thread3 = new MyThread(group, "thread3", 5);
        thread1.start();
        thread2.start();
        thread3.start();
        ThreadGroup child = new ThreadGroup(group, "child");
        Thread thread4 = new MyThread(child, "thread4", 6);
        thread4.start();
        new ThreadGroupUtil().printEternal(1, group);
    }
    
    private static class MyThread extends Thread {
        private final int lifetime;
        public MyThread(ThreadGroup group, String name, int lifetime) {
            super(group, name);
            this.lifetime = lifetime;
        }
        
        @Override
        public void run() {
            try {
                Thread.sleep(lifetime * 1000);
            } catch (InterruptedException e) {
                return;
            }
        }
    }
}
