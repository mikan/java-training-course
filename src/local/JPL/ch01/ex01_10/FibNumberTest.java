/*
 * Copyright(C) 2013 Yutaka Kato
 */
package local.JPL.ch01.ex01_10;

import static org.junit.Assert.*;

import org.junit.Test;

/** FibNumber をテストします。 */
public class FibNumberTest {

	/** get() をテストします。 */
	@Test
	public void testGet_normalCase() {
		int input = 8;
		FibNumber fib = new FibNumber(input, true);
		assertEquals(fib.get(), input);
	}
	
	/** isEven() をテストします。 */
	@Test
	public void testIsEven_normalCase() {
		int input = 8;
		FibNumber fib = new FibNumber(input, true);
		assertTrue(fib.isEven());
	}
}
