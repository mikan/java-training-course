/*
 * Copyright(C) 2013 Yutaka Kato
 */
package local.JPL.ch01.ex01_08;

/**
 * 2次元の座標を示します。
 */
public class Point {
	public double x, y;
	
	/**
	 * x 座標と y 座標を原点で初期化します。
	 */
	public void clear() {
		x = 0.0;
		y = 0.0;
	}
	
	/**
	 * 引数で指定された座標との距離を求めます。
	 * 
	 * @param that 比較対象の座標
	 * @return 距離
	 */
	public double distance(Point that) {
		double xdiff = x - that.x;
		double ydiff = y - that.y;
		return Math.sqrt(xdiff * xdiff + ydiff * ydiff);
	}
	
	/**
	 * 引数で指定した X, Y 座標に移動します。
	 * 
	 * @param x X座標
	 * @param y Y座標
	 */
	public void move(double x, double y) {
		this.x = x;
		this.y = y;
	}
	
	/**
	 * 引数で指定した座標に移動します。
	 * 
	 * @param point 座標
	 */
	public void move(Point point) {
		x = point.x;
		y = point.y;
	}
}
