/*
 * Copyright(C) 2013 Yutaka Kato
 */
package local.JPL.ch04.ex04_01;

public class Battery implements EnergySource {

	@Override
	public boolean empty() {
		return false;
	}
}
