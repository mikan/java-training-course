/*
 * Copyright(C) 2014 Yutaka Kato
 */
package local.Interpret;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTree;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

@SuppressWarnings("serial")
public class InterpretWindow extends JFrame {

    private List<ObjectElement> objects;
    private final GridBagLayout layout;
    private final JTree objectTree;
    private final DefaultTreeModel objectTreeModel;
    private final DefaultMutableTreeNode objectRootNode;
    private final JList<InterpretField> fieldList;
    private final DefaultListModel<InterpretField> fieldListModel;
    private final JList<InterpretMethod> methodList;
    private final DefaultListModel<InterpretMethod> methodListModel;
    private final JLabel valueLabel;
    private final JButton changeFieldButton;
    private final JButton invokeMethodButton;
    private final JTextField invokeParamsField;
    private static final String NOT_SELECTED = "(not selected)";

    public InterpretWindow() {

        objects = new ArrayList<ObjectElement>();
        layout = new GridBagLayout();

        // Menu
        JMenuBar menuBar = new JMenuBar();

        // Menu [File]
        JMenu fileMenu = new JMenu("File");
        fileMenu.setMnemonic('F');
        menuBar.add(fileMenu);

        // Menu [File] -> [New object...]
        JMenuItem newObjectMenuItem = new JMenuItem("New object...");
        newObjectMenuItem.setMnemonic('N');
        newObjectMenuItem.addActionListener(new NewObjectActionListener());
        fileMenu.add(newObjectMenuItem);

        // Menu [File] -> [Exit]
        JMenuItem exitMenuItem = new JMenuItem("Exit");
        exitMenuItem.setMnemonic('E');
        exitMenuItem.addActionListener(new ExitActionListener());
        fileMenu.add(exitMenuItem);

        // Menu [Help]
        JMenu helpMenu = new JMenu("Help");
        helpMenu.setMnemonic('H');
        menuBar.add(helpMenu);

        // Menu [Help] -> [About...]
        JMenuItem aboutMenuItem = new JMenuItem("About...");
        aboutMenuItem.setMnemonic('A');
        aboutMenuItem.addActionListener(new AboutActionListener());
        helpMenu.add(aboutMenuItem);

        // Object tree
        objectRootNode = new DefaultMutableTreeNode("Objects");
        objectTree = new JTree(objectRootNode);
        objectTree.addTreeSelectionListener(new ObjectSelectionListener());
        objectTree.setRootVisible(false);
        objectTree.setSize(300, 300);
        objectTreeModel = (DefaultTreeModel) objectTree.getModel();
        addGrid(new JLabel("Objects"), 1, 1);
        addGrid(new JScrollPane(objectTree), 1, 2, 1, 3);

        // Filed list
        fieldList = new JList<>();
        fieldListModel = new DefaultListModel<>();
        fieldList.setModel(fieldListModel);
        fieldList.addListSelectionListener(new FieldSelectionListener());
        addGrid(new JLabel("Fields"), 2, 1, 2, 1);
        addGrid(new JScrollPane(fieldList), 2, 2);

        // Method list
        methodList = new JList<>();
        methodListModel = new DefaultListModel<>();
        methodList.setModel(methodListModel);
        methodList.addListSelectionListener(new MethodSelectionListener());
        addGrid(new JLabel("Methods"), 2, 3, 2, 1);
        addGrid(new JScrollPane(methodList), 2, 4);

        // Field control panel
        JPanel fieldControlPanel = new JPanel();
        FlowLayout fieldControlPanelLayout = new FlowLayout();
        fieldControlPanelLayout.setAlignment(FlowLayout.LEFT);
        fieldControlPanel.setLayout(fieldControlPanelLayout);
        JLabel valueDescLabel = new JLabel("Value: ");
        fieldControlPanel.add(valueDescLabel);
        valueLabel = new JLabel(NOT_SELECTED);
        fieldControlPanel.add(valueLabel);
        changeFieldButton = new JButton("Change...");
        changeFieldButton.addActionListener(new ChangeFieldActionListener());
        changeFieldButton.setEnabled(false);
        fieldControlPanel.add(changeFieldButton);
        addGrid(fieldControlPanel, 3, 2);

        // Method control panel
        JPanel methodControlpanel = new JPanel();
        FlowLayout methodControlPanelLayout = new FlowLayout();
        methodControlPanelLayout.setAlignment(FlowLayout.LEFT);
        methodControlpanel.setLayout(methodControlPanelLayout);
        methodControlpanel.add(new JLabel("Parameters:"));
        invokeParamsField = new JTextField();
        invokeParamsField.setPreferredSize(new Dimension(150, 20));
        methodControlpanel.add(invokeParamsField);
        invokeMethodButton = new JButton("Invoke");
        invokeMethodButton.setEnabled(false);
        invokeMethodButton.addActionListener(new InvokeMethodActionListener());
        methodControlpanel.add(invokeMethodButton);
        addGrid(methodControlpanel, 3, 4);

        // Window
        getContentPane().setLayout(layout);
        getRootPane().setJMenuBar(menuBar);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("INTERPRET");
        setResizable(false);
        pack();
        setSize(getPreferredSize());
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public static void main(String[] args) {
        new InterpretWindow().setVisible(true);
    }

    private void addGrid(JComponent comp, int x, int y) {
        addGrid(comp, x, y, GridBagConstraints.WEST);
    }

    private void addGrid(JComponent comp, int x, int y, int anchor) {
        addGrid(comp, x, y, 1, 1, anchor);
    }

    private void addGrid(JComponent comp, int x, int y, int width, int height) {
        addGrid(comp, x, y, width, height, GridBagConstraints.WEST);
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

    private class NewObjectActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String value = JOptionPane.showInputDialog(InterpretWindow.this,
                    "Input class:", "java.awt.Frame");
            if (value != null) {
                try {
                    new InstantiateWindow(InterpretWindow.this,
                            Class.forName(value));
                } catch (ClassNotFoundException cnfe) {
                    JOptionPane.showMessageDialog(InterpretWindow.this,
                            "ClassNotFoundException", "ERROR",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }

    /**
     * Add object to this window.
     * 
     * @param cls Class
     * @param instance Instance of specified class
     * @param name Name of object
     */
    void addObject(Class<?> cls, Object instance, String name) {
        objects.add(new ObjectElement(instance, name));
        DefaultMutableTreeNode classNode = getClassNode(cls);
        if (classNode == null) {
            classNode = new DefaultMutableTreeNode(cls.getName());
            objectRootNode.add(classNode);
        }
        DefaultMutableTreeNode objectNode = new DefaultMutableTreeNode(name);
        classNode.add(objectNode);
        objectTreeModel.reload();
        expandAll(objectTree, 0, objectTree.getRowCount());
        pack();
        setLocationRelativeTo(null);
    }

    /**
     * Check duplicates.
     * 
     * @param name Name of object
     * @return Exits
     */
    boolean exists(String name) {
        return getObjectElement(name) != null;
    }

    /**
     * Get object element by name.
     * 
     * @param name Name of object
     * @return Object element
     */
    private ObjectElement getObjectElement(String name) {
        for (ObjectElement e : objects)
            if (e.getName().equals(name))
                return e;
        return null;
    }

    private List<InterpretField> getAllFields(ObjectElement element) {
        Set<InterpretField> fieldSet = new HashSet<>();
        for (Field f : element.getObject().getClass().getFields()) {
            f.setAccessible(true);
            fieldSet.add(new InterpretField(f, element));
        }
        for (Field f : element.getObject().getClass().getDeclaredFields()) {
            f.setAccessible(true);
            fieldSet.add(new InterpretField(f, element));
        }
        List<InterpretField> fieldList = new ArrayList<>(fieldSet);
        Collections.sort(fieldList);
        return fieldList;
    }

    private List<InterpretMethod> getAllMethods(ObjectElement element) {
        Set<InterpretMethod> methodSet = new HashSet<>();
        for (Method m : element.getObject().getClass().getMethods()) {
            m.setAccessible(true);
            methodSet.add(new InterpretMethod(m, element));
        }
        for (Method m : element.getObject().getClass().getDeclaredMethods()) {
            m.setAccessible(true);
            methodSet.add(new InterpretMethod(m, element));
        }
        List<InterpretMethod> methodList = new ArrayList<>(methodSet);
        Collections.sort(methodList);
        return methodList;
    }

    /**
     * Get class node from object tree.
     * 
     * @param cls Class
     * @return node
     */
    private DefaultMutableTreeNode getClassNode(Class<?> cls) {
        for (int i = 0; i < objectRootNode.getChildCount(); i++)
            if (objectRootNode.getChildAt(i).toString().equals(cls.getName()))
                return (DefaultMutableTreeNode) objectRootNode.getChildAt(i);
        return null;
    }

    /**
     * Expand all items of specified tree.
     * 
     * @param tree JTree object
     * @param startingIndex Start from 0
     * @param rowCount Input tree.getRowCount()
     */
    private void expandAll(JTree tree, int startingIndex, int rowCount) {
        for (int i = startingIndex; i < rowCount; ++i)
            tree.expandRow(i);
        if (tree.getRowCount() != rowCount)
            expandAll(tree, rowCount, tree.getRowCount());
    }

    private List<String> getParameters() {
        if (invokeParamsField.getText() == null)
            return null;
        List<String> list = new ArrayList<>();
        StringTokenizer token = new StringTokenizer(
                invokeParamsField.getText(), ",");
        while (token.hasMoreTokens()) {
            list.add(token.nextToken());
        }
        return list;
    }

    private void showErrorMessage(String message) {
        JOptionPane.showMessageDialog(this, message, "ERROR",
                JOptionPane.ERROR_MESSAGE);
    }

    /** Action listener of [Exit] menu item. */
    private static class ExitActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            System.exit(0);
        }
    }

    /** Action listener of [About...] menu item. */
    private class AboutActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            new AboutDialog(InterpretWindow.this).setVisible(true);
        }
    }

