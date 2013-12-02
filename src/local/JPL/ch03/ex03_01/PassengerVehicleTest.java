/*
 * Copyright(C) 2013 Yutaka Kato
 */
package local.JPL.ch03.ex03_01;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * PassengerVenhicle をテストします。
 */
public class PassengerVehicleTest {

	/** add をテストします。 */
	@Test
	public void testAdd_all() {
		int max = 10;
		PassengerVehicle v1 = new PassengerVehicle("Vehicle1", max);
		for (int i = 0; i < max; i++) {
			assertTrue(v1.add());
		}
		assertFalse(v1.add());
		assertFalse(v1.add());
	}
	
	/** Remove をテストします。 */
	@Test
	public void testRemove_all() {
		PassengerVehicle v1 = new PassengerVehicle("Vehicle1");
		assertFalse(v1.remove());
		int max = 10;
		PassengerVehicle v2 = new PassengerVehicle("Vehicle2", max);
		for (int i = 0; i < max; i++) {
			v2.add();
		}
		for (int i = 0; i < max; i++) {
			assertTrue(v2.remove());
		}
		assertFalse(v2.remove());
	}
}
