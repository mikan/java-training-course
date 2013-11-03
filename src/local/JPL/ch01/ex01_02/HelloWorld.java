/*
 * Copyright(C) 2013 Yutaka Kato
 */
package local.JPL.ch01.ex01_02;

import org.junit.Test;

/**
 * HelloWorld をいじってエラーを発生させます。
 */
public class HelloWorld {
	public static void main(String[] args) {

		// "Hello World!"
		System.out.println("Hello World!");

		// java.lang.Error
		// System.out.println("Hello World!);

		// "Hello World!null" (no error)
		System.out.println("Hello World!" + null);

		// java.lang.NumberFormatException
		System.out.println(Integer.parseInt("Hello World!", 10));

		// java.lang.RuntimeException
		System.out.println(new RuntimeException("Hello World!"));

	}

	/**
	 * main関数で例外が発生しないかテストします。
	 */
	@Test
	public void testMain_exceptions() {
		main(null);
	}
}
