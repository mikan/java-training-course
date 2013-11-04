/*
 * Copyright(C) 2013 Yutaka Kato
 */
package local.JPL.ch02.ex02_02;

import static org.junit.Assert.*;

import org.junit.Test;

/** LinkedList をテストします。 */
public class LinkedListTest {
	
	/** LinkedList(Object, LinkedList) の正常系と getBody(), getNext() をテストします。 */
	@Test
	public void testConstructor_normalCase() {
		Object o1 = new Object();
		Object o2 = new Object();
		LinkedList next = new LinkedList(o2, null);
		LinkedList list = new LinkedList(o1, next);
		assertEquals(list.getBody(), o1);
		assertEquals(list.getNext(), next);
		assertEquals(next.getBody(), o2);
		assertNull(next.getNext());
		assertEquals(list.getNext().getBody(), o2);
		assertNull(list.getNext().getNext());
	}
	
	/** LinkedList(Object, LinkedList) の例外系をテストします。 */
	@Test
	public void testConstructor_exceptionCase() {
		try {
			new LinkedList(null, null);
			fail();
		} catch (NullPointerException e) {
			// Test OK
		}
	}
	
	/** size() をテストします。 */
	@Test
	public void testSize_all() {
		LinkedList root = new LinkedList(new Object(), null);
		assertTrue(root.size() == 1);
		root.setNext(new LinkedList(new Object(), null));
		assertTrue(root.size() == 2);
		root.getNext().setNext(new LinkedList(new Object(), null));
		assertTrue(root.size() == 3);
		root.getNext().getNext().setNext(new LinkedList(new Object(), null));
		assertTrue(root.size() == 4);
	}
}