    /** Tree selection listener of object tree */
    public class ObjectSelectionListener implements TreeSelectionListener {
        @Override
        public void valueChanged(TreeSelectionEvent e) {
            DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode) objectTree
                    .getLastSelectedPathComponent();
            // Not choice
            if (selectedNode == null) {
                fieldListModel.clear();
                fieldList.setEnabled(false);
                methodListModel.clear();
                methodList.setEnabled(false);
            }
            // Class choice
            else if (selectedNode.getParent().equals(objectRootNode)) {
                fieldListModel.clear();
                fieldList.setEnabled(false);
                methodListModel.clear();
                methodList.setEnabled(false);
            } else if (selectedNode.getParent().getParent()
                    .equals(objectRootNode)) {
                ObjectElement element = getObjectElement(selectedNode
                        .toString());
                if (element == null) {
                    System.err.println("ObjectElement null!");
                    return;
                }
                // Load fields
                fieldListModel.clear();
                for (InterpretField f : getAllFields(element))
                    fieldListModel.addElement(f);
                fieldList.setEnabled(true);
                // Load methods
                methodListModel.clear();
                for (InterpretMethod m : getAllMethods(element))
                    methodListModel.addElement(m);
                methodList.setEnabled(true);
                pack();
                setLocationRelativeTo(null);
            }
        }
    }

    private class FieldSelectionListener implements ListSelectionListener {

        @Override
        public void valueChanged(ListSelectionEvent e) {
            InterpretField intField = fieldList.getSelectedValue();
            if (intField == null) {
                valueLabel.setText(NOT_SELECTED);
                valueLabel.setForeground(Color.black);
                changeFieldButton.setEnabled(false);
            } else {
                try {
                    Object value = intField.getField()
                            .get(intField.getObject());
                    valueLabel.setText(value.toString());
                    valueLabel.setForeground(Color.black);
                    changeFieldButton.setEnabled(true);
                } catch (IllegalArgumentException e1) {
                    valueLabel.setText("(IllegalArgument)");
                    valueLabel.setForeground(Color.red);
                    changeFieldButton.setEnabled(false);
                } catch (NullPointerException e1) {
                    valueLabel.setText("(null)");
                    valueLabel.setForeground(Color.darkGray);
                    changeFieldButton.setEnabled(true);
                } catch (SecurityException e1) {
                    valueLabel.setText("(SecurityException)");
                    valueLabel.setForeground(Color.red);
                    changeFieldButton.setEnabled(false);
                } catch (IllegalAccessException e1) {
                    valueLabel.setText("(IllegalAccess)");
                    valueLabel.setForeground(Color.red);
                    changeFieldButton.setEnabled(false);
                }
                pack();
            }
        }
    }

    /** Action listener of [Change...] button */
    private class ChangeFieldActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            InterpretField intField = fieldList.getSelectedValue();
            if (intField == null) {
                showErrorMessage("Field not selected.");
                return;
            }
            String current;
            try {
                current = intField.getField().get(intField.getObject())
                        .toString();
            } catch (IllegalArgumentException e1) {
                showErrorMessage("IllegalArgumentException");
                return;
            } catch (IllegalAccessException e1) {
                showErrorMessage("IllegalAccessException");
                return;
            } catch (NullPointerException e1) {
                current = "";
            }
            while (true) {
                String newValue = JOptionPane.showInputDialog("Input value:",
                        current);
                if (newValue == null)
                    return;
                if (newValue.isEmpty()) {
                    showErrorMessage("Value is empty.");
                    continue;
                }
                try {
                    Object object = intField.getObject();
                    if (Modifier.isStatic(intField.getField().getModifiers())) {
                        object = null;
                    }
                    Class<?> type = intField.getField().getType();
                    if (type.equals(byte.class))
                        intField.getField().setByte(object,
                                Byte.parseByte(newValue));
                    else if (type.equals(short.class))
                        intField.getField().setShort(object,
                                Short.parseShort(newValue));
                    else if (type.equals(int.class))
                        intField.getField().setInt(object,
                                Integer.parseInt(newValue));
                    else if (type.equals(long.class))
                        intField.getField().setLong(object,
                                Long.parseLong(newValue));
                    else if (type.equals(float.class))
                        intField.getField().setFloat(object,
                                Float.parseFloat(newValue));
                    else if (type.equals(double.class))
                        intField.getField().setDouble(object,
                                Double.parseDouble(newValue));
                    else if (type.equals(char.class))
                        intField.getField().setChar(object,
                                (char) Integer.parseInt(newValue));
                    else if (type.equals(boolean.class))
                        intField.getField().setBoolean(object,
                                Boolean.parseBoolean(newValue));
                    else
                        intField.getField().set(object, newValue);
                    valueLabel.setText(newValue);
                    pack();
                    break;
                } catch (NumberFormatException e1) {
                    showErrorMessage("NumberFormatException");
                    continue;
                } catch (IllegalArgumentException e1) {
                    showErrorMessage("IllegalArgumentException");
                    e1.printStackTrace();
                    continue;
                } catch (IllegalAccessException e1) {
                    showErrorMessage("IllegalAccessException");
                    break;
                } catch (SecurityException e1) {
                    showErrorMessage("SecurityException");
                    break;
                }
            }
        }
    }

    private class MethodSelectionListener implements ListSelectionListener {
        @Override
        public void valueChanged(ListSelectionEvent e) {
            InterpretMethod intMethod = methodList.getSelectedValue();
            if (intMethod == null) {
                invokeMethodButton.setEnabled(false);
            } else if (intMethod.getMethod().getParameterTypes().length == 0) {
                invokeMethodButton.setEnabled(true);
                invokeParamsField.setEnabled(false);
            } else {
                invokeMethodButton.setEnabled(true);
                invokeParamsField.setEnabled(true);
            }
        }
    }

    /** Action listener of [Invoke] button */
    private class InvokeMethodActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            InterpretMethod intMethod = methodList.getSelectedValue();
            if (intMethod == null) {
                showErrorMessage("Method not selected.");
                return;
            }

            // Load parameters
            List<String> inputParams = getParameters();
            Class<?>[] params = intMethod.getMethod().getParameterTypes();
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
                            paramData[i] = (char) Integer.parseInt(inputParam);
                        else if (params[i].equals(byte.class))
                            paramData[i] = Byte.parseByte(inputParam);
                        else if (params[i].equals(boolean.class))
                            paramData[i] = Boolean.parseBoolean(inputParam);
                        else {
                            showErrorMessage("Unknown type");
                            return;
                        }
                        System.out.println("paramData[" + i + "]=" + paramData[i]);
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
                        System.out.println("paramData[" + i + "]=" + p);
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

            Object result;
            try {
                result = intMethod.getMethod().invoke(intMethod.getObject(), paramData);
            } catch (IllegalAccessException e1) {
                showErrorMessage("IllegalAccessException");
                return;
            } catch (IllegalArgumentException e1) {
                showErrorMessage("IllegalArgumentException");
                return;
            } catch (InvocationTargetException e1) {
                showErrorMessage("InvocationTargetException");
                return;
            }
            if (result == null)
                result = "(none)";
            JOptionPane.showMessageDialog(InterpretWindow.this, "Result: "
                    + result);
        }
    }
}
