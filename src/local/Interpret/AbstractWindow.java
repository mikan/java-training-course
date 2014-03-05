/*
 * Copyright(C) 2014 Yutaka Kato
 */
package local.Interpret;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.ToolTipManager;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.UnsupportedLookAndFeelException;

@SuppressWarnings("serial")
public abstract class AbstractWindow extends JFrame {

	private GridBagLayout layout;
	private int lastAddedRow = 0;

	public AbstractWindow() {
		layout = new GridBagLayout();
		getContentPane().setLayout(layout);

		ToolTipManager tip = ToolTipManager.sharedInstance();
		tip.setInitialDelay(100);
		// tip.setDismissDelay(500);
	}

	protected void addGrid(JComponent comp) {
		addGrid(comp, 1, ++lastAddedRow, 1, 1, GridBagConstraints.WEST);
	}

	protected void addGrid(JComponent comp, int x, int y) {
		addGrid(comp, x, y, 1, 1, GridBagConstraints.WEST);
	}

	protected void addGrid(JComponent comp, int x, int y, int anchor) {
		addGrid(comp, x, y, 1, 1, anchor);
	}

	protected void addGrid(JComponent comp, int x, int y, int width, int height) {
		addGrid(comp, x, y, width, height, GridBagConstraints.WEST);
	}

	protected void addGrid(JComponent comp, int x, int y, int width,
			int height, int anchor) {
		GridBagConstraints constraints = new GridBagConstraints();
		constraints.gridx = x;
		constraints.gridy = y;
		constraints.gridwidth = width;
		constraints.gridheight = height;
		constraints.anchor = anchor;
		constraints.fill = GridBagConstraints.BOTH;
		layout.setConstraints(comp, constraints);
		getContentPane().add(comp);
	}

	protected void showErrorMessage(String message) {
		JOptionPane.showMessageDialog(this, message, "ERROR",
				JOptionPane.ERROR_MESSAGE);
	}
	
	protected void showInformationMessage(String message, String title) {
		JOptionPane.showMessageDialog(this,
				message, title,
				JOptionPane.INFORMATION_MESSAGE);
	}

	/** Set look & feel for Nimbus */
	protected void setLF() {
		for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
			if (info.getName().startsWith("Windows")) {
				try {
					UIManager.setLookAndFeel(info.getClassName());
					SwingUtilities.updateComponentTreeUI(this);
					break;
				} catch (ClassNotFoundException | InstantiationException
						| IllegalAccessException
						| UnsupportedLookAndFeelException
						| NullPointerException e) {
					e.printStackTrace();
				}
			}
		}
	}

	protected class LFActionListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
				if (((JMenuItem) e.getSource()).getText()
						.equals(info.getName())) {
					try {
						UIManager.setLookAndFeel(info.getClassName());
					} catch (ClassNotFoundException | InstantiationException
							| IllegalAccessException
							| UnsupportedLookAndFeelException e1) {
						e1.printStackTrace();
					}
					SwingUtilities.updateComponentTreeUI(AbstractWindow.this);
					pack();
					break;
				}
			}
		}
	}
}
