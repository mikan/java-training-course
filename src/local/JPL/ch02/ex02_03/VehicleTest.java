/*
 * Copyright(C) 2013 Yutaka Kato
 */
package local.JPL.ch02.ex02_03;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Test;

/** くるまのテストだよ */
public class VehicleTest {

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
}
