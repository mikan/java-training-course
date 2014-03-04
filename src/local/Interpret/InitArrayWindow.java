/*
 * Copyright(C) 2014 Yutaka Kato
 */
package local.Interpret;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Array;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

@SuppressWarnings("serial")
public class InitArrayWindow extends AbstractWindow {

	private InterpretWindow interpret;
	private Class<?> cls;
	private Object instance;
	private String name;
	private int length;
	private JTable table;
	private DefaultTableModel tableModel;
	private JButton okButton, cancelButton, zeroFillButton;

	public InitArrayWindow(InterpretWindow interpret, Class<?> cls,
			Object instance, String name, int length) {

		this.interpret = interpret;
		this.cls = cls;
		this.instance = instance;
		this.name = name;
		this.length = length;

		// Table pane
		addGrid(new JLabel("Size:"), 1, 1);
		table = new JTable(length, 1);
		tableModel = (DefaultTableModel) table.getModel();
		addGrid(new JScrollPane(table), 1, 2);

		// Control pane
		JPanel controlPanel = new JPanel();
		ButtonActionListener listener = new ButtonActionListener();
		zeroFillButton = new JButton("Zero fill");
		zeroFillButton.addActionListener(listener);
		controlPanel.add(zeroFillButton);
		addGrid(controlPanel, 1, 3);

		// Button pane
		JPanel buttonPanel = new JPanel();
		okButton = new JButton("OK");
		okButton.addActionListener(listener);
		buttonPanel.add(okButton);
		cancelButton = new JButton("Cancel");
		cancelButton.addActionListener(listener);
		buttonPanel.add(cancelButton);
		addGrid(buttonPanel, 1, 4);

		setTitle("Initializing " + cls.getName() + " array: " + name);
		setSize(500, 300);
		pack();
		setLocationRelativeTo(null);
		setVisible(true);
	}

	private class ButtonActionListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getSource().equals(okButton)) {
				try {
					if (cls.equals(byte.class))
						for (int i = 0; i < tableModel.getRowCount(); i++)
							Array.setByte(instance, i,
									(byte) tableModel.getValueAt(i, 0));
					else if (cls.equals(short.class))
						for (int i = 0; i < tableModel.getRowCount(); i++)
							Array.setShort(instance, i,
									(short) tableModel.getValueAt(i, 0));
					else if (cls.equals(int.class))
						for (int i = 0; i < tableModel.getRowCount(); i++)
							Array.setInt(instance, i,
									(int) tableModel.getValueAt(i, 0));
					else if (cls.equals(long.class))
						for (int i = 0; i < tableModel.getRowCount(); i++)
							Array.setLong(instance, i,
									(long) tableModel.getValueAt(i, 0));
					else if (cls.equals(float.class))
						for (int i = 0; i < tableModel.getRowCount(); i++)
							Array.setFloat(instance, i,
									(float) tableModel.getValueAt(i, 0));
					else if (cls.equals(double.class))
						for (int i = 0; i < tableModel.getRowCount(); i++)
							Array.setDouble(instance, i,
									(double) tableModel.getValueAt(i, 0));
					else if (cls.equals(char.class))
						for (int i = 0; i < tableModel.getRowCount(); i++)
							Array.setChar(instance, i,
									(char) tableModel.getValueAt(i, 0));
					else if (cls.equals(boolean.class))
						for (int i = 0; i < tableModel.getRowCount(); i++)
							Array.setBoolean(instance, i,
									(boolean) tableModel.getValueAt(i, 0));
					else if (cls.equals(String.class))
						for (int i = 0; i < tableModel.getRowCount(); i++)
							Array.set(instance, i, tableModel.getValueAt(i, 0)
									.toString());
					else
						for (int i = 0; i < tableModel.getRowCount(); i++)
							Array.set(instance, i, tableModel.getValueAt(i, 0));
				} catch (IllegalArgumentException ex) {
					showErrorMessage("IllegalArgumentException");
					return;
				} catch (NullPointerException ex) {
					showErrorMessage("NullPointerException");
					return;
				}
				interpret.addArray(cls, instance, name, length);
				setVisible(false);
			} else if (e.getSource().equals(cancelButton)) {
				setVisible(false);
			} else if (e.getSource().equals(zeroFillButton)) {
				for (int i = 0; i < tableModel.getRowCount(); i++)
					for (int j = 0; j < tableModel.getColumnCount(); j++)
						tableModel.setValueAt(0, i, j);
			}
		}
	}
}
