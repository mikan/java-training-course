/*
 * Copyright(C) 2013 Yutaka Kato
 */
package local.JPL.ch03.ex03_03;

public class Y extends X {
	protected int yMask = 0xff00;
	
	public Y() {
		super(0xff00);	// コンストラクタで渡す
		fullMask |= yMask;
	}
	
	public static void main(String[] args) {
		new Y();
	}
}
