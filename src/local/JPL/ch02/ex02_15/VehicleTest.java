/*
 * Copyright(C) 2013 Yutaka Kato
 */
package local.JPL.ch02.ex02_15;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Test;

/** くるまのテストだよ */
public class VehicleTest {
	
	private static final double DELTA = 0.00000000000000001;

	/** Vehicle() をテストします。 */
	@Test
	public void TestConstructor_defaultCase() {
		Vehicle vehicle = new Vehicle();
		assertNotNull(vehicle.getName());	// name は null にしないように実装
	}
	
	/** Vehicle(String) の正常系をテストします。 */
	@Test
	public void testConstructor_normalCase() {
		String name = "Audi R8";
		Vehicle vehicle = new Vehicle(name);
		assertTrue(vehicle.getName().equals(name));
	}
	
	/** Vehicle(String) の例外系をテストします。 */
	@Test
	public void testConstructor_exceptionCase1() {
		try {
			new Vehicle(null);
			fail();
		} catch (NullPointerException e) {
			// Test OK
		}
	}
	
	/** Vehicle(String) の例外系をテストします。 */
	@Test
	public void testConstructor_exceptionCase2() {
		try {
			new Vehicle("");
			fail();
		} catch (IllegalArgumentException e) {
			// Test OK
		}
	}
	
	/** getId() をテストします。 */
	@Test
	public void testGetId_normalCase1() {
		Vehicle v1 = new Vehicle("test car 1st");
		assertTrue(v1.getId() > 0);	// ID は 1 以上
	}
	
	/** getId() をテストします。 */
	@Test
	public void testGetId_normalCase2() {
		Vehicle v1 = new Vehicle("test car 1st");
		Vehicle v2 = new Vehicle("test car 2nd");
		assertFalse(v1.getId() == v2.getId());	// ID は重複しない
	}
	
	/** toString() をテストします。 */
	@Test
	public void testToString_normalCase() {
		String name = "unko";
		Vehicle v1 = new Vehicle(name);
		assertTrue(v1.toString().startsWith("Vehicle "));
		assertTrue(v1.toString().endsWith(name));
	}
	
	/** setDirection() の正常系をテストします。 */
	@Test
	public void testSetDirection_normalCase() {
		Vehicle v1 = new Vehicle("test car");
		v1.setDirection(0);
		v1.setDirection(180);
		v1.setDirection(360);
	}
	
	/** setDirection() の異常系をテストします。 */
	@Test
	public void testSetDirection_exceptionCase() {
		Vehicle v1 = new Vehicle("test car");
		try {
			v1.setDirection(-1);
			fail();
		} catch (IllegalArgumentException e) {
			// Test OK
		}
		try {
			v1.setDirection(361);
			fail();
		} catch (IllegalArgumentException e) {
			// Test OK
		}
	}
	
	/** setName() の正常系をテストします。 */
	@Test
	public void testSetName_normalCase() {
		Vehicle v1 = new Vehicle("test car");
		String newName = "super car";
		v1.setName(newName);
		assertTrue(v1.getName().equals(newName));
	}
	
	/** setName() の例外系をテストします。 */
	@Test
	public void testSetName_exceptionCase() {
		Vehicle v1 = new Vehicle("test car");
		try {
			v1.setName(null);
		} catch (NullPointerException e) {
			// Test OK
		}
		try {
			v1.setName("");
		} catch (IllegalArgumentException e) {
			// Test OK
		}
	}
	
	/** changeSpeed() と stop() をテストします。 */
	@Test
	public void testChangeSpeed_normalCase() {
		Vehicle v1 = new Vehicle("test car");
		v1.changeSpeed(100.0);
		assertEquals(v1.getSpeed(), 100.0, DELTA);
		v1.changeSpeed(-100.0);
		assertEquals(v1.getSpeed(), -100.0, DELTA);
		v1.stop();
		assertEquals(v1.getSpeed(), 0, DELTA);
	}
}
