/*
 * Copyright(C) 2013 Yutaka Kato
 */
package local.JPL.ch02.ex02_06;

import local.JPL.ch02.ex02_02.LinkedList;

public class Vehicle {
	
	private double speed;
	private int direction;
	private String name;
	private int id;
	private static int nextId = 1;
	
	/**
	 * main
	 * 
	 * @param args コマンドライン引数
	 */
	public static void main(String[] args) {
		LinkedList root = new LinkedList(new Vehicle("mycar"), null);
		root.setNext(new LinkedList(new Vehicle("mytruck"), null));
		root.getNext().setNext(new LinkedList(new Vehicle("mysportscar"), null));
		System.out.println("size: " + root.size());
		LinkedList current = root;
		while (true) {
			System.out.println(current.getBody());
			if (!current.hasNext()) {
				break;
			}
			current = current.getNext();
		}
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
		id = nextId;
		nextId++;
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
}
