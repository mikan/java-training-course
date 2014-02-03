/*
 * Copyright(C) 2013 Yutaka Kato
 */
package local.JPL.ch04.ex04_03;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Test;

/** LinkedList をテストします。 */
public class LinkedListTest {

    /** LinkedList(Object) の正常系と getBody(), getNext() をテストします。 */
    @Test
    public void testConstructor_normalCase() {
        Object o1 = new Object();
        LinkedList<Object> list = new LinkedListImpl(o1);
        assertEquals(list.getBody(), o1);
        assertEquals(list.getNext(), null);
    }

    /** LinkedList(Object) の例外系をテストします。 */
    @Test
    public void testConstructor_exceptionCase() {
        try {
            new LinkedListImpl(null);
            fail();
        } catch (NullPointerException e) {
            // Test OK
        }
    }

    /** LinkedList(Object, LinkedList) の正常系と getBody(), getNext() をテストします。 */
    @Test
    public void testConstructor2_normalCase() {
        Object o1 = new Object();
        Object o2 = new Object();
        LinkedListImpl next = new LinkedListImpl(o2, null);
        LinkedListImpl list = new LinkedListImpl(o1, next);
        assertEquals(list.getBody(), o1);
        assertEquals(list.getNext(), next);
        assertEquals(next.getBody(), o2);
        assertNull(next.getNext());
        assertEquals(list.getNext().getBody(), o2);
        assertNull(list.getNext().getNext());
    }

    /** LinkedList(Object, LinkedList) の例外系をテストします。 */
    @Test
    public void testConstructor2_exceptionCase() {
        try {
            new LinkedListImpl(null, null);
            fail();
        } catch (NullPointerException e) {
            // Test OK
        }
    }

    /** size() をテストします。 */
    @Test
    public void testSize_all() {
        LinkedListImpl root = new LinkedListImpl(new Object());
        assertTrue(root.size() == 1);
        root.setNext(new LinkedListImpl(new Object()));
        assertTrue(root.size() == 2);
        root.getNext().setNext(new LinkedListImpl(new Object()));
        assertTrue(root.size() == 3);
        root.getNext().getNext().setNext(new LinkedListImpl(new Object()));
        assertTrue(root.size() == 4);
    }

    /** clone() をテストします。 */
    @Test
    public void testClone_all() {
        LinkedListImpl list = new LinkedListImpl("Test string 1");
        LinkedListImpl list2 = null;
        try {
            list2 = list.clone();
        } catch (CloneNotSupportedException e) {
            fail();
        }
        assertEquals(list.getBody(), list2.getBody());
    }
}
