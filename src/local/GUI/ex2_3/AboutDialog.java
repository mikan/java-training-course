/*
 * Copyright(C) 2013-2014 Yutaka Kato
 */
package local.GUI.ex2_3;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JWindow;
import javax.swing.SwingConstants;

@SuppressWarnings("serial")
public class AboutDialog extends JDialog {

    private static final String DIALOG_TITLE = "About";

    AboutDialog(JWindow owner) {
	super(owner);
	setLayout(new BorderLayout());

	JLabel aboutLabel = new JLabel(" Digital Clock ");
	aboutLabel.setHorizontalAlignment(SwingConstants.CENTER);
	aboutLabel.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 30));
	add(aboutLabel, BorderLayout.NORTH);

	JLabel copyLabel = new JLabel("2014 Yutaka Kato");
	copyLabel.setHorizontalAlignment(SwingConstants.CENTER);
	copyLabel.setFont(new Font(Font.SERIF, Font.PLAIN, 15));
	add(copyLabel, BorderLayout.CENTER);

	JButton okButton = new JButton("OK");
	okButton.addActionListener(new AboutDialogActionListener());
	add(okButton, BorderLayout.SOUTH);

	setTitle(DIALOG_TITLE);
	pack();
	setLocationRelativeTo(null);
	setVisible(true);
    }

    private class AboutDialogActionListener implements ActionListener {
	@Override
	public void actionPerformed(ActionEvent e) {
	    setVisible(false);
	}
    }
}