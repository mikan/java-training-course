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
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class CreateArrayWindow extends AbstractWindow {

	private InterpretWindow interpret;
	private Class<?> cls;
	private JTextField sizeField, nameField;
	private JButton okButton, cancelButton, initButton;

	public CreateArrayWindow(InterpretWindow interpret, Class<?> cls) {

		this.interpret = interpret;
		this.cls = cls;

		// Size pane
		addGrid(new JLabel("Size:"), 1, 1);
		sizeField = new JTextField("");
		sizeField.addActionListener(new TextFieldActionListener());
		sizeField.setToolTipText("Size of array");
		addGrid(sizeField, 1, 2);

		// Name pane
		addGrid(new JLabel("Name:"), 1, 3);
		nameField = new JTextField("");
		nameField.addActionListener(new TextFieldActionListener());
		nameField.setToolTipText("Name of array");
		addGrid(nameField, 1, 4);

		// Button pane
		JPanel buttonPanel = new JPanel();
		ButtonActionListener listener = new ButtonActionListener();
		okButton = new JButton("OK");
		okButton.addActionListener(listener);
		buttonPanel.add(okButton);
		initButton = new JButton("Input data");
		initButton.addActionListener(listener);
		buttonPanel.add(initButton);
		cancelButton = new JButton("Cancel");
		cancelButton.addActionListener(listener);
		buttonPanel.add(cancelButton);
		addGrid(buttonPanel, 1, 7);

		setTitle("Creating " + cls.getName() + " array");
		setSize(500, 300);
		pack();
		setLocationRelativeTo(null);
		setVisible(true);
	}

	private void doInitData() {
		int size;
		try {
			String sizeStr = sizeField.getText();
			if (sizeStr == null || sizeStr.length() == 0) {
				showErrorMessage("Please input size");
				return;
			}
			size = Integer.parseInt(sizeStr);
		} catch (NumberFormatException ex) {
			showErrorMessage("NumberFormatException");
			sizeField.setText("");
			sizeField.requestFocus();
			return;
		}
		String name = nameField.getText();
		if (name == null || name.length() == 0) {
			showErrorMessage("Please input name");
			return;
		}
		if (interpret.exists(name)) {
			showErrorMessage("\"" + name + "\" already used.");
			return;
		}
		// Instantiate array
		try {
			new InitArrayWindow(interpret, cls, Array.newInstance(cls, size),
					name, size);
			setVisible(false);
		} catch (IllegalArgumentException e1) {
			showErrorMessage("IllegalArgumentException: " + e1.getMessage());
			return;
		}
	}

	private void addArray() {
		int size;
		try {
			String sizeStr = sizeField.getText();
			if (sizeStr == null || sizeStr.length() == 0) {
				showErrorMessage("Please input size");
				return;
			}
			size = Integer.parseInt(sizeStr);
		} catch (NumberFormatException ex) {
			showErrorMessage("NumberFormatException");
			sizeField.setText("");
			sizeField.requestFocus();
			return;
		}
		String name = nameField.getText();
		if (name == null || name.length() == 0) {
			showErrorMessage("Please input name");
			return;
		}
		if (interpret.exists(name)) {
			showErrorMessage("\"" + name + "\" already used.");
			return;
		}
		// Instantiate array
		try {
			interpret.addArray(cls, Array.newInstance(cls, size), name, size);
			setVisible(false);
		} catch (IllegalArgumentException e1) {
			showErrorMessage("IllegalArgumentException: " + e1.getMessage());
			return;
		} catch (NegativeArraySizeException e1) {
			showErrorMessage("NegativeArraySizeException");
			return;
		} catch (OutOfMemoryError e1) {
			showErrorMessage("OutOfMemoryError: " + e1.getMessage());
			return;
		} catch (VirtualMachineError e1) {
			showErrorMessage("VirtualMachineError: " + e1.getMessage());
			return;
		} catch (Error e1) {
			showErrorMessage("Error: " + e1.getMessage());
			return;
		} catch (RuntimeException e1) {
			showErrorMessage("RuntimeException: " + e1.getMessage());
			return;
		}
	}

	private class ButtonActionListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getSource().equals(initButton))
				doInitData();
			else if (e.getSource().equals(cancelButton))
				setVisible(false);
			else if (e.getSource().equals(okButton))
				addArray();
		}
	}

	/** Action (= Enter key is pressed) listener for text fields. */
	private class TextFieldActionListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getSource().equals(sizeField)) {
				String sizeStr = sizeField.getText();
				if (sizeStr == null || sizeStr.length() == 0) {
					showErrorMessage("Please input size");
					return;
				}
				try {
					Integer.parseInt(sizeStr);
					nameField.requestFocus();
				} catch (NumberFormatException ex) {
					showErrorMessage("NumberFormatException");
					sizeField.setText("");
					sizeField.requestFocus();
					return;
				}
			} else if (e.getSource().equals(nameField)) {
				String name = nameField.getText();
				if (name == null || name.length() == 0) {
					showErrorMessage("Please input name");
					return;
				}
				if (interpret.exists(name)) {
					showErrorMessage("\"" + name + "\" already used.");
					return;
				}
				okButton.requestFocus();
			}
		}
	}
}
