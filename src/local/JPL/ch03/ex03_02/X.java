/*
 * Copyright(C) 2013 Yutaka Kato
 */
package local.JPL.ch03.ex03_02;

public abstract class X {

    {
        print("Field default value");
    }

    protected int xMask = 0x00ff;
    protected int fullMask;

    {
        print("X's field was initialized");
    }

    public X() {
        fullMask = xMask;
        print("X's constructor was executed");
    }

    public int mask(int orig) {
        return (orig & fullMask);
    }

    public abstract void print(String msg);
}
