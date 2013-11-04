/*
 * Copyright(C) 2013 Yutaka Kato
 */
package local.JPL.ch01.ex01_14;

import static org.junit.Assert.fail;

import org.junit.Test;

public class WalkmanTest3 {
	
	/** communicate() の正常系をテストします。 */
	@Test
	public void testCommunicate_normalCase() {
		Tape testTape = new Tape();
		Walkman3 walkman = new Walkman3();
		walkman.setTape(testTape);
		walkman.connect(new String("Earphone 1"));
		walkman.connect2(new String("Earphone 2"));
		walkman.communicate();
	}
	
	/** communicate() の例外系をテストします。 */
	@Test
	public void testCommunicate_exceptionCase() {
		Tape testTape = new Tape();
		Walkman3 walkman = new Walkman3();
		walkman.setTape(testTape);
		try {
			walkman.communicate();
			fail();
		} catch (RuntimeException e) {
			// Test OK
		}
		walkman.connect(new String("Earphone 1"));
		try {
			walkman.communicate();
			fail();
		} catch (RuntimeException e) {
			// Test OK
		}
		walkman.disconnect();
		walkman.connect2(new String("Earphone 2"));
		try {
			walkman.communicate();
			fail();
		} catch (RuntimeException e) {
			// Test OK
		}
	}
}
