/*
 * Copyright(C) 2013 Yutaka Kato
 */
package local.JPL.ch02.ex02_01;

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
}
