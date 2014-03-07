/*
 * Copyright(C) 2014 Yutaka Kato
 */
package local.JPL.ch09.ex09_04;

/** Check expressions of ex09_04 */
public class ExpressionCheck {

    public static void main(String[] args) {
        System.out.println(3 << 2L - 1); // int 6
        System.out.println((3L << 2) - 1); // double 11
        System.out.println(10 < 12 == 6 > 17); // boolean false
        System.out.println(10 << 12 == 6 >> 17); // boolean false
        System.out.println(13.5e-1 % Float.POSITIVE_INFINITY); // double 1.35
        System.out.println(Float.POSITIVE_INFINITY + Double.NEGATIVE_INFINITY); // double NaN
        System.out.println(Double.POSITIVE_INFINITY - Float.NEGATIVE_INFINITY); // double Infinity
        System.out.println(0.0 / -0.0 == -0.0 / 0.0); // boolean false
        System.out.println(Integer.MAX_VALUE + Integer.MIN_VALUE); // int -1
        System.out.println(Long.MAX_VALUE + 5); // long -9223372036854775804
        System.out.println((short) 5 * (byte) 10); // int 50
        int i = 3;
        System.out.println(i < 15 ? 1.72e3f : 0); // float 1720.0
        System.out.println(i++ + i++ + --i); // int 11
    }
}
