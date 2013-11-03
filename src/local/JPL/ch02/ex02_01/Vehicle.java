/*
 * Copyright(C) 2013 Yutaka Kato
 */
package local.JPL.ch02.ex02_01;

public class Vehicle {
	
	private double speed;
	private int direction;
	private String name;
	
	/**
	 * くるま。
	 * 
	 * @param name 車の名前
	 * @throws NullPointerException 名前が欲しい
	 * @throws IllegalArgumentException ちゃんと名前が欲しい
	 */
	public Vehicle(String name) {
		if (name == null) throw new NullPointerException("Name is null.");
		if (name.isEmpty()) throw new IllegalArgumentException("Name is empty.");
		speed = 0.0;
		direction = 0;
		this.name = name;
	}
	
	public double getSpeed() {
		return speed;
	}
	
	public int getDirection() {
		return direction;
	}
	
	public String getName() {
		return name;
	}
}
