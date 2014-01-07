/*
 * Copyright(C) 2014 Yutaka Kato
 */
package local.JPL.ch07.ex07_02;

/** Test of types and literals */
@SuppressWarnings("unused")
public class TypesAndLiterals {

    private static char char1, char2; // 16
    private static byte byte1, byte2; // 8
    private static short short1, short2; // 16
    private static int int1, int2; // 32
    private static long long1, long2; // 64
    private static float float1, float2; // 32
    private static double double1, double2; // 64

    {
        char1 = Character.MIN_VALUE;
        char2 = Character.MAX_VALUE;
        byte1 = Byte.MIN_VALUE;
        byte2 = Byte.MAX_VALUE;
        short1 = Short.MIN_VALUE;
        short2 = Short.MAX_VALUE;
        int1 = Integer.MIN_VALUE;
        int2 = Integer.MAX_VALUE;
        long1 = Long.MIN_VALUE;
        long2 = Long.MAX_VALUE;
        float1 = Float.MIN_VALUE;
        float2 = Float.MAX_VALUE;
        double1 = Double.MIN_VALUE;
        double2 = Double.MAX_VALUE;
    }

    public static void main(String[] args) {
        char1 = 'a';
        char1 = '\000';
        char1 = Character.MAX_VALUE;
        short1 = byte1;
        // int1 = 3.5f;
        int1 = short1;
        double1 = float1;
        double1 = long1;
        double1 = byte1;
        double1 = char1;
        System.out.println("エラーなし");
    }
}
