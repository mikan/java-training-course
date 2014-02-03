/*
 * Copyright(C) 2014 Yutaka Kato
 */
package local.JPL.ch09.ex09_02;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class TestMyBitCount {
    
    @Test
    public void testBitCount_all() {
        //for (int i = Integer.MIN_VALUE; i <= Integer.MAX_VALUE; i++)
        for (int i = -10000; i < 100000; i++)
            assertEquals(Integer.bitCount(i), MyBitCount.bitCount(i));
    }
}
