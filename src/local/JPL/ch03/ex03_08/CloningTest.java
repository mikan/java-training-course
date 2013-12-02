/*
 * Copyright(C) 2013 Yutaka Kato
 */
package local.JPL.ch03.ex03_08;

public class CloningTest {
	public static void main(String[] args) throws CloneNotSupportedException {
		Vehicle v1 = new Vehicle("test car");
		Vehicle v2 = v1.clone();
		System.out.println(v1);
		System.out.println(v2);
	}
}
