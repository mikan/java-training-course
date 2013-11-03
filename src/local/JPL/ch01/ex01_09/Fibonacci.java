/*
 * Copyright(C) 2013 Yutaka Kato
 */
package local.JPL.ch01.ex01_09;

/**
 * フィボナッチ数列を表示します。
 */
public class Fibonacci {

	private static final int MAX_INDEX = 9;
	private static int[] values = new int[MAX_INDEX];
	
	public static void main(String[] args) {
		int lo = 1;
		int hi = 1;
		values[0] = 1;
		
		for (int i = 1; i < MAX_INDEX; i++) {
			values[i] = hi;
			hi = lo + hi; // 新しい hi
			lo = hi - lo; // 新しい lo は (合計 - 古い lo) すなわち、古い hi			
		}
		
		for (int v : values) {
			System.out.println(v);
		}
	}
}
