/*
 * Copyright(C) 2014 Yutaka Kato
 */
package local.JPL.ch13.ex13_02;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Test;

public class TestStringUtil2 {
    
    /** countSubstring の正常系をテストします。 */
    @Test
    public void testCountSubstring_normalCase() {
        assertEquals(0, new StringUtil2().countSubstring("abcdefg-hijklmn", "mno"));
        assertEquals(0, new StringUtil2().countSubstring("abcdefg-hijklmn", "z"));
        assertEquals(0, new StringUtil2().countSubstring("abcdefg-hijklmn", "abcdefg-hijklmnz"));
        assertEquals(1, new StringUtil2().countSubstring("abcdefg-hijklmn", "-"));
        assertEquals(1, new StringUtil2().countSubstring("abcdefg-hijklmn", "hijklmn"));
        assertEquals(1, new StringUtil2().countSubstring("abcdefg-hijklmn", "abcdefg-hijklmn"));
        assertEquals(2, new StringUtil2().countSubstring("abcdefg-hijklmnXabcdefg-hijklmn", "abcdefg-hijklmn"));
    }
    
    /** countSubstring の例外系をテストします。 */
    @Test
    public void testCountSubstring_exceptionCase() {
        try {
            new StringUtil2().countSubstring(null, "abc");
            fail();
        } catch (NullPointerException e) {
            // Pass
        }
        try {
            new StringUtil2().countSubstring("abc", null);
            fail();
        } catch (NullPointerException e) {
            // Pass
        }
        try {
            new StringUtil2().countSubstring(null, null);
            fail();
        } catch (NullPointerException e) {
            // Pass
        }
        try {
            new StringUtil2().countSubstring("", "abc");
            fail();
        } catch (IllegalArgumentException e) {
            // Pass
        }
        try {
            new StringUtil2().countSubstring("abc", "");
            fail();
        } catch (IllegalArgumentException e) {
            // Pass
        }
        try {
            new StringUtil2().countSubstring("", "");
            fail();
        } catch (IllegalArgumentException e) {
            // Pass
        }
    }
}
