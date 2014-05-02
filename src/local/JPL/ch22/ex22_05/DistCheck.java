/*
 * Copyright(C) 2014 Yutaka Kato
 */
package local.JPL.ch22.ex22_05;

public class DistCheck {

    private static final int MAX_TRY = 10000;

    public static void main(String[] args) {
        int count = 0;
        Dice dice1 = new Dice();
        Dice dice2 = new Dice();
        
        // java.util.Random
        for (int i = 0; i < MAX_TRY; i++) {
            int sum = dice1.nextValueWithUtilRandom() + dice2.nextValueWithUtilRandom();
            if (sum == 7)
                count++;
        }
        int percent = (int) ((double) count / MAX_TRY * 100);
        
        System.out.println("java.util.Random: " + count + "/" + MAX_TRY + " (" + percent + "%)");

                // Math.random
                count = 0;
        for (int i = 0; i < MAX_TRY; i++) {
            int sum = dice1.nextValueWithMathRandom() + dice2.nextValueWithMathRandom();
            if (sum == 7)
                count++;
        }
        percent = (int) ((double) count / MAX_TRY * 100);
        System.out.println("Math.random:      " + count + "/" + MAX_TRY + " (" + percent + "%)");
    }
}
