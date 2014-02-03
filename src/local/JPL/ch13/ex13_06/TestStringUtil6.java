/*
 * Copyright(C) 2014 Yutaka Kato
 */
package local.JPL.ch13.ex13_06;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Test;

public class TestStringUtil6 {

    @Test
    public void testInsertComma_normalCase1() {
        assertEquals("", StringUtil6.insertComma(""));
        assertEquals("0", StringUtil6.insertComma("0"));
        assertEquals("10", StringUtil6.insertComma("10"));
        assertEquals("100", StringUtil6.insertComma("100"));
        assertEquals("1,000", StringUtil6.insertComma("1000"));
        assertEquals("10,000", StringUtil6.insertComma("10000"));
        assertEquals("100,000", StringUtil6.insertComma("100000"));
        assertEquals("1,000,000", StringUtil6.insertComma("1000000"));
        assertEquals("10,000,000", StringUtil6.insertComma("10000000"));
        assertEquals("100,000,000", StringUtil6.insertComma("100000000"));
    }
    
    @Test
    public void testInsertComma_normalCase2() {
        assertEquals("", StringUtil6.insertComma("", '/', 1));
        assertEquals("0", StringUtil6.insertComma("0", '/', 1));
        assertEquals("1/0", StringUtil6.insertComma("10", '/', 1));
        assertEquals("1/0/0", StringUtil6.insertComma("100", '/', 1));
        assertEquals("1/0/0/0", StringUtil6.insertComma("1000", '/', 1));
        assertEquals("1/0/0/0/0", StringUtil6.insertComma("10000", '/', 1));
    }
    
    @Test
    public void testInsertComma_exceptionCase1() {
        try {
            StringUtil6.insertComma(null);
            fail();
        } catch (NullPointerException e) {
            // PASS
        }
    }
    
    @Test
    public void testInsertComma_exceptionCase2() {
        try {
            StringUtil6.insertComma("a", ',', 0);
            fail();
        } catch (IllegalArgumentException e) {
            // PASS
        }
        try {
            StringUtil6.insertComma("a", ',', -1);
            fail();
        } catch (IllegalArgumentException e) {
            // PASS
        }
    }
}
