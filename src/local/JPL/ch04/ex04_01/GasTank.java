/*
 * Copyright(C) 2013 Yutaka Kato
 */
package local.JPL.ch04.ex04_01;

public class GasTank implements EnergySource {

	@Override
    public boolean empty() {
		return false;
	}
}
