/*
 * Copyright(C) 2013-2014 Yutaka Kato
 */
package local.JPL.ch11.ex11_02;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class TestAttr {

    @Test
    public void testSetValue_normalCase() {
        
        Integer value1 = new Integer(100);
        Integer value2 = new Integer(101);
        Attr<Integer> intAttr = new Attr<>("integer", value1);
        intAttr.setValue(value2);
        assertEquals(value2, intAttr.getValue());
    }
}
