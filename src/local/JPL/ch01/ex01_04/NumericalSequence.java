/*
 * Copyright(C) 2013 Yutaka Kato
 */
package local.JPL.ch01.ex01_04;

/**
 * 数列を表示します。
 */
public class NumericalSequence {

	public static void main(String[] args) {
		int base = 1;
		while (base < 50) {
			System.out.println(base * base);
			base++;
		}
	}

}
