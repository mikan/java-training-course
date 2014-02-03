/*
 * Copyright(C) 2014 Yutaka Kato
 */
package local.JPL.ch13.ex13_01;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class TestStringUtil {

    @Test
    public void testCountChar_normalCase() {
        assertEquals(0, new StringUtil().countChar("abcdefg-hijklmn", 'z'));
        assertEquals(0, new StringUtil().countChar("abcdefg-hijklmn", 'A'));
        assertEquals(0, new StringUtil().countChar("abcdefg-hijklmn", '\n'));
        assertEquals(0, new StringUtil().countChar("abcdefg-hijklmn", '?'));
        assertEquals(1, new StringUtil().countChar("abcdefg-hijklmn", 'a'));
        assertEquals(1, new StringUtil().countChar("abcdefg-hijklmn", '-'));
        assertEquals(2, new StringUtil().countChar("abcdefg-abcdefg", 'a'));
        assertEquals(10, new StringUtil().countChar("abababababababababab", 'a'));
    }
}
