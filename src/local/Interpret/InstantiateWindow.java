/*
 * Copyright(C) 2014 Yutaka Kato
 */
package local.Interpret;

import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DropTarget;
import java.awt.dnd.DropTargetDropEvent;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;

import javax.swing.DropMode;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import static local.Interpret.InterpretWindow.OBJECT_PREFIX;

@SuppressWarnings("serial")
public class InstantiateWindow extends AbstractWindow {

	private InterpretWindow interpret;
	private Class<?> cls;
	private JList<String> constructorList;
	private JTextField nameField, paramField;
	private JButton okButton;
	private JButton cancelButton;
	private Set<Constructor<?>> constructors;
	private int index;
	private boolean array = false;
	private String arrayName;

	public InstantiateWindow(InterpretWindow interpret, Class<?> cls,
			String name, int index) {
		this.interpret = interpret;
		this.cls = cls;
		if (name != null) {
			array = true;
			arrayName = name;
		}
		this.index = index;

		// Constructors label
		addGrid(new JLabel("Constructors:"), 1, 1);

		// Constructor list
		constructors = new HashSet<>();
		for (Constructor<?> c : cls.getConstructors())
			constructors.add(c);
		for (Constructor<?> c : cls.getDeclaredConstructors())
			constructors.add(c);
		Set<String> constructorNames = new HashSet<>();
		for (Constructor<?> c : constructors)
			constructorNames.add(c.toString());
		constructorList = new JList<>(
				constructorNames.toArray(new String[constructors.size()]));
		constructorList
				.addListSelectionListener(new ConstructorSelectionListener());
		JScrollPane scrollPane = new JScrollPane(constructorList);
		addGrid(scrollPane, 1, 2);

		// Parameters pane
		addGrid(new JLabel("Parameter(s):"), 1, 3);
		paramField = new JTextField("");
		if (!cls.isPrimitive())
			paramField.setEnabled(false);
		paramField.addActionListener(new TextFieldActionListener());
		paramField.setDropMode(DropMode.INSERT);
		paramField.setDropTarget(new ObjectDropTarget());
		paramField
				.setToolTipText("[ex1] null  [ex2] abc,123  [ex3] =(object name)");
		addGrid(paramField, 1, 4);

		// Name
		addGrid(new JLabel("Name:"), 1, 5);
		nameField = new JTextField("");
		nameField.addActionListener(new TextFieldActionListener());
		if (array) {
			nameField.setText(name + "[" + index + "]");
			nameField.setEnabled(false);
		}
		addGrid(nameField, 1, 6);

		// Button pane
		JPanel buttonPanel = new JPanel();
		ButtonActionListener listener = new ButtonActionListener();
		okButton = new JButton("OK");
		if (!cls.isPrimitive())
			okButton.setEnabled(false);
		okButton.addActionListener(listener);
		buttonPanel.add(okButton);
		cancelButton = new JButton("Cancel");
		cancelButton.addActionListener(listener);
		buttonPanel.add(cancelButton);
		addGrid(buttonPanel, 1, 7);

		setTitle("Instantiating " + cls.getName());
		setSize(500, 300);
		pack();
		setLocationRelativeTo(null);
		setVisible(true);
	}

	private Constructor<?> getSelectedConstructor() {
		String selectedName = constructorList.getSelectedValue();
		for (Constructor<?> c : constructors)
			if (c.toString().equals(selectedName))
				return c;
		return null;
	}

	private List<String> getParameters() {
		if (paramField.getText() == null)
			return null;
		List<String> list = new ArrayList<>();
		StringTokenizer token = new StringTokenizer(paramField.getText(), ",");
		while (token.hasMoreTokens()) {
			list.add(token.nextToken());
		}
		return list;
	}

