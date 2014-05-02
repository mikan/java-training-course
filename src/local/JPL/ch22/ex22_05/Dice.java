/*
 * Copyright(C) 2014 Yutaka Kato
 */
package local.JPL.ch22.ex22_05;

import java.util.Random;

public class Dice {
    
    private static final int SIDE = 6;
    
    public int nextValueWithUtilRandom() {
        return new Random().nextInt(SIDE) + 1;
    }
    public int nextValueWithMathRandom() {
        return (int) (Math.random() * 100) % SIDE + 1;
    }
}
