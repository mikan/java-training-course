/*
 * Copyright(C) 2014 Yutaka Kato
 */
package local.JPL.ch13.ex13_05;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Test;

public class TestStringUtil5 {

    @Test
    public void testInsertComma_normalCase() {
        assertEquals("", StringUtil5.insertComma(""));
        assertEquals("0", StringUtil5.insertComma("0"));
        assertEquals("10", StringUtil5.insertComma("10"));
        assertEquals("100", StringUtil5.insertComma("100"));
        assertEquals("1,000", StringUtil5.insertComma("1000"));
        assertEquals("10,000", StringUtil5.insertComma("10000"));
        assertEquals("100,000", StringUtil5.insertComma("100000"));
        assertEquals("1,000,000", StringUtil5.insertComma("1000000"));
        assertEquals("10,000,000", StringUtil5.insertComma("10000000"));
        assertEquals("100,000,000", StringUtil5.insertComma("100000000"));
    }
    
    @Test
    public void testInsertComma_exceptionCase() {
        try {
            StringUtil5.insertComma(null);
            fail();
        } catch (NullPointerException e) {
            // PASS
        }
    }
}
