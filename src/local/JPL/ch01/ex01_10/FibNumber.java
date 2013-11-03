/*
 * Copyright(C) 2013 Yutaka Kato
 */
package local.JPL.ch01.ex01_10;

/**
 * フィボナッチ数を示します。
 */
public class FibNumber {

	private int fib;
	private boolean even;
	
	public FibNumber(int fib, boolean even) {
		this.fib = fib;
		this.even = even;
	}
	
	@Override
	public String toString() {
		return even ? Integer.toString(fib) + " *" : Integer.toString(fib);
	}
}
