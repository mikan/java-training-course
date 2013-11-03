/*
 * Copyright(C) 2013 Yutaka Kato
 */
package local.JPL.ch01.ex01_03;

import org.junit.Test;

/**
 * フィボナッチ数列を表示します。
 */
public class Fibonacci {

	public static void main(String[] args) {
		int lo = 1;
		int hi = 1;
		System.out.println("Fibonatti");
		System.out.println(lo);
		while (hi < 50) {
			System.out.println(hi);
			hi = lo + hi; // 新しい hi
			lo = hi - lo; // 新しい lo は (合計 - 古い lo) すなわち、古い hi
		}
	}

	/**
	 * main関数で例外が発生しないかテストします。
	 */
	@Test
	public void testMain_exceptions() {
		main(null);
	}
}
