/*
 * Copyright(C) 2013 Yutaka Kato
 */
package local.JPL.ch01.ex01_14;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Test;

public class WalkmanTest2 {
	
	/** 正しい規格のテープを正常に受け付けるか試します。 */
	@Test
	public void testSetTape_normalInput() {
		Tape testTape = new Tape();
		Walkman2 walkman = new Walkman2();
		walkman.setTape(testTape);
		Tape accTape = new Tape(Tape.Type.ACC);
		walkman.setTape(accTape);
	}
	
	/** 規格外のテープが通ってしまわないか試します。 */
	@Test
	public void testSetTape_illegalTypes() {
		Tape elcastTape = new Tape(Tape.Type.ELCASET);
		Walkman2 walkman = new Walkman2();
		try {
			walkman.setTape(elcastTape);
			fail("規格外が通ってしまった。");
		} catch (IllegalArgumentException e) {
			// Test success
		}
	}
	
	/** テープの取り出し結果が正しいか試します。 */
	@Test
	public void testSetTape_retrurn() {
		Tape testTape1 = new Tape();
		Tape tsetTape2 = new Tape();
		Tape testTape3 = new Tape();
		Walkman2 walkman = new Walkman2();
		assertNull(walkman.setTape(testTape1));		// null が返れば OK
		assertNotNull(walkman.setTape(tsetTape2));	// tape1 が返ってくる (null でない) はず
		assertTrue(walkman.setTape(testTape3).equals(tsetTape2));	// tape2 が返ってくるはず
	}
	
	/** connect(), disconnect(), isConnected() をテストします。 */
	@Test
	public void testConnectionMethods_normalCase() {
		Walkman2 walkman = new Walkman2();
		assertFalse(walkman.isConnected());
		walkman.connect(new Object());
		assertTrue(walkman.isConnected());
		walkman.disconnect();
		assertFalse(walkman.isConnected());
	}
	
	/** connect(), disconnect(), isConnected() をテストします。 */
	@Test
	public void testConnection2Methods_normalCase() {
		Walkman2 walkman = new Walkman2();
		assertFalse(walkman.isConnected2());
		walkman.connect2(new Object());
		assertTrue(walkman.isConnected2());
		walkman.disconnect2();
		assertFalse(walkman.isConnected2());
	}
}
