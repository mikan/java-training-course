/*
 * Copyright(C) 2014 Yutaka Kato
 */
package local.Interpret;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.InvocationTargetException;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class CreatePrimitiveWindow extends AbstractWindow {

	private final InterpretWindow interpret;
	private final Class<?> cls;
	private JTextField nameField, valueField;
	private JButton okButton;
	private JButton cancelButton;

	public CreatePrimitiveWindow(InterpretWindow interpret, Class<?> cls) {
		this.interpret = interpret;
		this.cls = cls;

		if (!cls.isPrimitive()) {
			showErrorMessage("FATAL: Class isn't primitive");
			return;
		}

		// Value pane
		addGrid(new JLabel("Value:"));
		valueField = new JTextField("");
		valueField.addActionListener(new TextFieldActionListener());
		addGrid(valueField);

		// Name
		addGrid(new JLabel("Name:"));
		nameField = new JTextField("");
		nameField.addActionListener(new TextFieldActionListener());
		addGrid(nameField);

		// Button pane
		JPanel buttonPanel = new JPanel();
		ButtonActionListener listener = new ButtonActionListener();
		okButton = new JButton("OK");
		okButton.addActionListener(listener);
		buttonPanel.add(okButton);
		cancelButton = new JButton("Cancel");
		cancelButton.addActionListener(listener);
		buttonPanel.add(cancelButton);
		addGrid(buttonPanel);

		setTitle("Instantiating " + cls.getName());
		setSize(500, 300);
		pack();
		setLocationRelativeTo(null);
		setVisible(true);
	}

	/** Add object with input data. */
	private void addObject() {
		String value = valueField.getText();
		if (value == null || value.length() == 0) {
			showErrorMessage("Please input value");
			return;
		}
		String name = nameField.getText();
		if (name == null || name.length() == 0) {
			showErrorMessage("Please input name");
			return;
		}
		if (name.startsWith("=") || name.contains("[") || name.contains("]")) {
			showErrorMessage("Name contains illegal character.");
			return;
		}
		if (interpret.exists(name)) {
			showErrorMessage("\"" + name + "\" already used.");
			return;
		}
		try {
			if (cls.equals(byte.class)) {
				interpret.addObject(cls,
						(byte) Byte.class.getConstructor(String.class)
								.newInstance(value), name);
			} else if (cls.equals(short.class)) {
				interpret.addObject(cls,
						(short) Short.class.getConstructor(String.class)
								.newInstance(value), name);
			} else if (cls.equals(int.class)) {
				interpret.addObject(cls,
						(int) Integer.class.getConstructor(String.class)
								.newInstance(value), name);
			} else if (cls.equals(long.class)) {
				interpret.addObject(cls,
						(long) Long.class.getConstructor(String.class)
								.newInstance(value), name);
			} else if (cls.equals(float.class)) {
				interpret.addObject(cls,
						(float) Float.class.getConstructor(String.class)
								.newInstance(value), name);
			} else if (cls.equals(double.class)) {
				interpret.addObject(cls,
						(double) Double.class.getConstructor(String.class)
								.newInstance(value), name);
			} else if (cls.equals(char.class)) {
				interpret.addObject(cls,
						(char) Character.class.getConstructor(String.class)
								.newInstance(value), name);
			} else if (cls.equals(boolean.class)) {
				interpret.addObject(cls, (boolean) Boolean.class
						.getConstructor(String.class).newInstance(value), name);
			} else {
				showErrorMessage("FATAL: Undefined type: " + cls.getName());
				return;
			}
			setVisible(false);
		} catch (InstantiationException e1) {
			showErrorMessage("InstantiationException");
			return;
		} catch (IllegalAccessException e1) {
			showErrorMessage("IllegalAccessException");
			return;
		} catch (IllegalArgumentException e1) {
			showErrorMessage("IllegalArgumentException");
			return;
		} catch (InvocationTargetException e1) {
			showErrorMessage("Exception caught:"
					+ System.getProperty("line.separator") + e1.getCause());
			return;
		} catch (NoSuchMethodException e1) {
			showErrorMessage("NoSuchMethodException");
			return;
		} catch (SecurityException e1) {
			showErrorMessage("SecurityException");
			return;
		}
	}

	private class ButtonActionListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getSource().equals(okButton)) {
				addObject();
			} else if (e.getSource().equals(cancelButton)) {
				setVisible(false);
			}
		}
	}

	/** Action (= Enter key is pressed) listener for text fields. */
	private class TextFieldActionListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getSource().equals(valueField)) {
				String value = valueField.getText();
				if (value == null || value.length() == 0) {
					showErrorMessage("Please input value");
					return;
				} else {
					nameField.requestFocus();
				}
			} else if (e.getSource().equals(nameField)) {
				addObject();
			}
		}
	}
}
