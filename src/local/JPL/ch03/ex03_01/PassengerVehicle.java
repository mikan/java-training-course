/*
 * Copyright(C) 2013 Yutaka Kato
 */
package local.JPL.ch03.ex03_01;

import java.util.ArrayList;
import java.util.List;

/**
 * たくさん乗れるくるま
 */
public class PassengerVehicle extends Vehicle {

	private final int maxPassengers;
	private static final int DEFAULT_MAX_PASSENGERS = 5;
	private int passengers;

	public static void main(String[] args) {
		List<PassengerVehicle> vehicles = new ArrayList<PassengerVehicle>();
		vehicles.add(new PassengerVehicle("くるま1", 2));
		vehicles.add(new PassengerVehicle("くるま2", 2));
		vehicles.add(new PassengerVehicle("くるま3", 4));
		vehicles.add(new PassengerVehicle("くるま4", 4));
		vehicles.add(new PassengerVehicle("くるま5", 5));
		vehicles.get(0).add();
		vehicles.get(0).add();
		vehicles.get(0).add();
		vehicles.get(1).add();
		for (PassengerVehicle v : vehicles) {
			System.out.println(v);
		}
	}

	/**
	 * 無名の5人乗りのくるまを作ります。
	 */
	public PassengerVehicle() {
		super();
		maxPassengers = DEFAULT_MAX_PASSENGERS;
	}

	/**
	 * なまえがある5人乗りのくるまを作ります。
	 * @param name なまえ
	 */
	public PassengerVehicle(String name) {
		super(name);
		maxPassengers = DEFAULT_MAX_PASSENGERS;
	}

	/**
	 * なまえと人数を指定してくるまを作ります。
	 * @param name なまえ
	 * @param maxPassengers 人数
	 * @throws IllegalArgumentException 人数が 1 未満の場合
	 */
	public PassengerVehicle(String name, int maxPassengers) {
		super(name);
		if (maxPassengers < 1) {
			throw new IllegalArgumentException();
		}
		this.maxPassengers = maxPassengers;
	}

	/**
	 * 現在の乗車人数を取得します。
	 * @return 乗車人数
	 */
	public int getPassengers() {
		return passengers;
	}

	/**
	 * この車の最大乗車人数を取得します。
	 * @return 最大乗車人数
	 */
	public int getMaxPassengers() {
		return maxPassengers;
	}
	
	/**
	 * 乗客を1人乗せます。
	 * @return 乗れたら true、乗れなかったら false
	 */
	public boolean add() {
		if (passengers == maxPassengers) {
			System.out.println(getName() + "は満員です。");
			return false;
		}
		passengers++;
		System.out.println(getName() + "に1人乗りました。");
		return true;
	}
	
	/**
	 * 乗客を1人おろします。
	 * @return 降ろせたら true、降ろせなかったら false
	 */
	public boolean remove() {
		if (passengers == 0) {
			System.out.println(getName() + "には誰も乗っていません。");
			return false;
		}
		passengers--;
		System.out.println(getName() + "から1人降りました。");
		return true;
	}

	@Override
	public String toString() {
		return "Vehicle " + getId() + ": Speed=" + getSpeed() + ", Direction="
				+ getDirection() + ", name=" + getName() + ", passengers="
				+ getPassengers() + ", max=" + getMaxPassengers();
	}
}
