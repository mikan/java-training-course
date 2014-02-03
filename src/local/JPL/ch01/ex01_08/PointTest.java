/*
 * Copyright(C) 2013 Yutaka Kato
 */
package local.JPL.ch01.ex01_08;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

/** Point をテストします。 */
public class PointTest {
	
	private static final double Δ = 0.000000001;
	
	/** move(x,y) をテストします。 */
	@Test
	public void testMove_xyInput() {
		Point point = new Point();
		double x = 1.0;
		double y = -1.0;
		point.move(x, y);
		assertEquals(point.x, x, Δ);	// イコールでテスト成功
		assertEquals(point.y, y, Δ);	// (delta 指定しない double の assertEquals は deprecated)
	}
	
	/** move(Point) をテストします。 */
	@Test
	public void testMove_pointInput() {
		Point point = new Point();
		double x = 1.0;
		double y = -1.0;
		point.x = x;
		point.y = y;
		point.move(point);
		assertEquals(point.x, x, Δ);
		assertEquals(point.y, y, Δ);
	}
	
	/** distance(Point) をテストします。 */
	@Test
	public void testDistance_normalCase() {
		Point p1 = new Point();
		p1.x = 100.0;
		p1.y = 200.0;
		Point p2 = new Point();
		p2.x = -100.0;
		p2.y = -200.0;
		assertEquals(p1.distance(p2), p2.distance(p1), Δ);
	}
	
	/** clear() をテストします。 */
	@Test
	public void testClear_normalCase() {
		Point p1 = new Point();
		p1.x = 100.0;
		p1.y = 200.0;
		p1.clear();
		assertEquals(p1.x, 0, Δ);
		assertEquals(p1.y, 0, Δ);
	}
}
