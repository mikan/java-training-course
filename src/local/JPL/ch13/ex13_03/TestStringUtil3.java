/*
 * Copyright(C) 2014 Yutaka Kato
 */
package local.JPL.ch13.ex13_03;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class TestStringUtil3 {
    
    @Test
    public void testDelimitedString() {
        assertEquals("《Bonjour!》", StringUtil3.delimitedString("Il a dit 《Bonjour!》", '《', '》'));
    }
    
    @Test
    public void testDelimitedStrings() {
        String[] expectedResult = new String[] {"《Bonjour!》"};
        String[] result = StringUtil3.delimitedStrings("Il a dit 《Bonjour!》", '《', '》');
        assertEquals(expectedResult.length, result.length);
        assertEquals(expectedResult[0], result[0]);
        String[] expectedResult2 = new String[] {"《Bonjour!》", "《Konnichiwa!》"};
        String[] result2 = StringUtil3.delimitedStrings("Il a dit 《Bonjour!》 《Konnichiwa!》 ", '《', '》');
        assertEquals(expectedResult2.length, result2.length);
        assertEquals(expectedResult2[0], result2[0]);
        assertEquals(expectedResult2[1], result2[1]);
    }
}
