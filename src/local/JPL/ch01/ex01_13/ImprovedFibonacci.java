/*
 * Copyright(C) 2013 Yutaka Kato
 */
package local.JPL.ch01.ex01_13;

/**
 * フィボナッチ数列を表示します。
 */
public class ImprovedFibonacci {

	static final int MAX_INDEX = 9;

	/**
	 * 偶数要素に '*' を付けて、フィボナッチ数列の最初の方の要素を表示する。
	 * 
	 * @param args コマンドライン引数
	 */
	public static void main(String[] args) {
		int lo = 1;
		int hi = 1;
		String mark;
		System.out.printf(" 1: %2d%n", lo);
		for (int i = 2; i <= MAX_INDEX; i++) {
			if (hi % 2 == 0) {
				mark = " *";
			} else {
				mark = "";
			}
			System.out.printf("%2d: %2d%s%n", i, hi, mark);
			hi = lo + hi;
			lo = hi - lo;
		}
	}
}
