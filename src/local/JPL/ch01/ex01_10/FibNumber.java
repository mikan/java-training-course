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
	
	/**
	 * フィボナッチ数を作ります。値が本当にフィボナッチ数かは検査されません。
	 * 
	 * @param fib フィボナッチ数
	 * @param even 偶数ならば true、奇数ならば false
	 */
	public FibNumber(int fib, boolean even) {
		this.fib = fib;
		this.even = even;
	}
	
	/**
	 * フィボナッチ数を取得します。
	 * @return フィボナッチ数
	 */
	public int get() {
		return fib;
	}
	
	/**
	 * 偶数/奇数を判定します。
	 * @return 偶数ならば true、奇数ならば false
	 */
	public boolean isEven() {
		return even;
	}
	
	@Override
	public String toString() {
		return even ? Integer.toString(fib) + " *" : Integer.toString(fib);
	}
}
