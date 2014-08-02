/*
 * Copyright(C) 2013, 2014 Yutaka Kato
 */
package local.GUI.ex2_4;

/**
 * Launch the digital clock.
 */
public class DigitalClock {

	public static void main(String[] args) {
		new DigitalClockFrame("Digital Clock 2.4");
	}
	
	protected static DigitalClockConfiguration getConfiguration() {
	    return new ConfigurationPreferences();
	}
}