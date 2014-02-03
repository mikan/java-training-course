/*
 * Copyright(C) 2013 Yutaka Kato
 */
package local.JPL.ch06.ex06_05;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class TrafficSignalTest {

	@Test
	public void testTrafficSiglnal_all() {
		TrafficSignal off = TrafficSignal.OFF;
		assertEquals(off.getColor().getColorCode(), 0x000000);
		TrafficSignal red = TrafficSignal.RED;
		assertEquals(red.getColor().getColorCode(), 0xff0000);
	}
}
