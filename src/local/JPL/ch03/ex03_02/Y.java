/*
 * Copyright(C) 2013 Yutaka Kato
 */
package local.JPL.ch03.ex03_02;

public class Y extends X {
	protected int yMask = 0xff00;
	
	{
		System.out.printf(Y.FORMAT, Y.step++, "Y's field was initialized", xMask, yMask, fullMask);
	}
	
	public Y() {
		super();
		fullMask |= yMask;
		System.out.printf(Y.FORMAT, Y.step++, "Y's constructor was executed", xMask, yMask, fullMask);
	}
	
	public static int step = 0;
	public static final String FORMAT = "%d %-30s %#06x %#06x %#06x%n";
	
	public static void main(String[] args) {
		System.out.printf(Y.FORMAT, Y.step++, "Set default value", 0, 0, 0);
		System.out.printf(Y.FORMAT, Y.step++, "Y's constructor was called", 0, 0, 0);
		System.out.printf(Y.FORMAT, Y.step++, "X's constructor was called", 0, 0, 0);
		new Y();
	}
}
