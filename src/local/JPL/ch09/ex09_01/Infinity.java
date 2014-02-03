/*
 * Copyright(C) 2014 Yutaka Kato
 */
package local.JPL.ch09.ex09_01;

import static java.lang.Double.NEGATIVE_INFINITY;
import static java.lang.Double.POSITIVE_INFINITY;

/** Test of calculate infinities */
public class Infinity {

    public static void main(String[] args) {
        // Positive & Positive
        System.out.println(POSITIVE_INFINITY + POSITIVE_INFINITY); // Infinity
        System.out.println(POSITIVE_INFINITY - POSITIVE_INFINITY); // NaN
        System.out.println(POSITIVE_INFINITY * POSITIVE_INFINITY); // Infinity
        System.out.println(POSITIVE_INFINITY / POSITIVE_INFINITY); // NaN
        // Positive & Negative
        System.out.println(POSITIVE_INFINITY + NEGATIVE_INFINITY); // NaN
        System.out.println(POSITIVE_INFINITY - NEGATIVE_INFINITY); // Infinity
        System.out.println(POSITIVE_INFINITY * NEGATIVE_INFINITY); // -Infinity
        System.out.println(POSITIVE_INFINITY / NEGATIVE_INFINITY); // NaN
        // Negative & Negative
        System.out.println(NEGATIVE_INFINITY + NEGATIVE_INFINITY); // -Infinity
        System.out.println(NEGATIVE_INFINITY - NEGATIVE_INFINITY); // NaN
        System.out.println(NEGATIVE_INFINITY * NEGATIVE_INFINITY); // Infinity
        System.out.println(NEGATIVE_INFINITY / NEGATIVE_INFINITY); // NaN
    }
}
