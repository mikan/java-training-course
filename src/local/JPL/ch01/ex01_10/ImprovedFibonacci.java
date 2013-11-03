/*
 * Copyright(C) 2013 Yutaka Kato
 */
package local.JPL.ch01.ex01_10;

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
		FibNumber[] fibArray = new FibNumber[MAX_INDEX];
		
		fibArray[0] = new FibNumber(lo, lo % 2 == 0);
		for (int i = 2; i <= MAX_INDEX; i++) {
			fibArray[i - 1] = new FibNumber(hi, hi % 2 == 0);
			hi = lo + hi;
			lo = hi - lo;
		}
		
		for (FibNumber e : fibArray) {
			System.out.println(e);
		}
	}
}
