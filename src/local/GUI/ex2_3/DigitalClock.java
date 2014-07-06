/*
 * Copyright(C) 2013, 2014 Yutaka Kato
 */
package local.GUI.ex2_3;

/**
 * Launch the digital clock.
 */
public class DigitalClock {

    public static void main(String[] args) {
	new DigitalClockFrame("Digital Clock 2.3");
    }

    protected static DigitalClockConfiguration getConfiguration() {
	return new ConfigurationPreferences();
    }
}