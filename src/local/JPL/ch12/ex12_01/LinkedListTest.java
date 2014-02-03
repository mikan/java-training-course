/*
 * Copyright(C) 2013 Yutaka Kato
 */
package local.JPL.ch12.ex12_01;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Test;

/** LinkedList をテストします。 */
public class LinkedListTest {

    /** LinkedList(Object, LinkedList) の正常系と getBody(), getNext() をテストします。 */
    @Test
    public void testConstructor_normalCase() {
        Object o1 = new Object();
        Object o2 = new Object();
        LinkedList<Object> next = new LinkedList<>(o2, null);
        LinkedList<Object> list = new LinkedList<>(o1, next);
        assertEquals(list.getBody(), o1);
        try {
            assertEquals(list.getNext(), next);            
        } catch (ObjectNotFoundException e) {
            fail();
        }
        assertEquals(next.getBody(), o2);
        try {
            assertNull(next.getNext());            
        } catch (ObjectNotFoundException e) {
            fail();
        }
        try {
            assertEquals(list.getNext().getBody(), o2);            
        } catch (ObjectNotFoundException e) {
            fail();
        }
        try {
            list.getNext().getNext();
            fail();
        } catch (ObjectNotFoundException e) {
        }
    }

    /** LinkedList(Object, LinkedList) の例外系をテストします。 */
    @Test
    public void testConstructor_exceptionCase() {
        try {
            new LinkedList<Object>(null, null);
            fail();
        } catch (NullPointerException e) {
            // Test OK
        }
    }

    /** size() をテストします。 */
    @Test
    public void testSize_all() {
        LinkedList<Object> root = new LinkedList<>(new Object(), null);
        assertTrue(root.size() == 1);
        root.setNext(new LinkedList<>(new Object(), null));
        assertTrue(root.size() == 2);
        try {
            root.getNext().setNext(new LinkedList<>(new Object(), null));
        } catch (ObjectNotFoundException e) {
            fail();
        }
        assertTrue(root.size() == 3);
        try {
            root.getNext().getNext()
                    .setNext(new LinkedList<>(new Object(), null));
        } catch (ObjectNotFoundException e) {
            fail();
        }
        assertTrue(root.size() == 4);

    }
}
