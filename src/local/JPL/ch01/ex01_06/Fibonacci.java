/*
 * Copyright(C) 2013 Yutaka Kato
 */
package local.JPL.ch01.ex01_06;

/**
 * フィボナッチ数列を表示します。
 */
public class Fibonacci {

	static final String TITLE = "Fibonatti";
	
	public static void main(String[] args) {
		int lo = 1;
		int hi = 1;
		System.out.println(TITLE);
		System.out.println(lo);
		while (hi < 50) {
			System.out.println(hi);
			hi = lo + hi; // 新しい hi
			lo = hi - lo; // 新しい lo は (合計 - 古い lo) すなわち、古い hi
		}
	}

}
