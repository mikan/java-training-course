/*
 * Copyright(C) 2013 Yutaka Kato
 */
package local.JPL.ch02.ex02_13;

import java.util.ArrayList;
import java.util.List;

public class Vehicle {
	
	private double speed;
	private int direction;
	private String name;
	private int id;
	private static int lastId;
	
	/**
	 * main
	 * 
	 * @param args コマンドライン引数
	 */
	public static void main(String[] args) {
		List<Vehicle> vehicles = new ArrayList<Vehicle>();
		vehicles.add(new Vehicle("mycar"));
		vehicles.add(new Vehicle("mytruck"));
		vehicles.add(new Vehicle("mysportscar"));
		vehicles.add(new Vehicle());
		vehicles.add(new Vehicle());
		for (Vehicle v : vehicles) {
			System.out.println(v);
		}
	}
	
	/**
	 * 無名のくるま。
	 */
	public Vehicle() {
		speed = 0.0;
		direction = 0;
		name = "unnamed";
		id = ++lastId;
	}
	
	/**
	 * 名前があるくるま。
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
		id = ++lastId;
	}
	
	/**
	 * 速度を取得します。
	 * @return 速度
	 */
	public double getSpeed() {
		return speed;
	}
	
	/**
	 * 速度を変更します。
	 * @param speed 速度
	 */
	public void setSpeed(double speed) {
		this.speed = speed;
	}
	
	/**
	 * 方向を取得します。
	 * @return 方向 (角度)
	 */
	public int getDirection() {
		return direction;
	}
	
	/**
	 * 方向を変更します。
	 * @param direction 方向
	 * @throws IllegalArgumentException 方向が 0 - 360 の間にない場合
	 */
	public void setDirection(int direction) {
		if (direction < 0 || direction > 360) {
			throw new IllegalArgumentException("Out of direction.");
		}
		this.direction = direction;
	}
	
	/**
	 * 名前を取得します。
	 * @return 名前
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * 名前を変更します。
	 * @param name 名前
	 * @throws NullPointerException 名前が null の場合
	 * @throws IllegalArgumentException 名前が空の場合
	 */
	public void setName(String name) {
		if (name == null) {
			throw new NullPointerException("名前がないよ");
		}
		if (name.isEmpty()) {
			throw new IllegalArgumentException("名前がないよ");
		}
		this.name = name;
	}
	
	/**
	 * ID を取得します。
	 * @return ID
	 */
	public int getId() {
		return id;
	}
	
	// ID を変更できるようにすべきではない
	@SuppressWarnings("unused")
	private void setId(int id) {
		this.id = id;
	}
	
	@Override
	public String toString() {
		return "Vehicle " + getId() + ": Speed=" + getSpeed() + ", Direction=" + getDirection() +
				", name=" + getName();
	}
	
	/**
	 * 今まで使われた識別番号の最大値を返します。一度も使われていない場合、0 を返します。
	 * 
	 * @return 識別番号
	 */
	public static int getLastId() {
		return lastId;
	}

}
