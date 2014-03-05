/*
 * Copyright(C) 2014 Yutaka Kato
 */
package local.Interpret;

public class Interpret {

	@SuppressWarnings("unused")
	private static int staticFieldUpdateTest = 999;

	@SuppressWarnings("unused")
	private final int finalFieldUpdateTest = 888;

	public static void main(String[] args) {
		new InterpretWindow();
	}
}
