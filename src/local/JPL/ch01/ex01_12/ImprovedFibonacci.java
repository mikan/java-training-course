/*
 * Copyright(C) 2013 Yutaka Kato
 */
package local.JPL.ch01.ex01_12;

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
		String[] output = new String[MAX_INDEX];
		output[0] =  "1: " + lo;
		for (int i = 2; i <= MAX_INDEX; i++) {
			if (hi % 2 == 0) {
				mark = " *";
			} else {
				mark = "";
			}
			output[i - 1] = i + ": " + hi + mark;
			hi = lo + hi;
			lo = hi - lo;
		}
		for (String s : output) {
			System.out.println(s);
		}
	}
}
