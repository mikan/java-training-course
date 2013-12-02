/*
 * Copyright(C) 2013 Yutaka Kato
 */
package local.JPL.ch03.ex03_02;

public class X {
	protected int xMask = 0x00ff;
	protected int fullMask;
	
	{
		System.out.printf(Y.FORMAT, Y.step++, "X's field was initialized", xMask, 0, fullMask);
	}
	
	public X() {
		fullMask = xMask;
		System.out.printf(Y.FORMAT, Y.step++, "X's constructor was executed", xMask, 0, fullMask);
	}
	
	public int mask(int orig) {
		return (orig & fullMask);
	}
}
