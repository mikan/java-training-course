/*
 * Copyright(C) 2014 Yutaka Kato
 */
package local.Interpret;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Array;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class CreateArrayWindow extends JFrame {

    private InterpretWindow interpret;
    private Class<?> cls;
    private GridBagLayout layout;
    private JTextField sizeField, nameField;
    private JButton okButton, cancelButton, initButton;

    public CreateArrayWindow(InterpretWindow interpret, Class<?> cls) {

        this.interpret = interpret;
        this.cls = cls;

        layout = new GridBagLayout();
        getContentPane().setLayout(layout);

        // Size pane
        addGrid(new JLabel("Size:"), 1, 1);
        sizeField = new JTextField("");
        addGrid(sizeField, 1, 2);

        // Name pane
        addGrid(new JLabel("Name:"), 1, 3);
        nameField = new JTextField("");
        addGrid(nameField, 1, 4);

        // Button pane
        JPanel buttonPanel = new JPanel();
        ButtonActionListener listener = new ButtonActionListener();
        cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(listener);
        buttonPanel.add(cancelButton);
        okButton = new JButton("OK");
        okButton.addActionListener(listener);
        buttonPanel.add(okButton);
        initButton = new JButton("Input data");
        initButton.addActionListener(listener);
        buttonPanel.add(initButton);
        addGrid(buttonPanel, 1, 7);

        setTitle("Creating " + cls.getName() + " array");
        setSize(500, 300);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void addGrid(JComponent comp, int x, int y) {
        addGrid(comp, x, y, 1, 1, GridBagConstraints.WEST);
    }

    private void addGrid(JComponent comp, int x, int y, int width, int height,
            int anchor) {
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

    private class ButtonActionListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource().equals(initButton)) {
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
                    new InitArrayWindow(interpret, cls, Array.newInstance(cls,
                            size), name, size);
                    setVisible(false);
                } catch (IllegalArgumentException e1) {
                    showErrorMessage("IllegalArgumentException: "
                            + e1.getMessage());
                    return;
                }
            } else if (e.getSource().equals(cancelButton)) {
                setVisible(false);
            } else if (e.getSource().equals(okButton)) {
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
                    interpret.addArray(cls, Array.newInstance(cls, size), name,
                            size);
                    setVisible(false);
                } catch (IllegalArgumentException e1) {
                    showErrorMessage("IllegalArgumentException: "
                            + e1.getMessage());
                    return;
                }
            }
        }
    }

    private void showErrorMessage(String message) {
        JOptionPane.showMessageDialog(this, message, "ERROR",
                JOptionPane.ERROR_MESSAGE);
    }
}
