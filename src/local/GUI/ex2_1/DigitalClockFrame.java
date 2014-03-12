/*
 * Copyright(C) 2013, 2014 Yutaka Kato
 */
package local.GUI.ex2_1;

import java.awt.BorderLayout;

import javax.swing.JFrame;

/** Display window of the digital clock. */
@SuppressWarnings("serial")
public class DigitalClockFrame extends JFrame {

	public DigitalClockFrame(String title) {
		super(title);
		setLayout(new BorderLayout());
		DigitalClockPanel panel = new DigitalClockPanel();
		add(panel, BorderLayout.CENTER);
		pack();
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);
		setLocationRelativeTo(null);
		setVisible(true);
		new RepaintThread(panel, 3).start();
	}
}