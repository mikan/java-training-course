/*
 * Copyright(C) 2013 Yutaka Kato
 */
package local.JPL.ch02.ex02_12;

import java.util.ArrayList;
import java.util.List;

public class Vehicle {
	
	private double speed;
	private int direction;
	private String name;
	private int id;
	private static int lastId;
	private List<String> alternateNames = new ArrayList<String>();
	
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
		vehicles.get(3).setAlternateName("alt1", "alt2", "alt3", "alt4");
		for (String s : vehicles.get(3).getAlternateNames()) {
			System.out.println("Alternate name: " + s);
		}
		
		LinkedList root = new LinkedList(new Vehicle("mycar"), 
				new LinkedList(new Vehicle("mytruck")),
				new LinkedList(new Vehicle("mysportscar")),
				new LinkedList(new Vehicle()),
				new LinkedList(new Vehicle()));
		LinkedList current = root;
		while (true) {
			System.out.println(current.getBody());
			if (current.hasNext()) {
				current = current.getNext();
			} else {
				break;
			}
		}
	}
	
	public Vehicle() {
		speed = 0.0;
		direction = 0;
		name = "unnamed";
		id = ++lastId;
	}
	
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
		id = ++lastId;
	}
	
	/**
	 * 別名を追加します。
	 * @param altName 別名
	 */
	public void setAlternateName(String ... altName) {	// 可変引数
		for (String s : altName) {
			alternateNames.add(s);
		}
	}
	
	/**
	 * 別名一覧を取得します。
	 * @return 別名一覧
	 */
	public List<String> getAlternateNames() {
		return alternateNames;
	}
	
	/**
	 * 速度を取得します。
	 * @return 速度
	 */
	public double getSpeed() {
		return speed;
	}
	
	/**
	 * 方向を取得します。
	 * @return 方向 (角度)
	 */
	public int getDirection() {
		return direction;
	}
	
	/**
	 * 名前を取得します。
	 * @return 名前
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * ID を取得します。
	 * @return ID
	 */
	public int getId() {
		return id;
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