	/** Add object with input data. */
	private void addObject() {
		String name = nameField.getText();
		if (name == null || name.length() == 0) {
			showErrorMessage("Please input name");
			return;
		}
		if (!array) {
			if (name.startsWith("=") || name.contains("[")
					|| name.contains("]")) {
				showErrorMessage("Name contains illegal character.");
				return;
			}
			if (interpret.exists(name)) {
				showErrorMessage("\"" + name + "\" already used.");
				return;
			}
		}
		if (constructorList.getSelectedValue() == null) {
			showErrorMessage("Please select constructor");
			return;
		}
		Constructor<?> selected = getSelectedConstructor();
		if (selected == null) {
			showErrorMessage("Please select constructor");
			return;
		}
		Class<?>[] params = selected.getParameterTypes();
		if (!array && (params == null || params.length == 0)) {
			// Instantiate with no parameter
			try {
				interpret.addObject(cls, selected.newInstance(), name);
				setVisible(false);
			} catch (InstantiationException e1) {
				showErrorMessage("InstantiationException: " + e1.getMessage());
				return;
			} catch (IllegalAccessException e1) {
				showErrorMessage("IllegalAccessException: " + e1.getMessage());
				return;
			} catch (IllegalArgumentException e1) {
				showErrorMessage("IllegalArgumentException: " + e1.getMessage());
				return;
			} catch (InvocationTargetException e1) {
				showErrorMessage("InvocationTargetException: "
						+ e1.getMessage());
				return;
			}
		} else {
			List<String> inputParams = getParameters();
			Object[] paramData = new Object[params.length];
			for (int i = 0; i < params.length; i++) {
				String inputParam;
				try {
					inputParam = inputParams.get(i);
				} catch (IndexOutOfBoundsException ioobe) {
					paramData[i] = null;
					System.out.println("paramData[" + i + "]=(null)");
					continue;
				}
				if (inputParam.equals("null")) {
					paramData[i] = null;
					System.out.println("paramData[" + i + "]=(null)");
					continue;
				}
				if (inputParam.startsWith(OBJECT_PREFIX)) {
					String escaped = inputParam.substring(OBJECT_PREFIX
							.length());
					if (escaped.contains("[") && escaped.endsWith("]")) {
						String arrayName = escaped.substring(0,
								escaped.indexOf("["));
						String indexStr = escaped.substring(
								escaped.indexOf("[") + 1,
								escaped.lastIndexOf("]"));
						ArrayElement arrayElement = interpret
								.getArrayElement(arrayName);
						if (arrayElement == null) {
							showErrorMessage("Array not found: " + escaped);
							return;
						}
						try {
							int index = Integer.parseInt(indexStr);
							paramData[i] = arrayElement.getObjectAt(index);
						} catch (NumberFormatException ex) {
							showErrorMessage("Illegal index: " + indexStr);
							return;
						} catch (ArrayIndexOutOfBoundsException ex) {
							showErrorMessage("Illegal index: " + indexStr);
							return;
						}
					} else {
						InterpretObject element = interpret
								.getObjectElement(escaped);
						ArrayElement arrayElement = interpret
								.getArrayElement(escaped);
						if (element == null && arrayElement == null) {
							showErrorMessage("Object not found: " + escaped);
							return;
						}
						if (element != null)
							paramData[i] = element.getObject();
						if (arrayElement != null)
							paramData[i] = arrayElement.getObject();
					}
				} else {
					// parameter is primitive type
					if (params[i].isPrimitive()) {
						try {
							if (params[i].equals(int.class))
								paramData[i] = Integer.parseInt(inputParam);
							else if (params[i].equals(double.class))
								paramData[i] = Double.parseDouble(inputParam);
							else if (params[i].equals(float.class))
								paramData[i] = Float.parseFloat(inputParam);
							else if (params[i].equals(short.class))
								paramData[i] = Short.parseShort(inputParam);
							else if (params[i].equals(char.class))
								paramData[i] = (char) Integer
										.parseInt(inputParam);
							else if (params[i].equals(byte.class))
								paramData[i] = Byte.parseByte(inputParam);
							else if (params[i].equals(boolean.class))
								paramData[i] = Boolean.parseBoolean(inputParam);
							else {
								showErrorMessage("Unknown type");
								return;
							}
							System.out.println("paramData[" + i + "]="
									+ paramData[i]);
							continue;
						} catch (NumberFormatException e1) {
							showErrorMessage("NumberFormatException");
							return;
						}
					} else {
						// parameter has string constructor
						try {
							Object p = params[i].getConstructor(String.class)
									.newInstance(inputParam);
							paramData[i] = p;
							continue;
						} catch (ReflectiveOperationException e1) {
							System.err
									.println("Parameter #"
											+ (i + 1)
											+ " hasn't string constructor. Inserting null.");
						} catch (SecurityException e1) {
							showErrorMessage("SecurityException");
							return;
						}
						// insert null
						System.out.println("paramData[" + i + "]=(null)");
						paramData[i] = null;
					}
				}
			}
			// Instantiate with parameter(s)
			try {
				if (array) {
					interpret.addArrayCell(selected.newInstance(paramData),
							arrayName, index);
				} else {
					interpret.addObject(cls, selected.newInstance(paramData),
							name);
				}
				setVisible(false);
			} catch (InstantiationException e1) {
				showErrorMessage("InstantiationException: " + e1.getMessage());
				return;
			} catch (IllegalAccessException e1) {
				showErrorMessage("IllegalAccessException: " + e1.getMessage());
				return;
			} catch (IllegalArgumentException e1) {
				showErrorMessage("IllegalArgumentException: " + e1.getMessage());
				return;
			} catch (InvocationTargetException e1) {
				showErrorMessage("Exception caught:"
						+ System.getProperty("line.separator") + e1.getCause());
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

	private class ConstructorSelectionListener implements ListSelectionListener {
		@Override
		public void valueChanged(ListSelectionEvent e) {
			if (constructorList.getSelectedValue() == null) {
				okButton.setEnabled(false);
			} else {
				Constructor<?> selected = getSelectedConstructor();
				if (selected != null) {
					Class<?>[] params = selected.getParameterTypes();
					if (params == null || params.length == 0) {
						paramField.setEnabled(false);
					} else {
						paramField.setEnabled(true);
					}
					okButton.setEnabled(true);
				}
			}
		}
	}

	private class ObjectDropTarget extends DropTarget {
		@Override
		public void drop(DropTargetDropEvent e) {
			Transferable t = e.getTransferable();
			if (t.isDataFlavorSupported(DataFlavor.stringFlavor)) {
				e.acceptDrop(DnDConstants.ACTION_REFERENCE);
				try {
					String s = (String) t
							.getTransferData(DataFlavor.stringFlavor);
					String text = paramField.getText();
					if (text.length() == 0)
						paramField.setText("=" + s);
					else
						paramField.setText(text + ",=" + s);
				} catch (UnsupportedFlavorException e1) {
					showErrorMessage("UnsupportedFlavorException");
					return;
				} catch (IOException e1) {
					showErrorMessage("IOException");
					return;
				}
			}
		}
	}

	/** Action (= Enter key is pressed) listener for text fields. */
	private class TextFieldActionListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getSource().equals(paramField)) {
				if (nameField.isEnabled())
					nameField.requestFocus();
				else
					addObject();
			} else if (e.getSource().equals(nameField)) {
				addObject();
			}
		}
	}
}
