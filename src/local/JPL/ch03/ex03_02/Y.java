/*
 * Copyright(C) 2013 Yutaka Kato
 */
package local.JPL.ch03.ex03_02;

public class Y extends X {

    protected int yMask = 0xff00;

    {
        print("Y's field was initialized");
    }

    public Y() {
        super();
        fullMask |= yMask;
        print("Y's constructor was executed");
    }

    public static int step = 0;
    public static final String FORMAT = "%d %-30s %#06x %#06x %#06x%n";

    public static void main(String[] args) {
        new Y();
    }

    @Override
    public void print(String msg) {
        System.out.printf(Y.FORMAT, Y.step++, msg, xMask, yMask, fullMask);
    }
}
