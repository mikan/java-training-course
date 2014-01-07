/*
 * Copyright(C) 2014 Yutaka Kato
 */
package local.JPL.ch09.ex09_02;

/** Count size of bit */
public class MyBitCount {

    public static int bitCount(int i) {
        // Copy & paste from Integer#bitCount()
        i = i - ((i >>> 1) & 0x55555555);
        i = (i & 0x33333333) + ((i >>> 2) & 0x33333333);
        i = (i + (i >>> 4)) & 0x0f0f0f0f;
        i = i + (i >>> 8);
        i = i + (i >>> 16);
        return i & 0x3f;
    }

    public static void main(String[] args) {
        print(0);
        print(8);
        print(9);
        print(16);
        print(17);
    }

    private static void print(int input) {
        System.out.println("Integer.bitCount(" + input + "):     \t"
                + Integer.bitCount(input));
        System.out.println("MyBitCount.bitCount(" + input + "):  \t"
                + bitCount(input));
    }
}

// I found the same idea!
// http://www.mwsoft.jp/programming/java/java_lang_integer_bit_count.html
