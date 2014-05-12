/*
 * Copyright(C) 2014 Yutaka Kato
 */
package local.GUI.ex2_2;

import javax.swing.JComponent;

/** Repaint component with specified interval. */
public class RepaintThread extends Thread {

	private JComponent component;
	private int interval;

	public RepaintThread(JComponent component, int interval) {
		this.component = component;
		this.interval = interval;
	}

	@Override
	public void run() {
		while (true) {
			component.repaint();
			try {
				Thread.sleep(interval);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
