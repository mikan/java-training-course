/*
 * Copyright(C) 2013 Yutaka Kato
 */
package local.JPL.ch03.ex03_03;

public class X {
	protected int xMask = 0x00ff;
	protected int fullMask;
	
	public X() {
		fullMask = xMask;
	}
	
	public X(int mask) {
		fullMask = mask;
	}
	
	public int mask(int orig) {
		return (orig & fullMask);
	}
}
