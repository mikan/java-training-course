/*
 * Copyright(C) 2014 Yutaka Kato
 */
package local.Interpret;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DropTarget;
import java.awt.dnd.DropTargetDropEvent;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
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
import javax.swing.DropMode;
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
    private static final String OBJECT_PREFIX = "=";
    private boolean showArrayArea = false;
    private List<ArrayElement> arrays;
    private JTree arrayTree;
    private DefaultTreeModel arrayTreeModel;
    private DefaultMutableTreeNode arrayRootNode;
    private JList<ObjectElement> arrayCellList;
    private DefaultListModel<ObjectElement> arrayCellListModel;
    private JButton insertNewButton;

    public InterpretWindow() {

        objects = new ArrayList<ObjectElement>();
        arrays = new ArrayList<ArrayElement>();
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

        // Menu [File] -> [New array...]
        JMenuItem newArrayMenuItem = new JMenuItem("New array...");
        newArrayMenuItem.setMnemonic('r');
        newArrayMenuItem.addActionListener(new NewArrayActionListener());
        fileMenu.add(newArrayMenuItem);

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
        objectTree.setDragEnabled(true);
        objectTreeModel = (DefaultTreeModel) objectTree.getModel();
        addGrid(new JLabel("Objects           "), 1, 1);
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
        fieldControlPanel.setPreferredSize(new Dimension(180, 100));
        JLabel valueDescLabel = new JLabel("Value: ");
        fieldControlPanel.add(valueDescLabel);
        valueLabel = new JLabel(NOT_SELECTED);
        valueLabel.setPreferredSize(new Dimension(120, 10));
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
        methodControlpanel.setPreferredSize(new Dimension(180, 100));
        methodControlpanel.setLayout(methodControlPanelLayout);
        methodControlpanel.add(new JLabel("Parameters:"));
        invokeParamsField = new JTextField();
        invokeParamsField.setPreferredSize(new Dimension(150, 20));
        invokeParamsField.setDropMode(DropMode.INSERT);
        invokeParamsField.setDropTarget(new ObjectDropTarget());
        methodControlpanel.add(invokeParamsField);
        JLabel ex1 = new JLabel("ex1) null          ");
        JLabel ex2 = new JLabel("ex2) abc,123       ");
        JLabel ex3 = new JLabel("ex3) =(object name)");
        ex1.setForeground(Color.darkGray);
        ex2.setForeground(Color.darkGray);
        ex3.setForeground(Color.darkGray);
        methodControlpanel.add(ex1);
        methodControlpanel.add(ex2);
        methodControlpanel.add(ex3);
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

    private void showArrayArea() {
        if (showArrayArea)
            return;

        // Array tree
        addGrid(new JLabel("Arrays"), 1, 5);
        arrayRootNode = new DefaultMutableTreeNode("Arrays");
        arrayTree = new JTree(arrayRootNode);
        arrayTree.setRootVisible(false);
        arrayTree.addTreeSelectionListener(new ArrayObjectSelectionListener());
        arrayTreeModel = (DefaultTreeModel) arrayTree.getModel();
        addGrid(new JScrollPane(arrayTree), 1, 6);

        // Array cell list
        addGrid(new JLabel("Cells"), 2, 5);
        arrayCellListModel = new DefaultListModel<>();
        arrayCellList = new JList<>();
        arrayCellList.setModel(arrayCellListModel);
        arrayCellList
                .addListSelectionListener(new ArrayCellSelectionListener());
        addGrid(new JScrollPane(arrayCellList), 2, 6);

        // Array cell control panel
        JPanel arrayCellControlPanel = new JPanel();
        FlowLayout arrayCellControlPanelLayout = new FlowLayout();
        arrayCellControlPanelLayout.setAlignment(FlowLayout.LEFT);
        arrayCellControlPanel.setPreferredSize(new Dimension(180, 100));
        arrayCellControlPanel.setLayout(arrayCellControlPanelLayout);
        insertNewButton = new JButton("Insert new...");
        insertNewButton.setEnabled(false);
        insertNewButton.addActionListener(new InsertNewActionListener());
        arrayCellControlPanel.add(insertNewButton);
        addGrid(arrayCellControlPanel, 3, 6);

        pack();
        showArrayArea = true;
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

    void addArray(Class<?> cls, Object instance, String name, int length) {
        showArrayArea();
        arrays.add(new ArrayElement(instance, name, length));
        DefaultMutableTreeNode arrayNode = getArrayClassNode(cls);
        if (arrayNode == null) {
            arrayNode = new DefaultMutableTreeNode(cls.getName());
            arrayRootNode.add(arrayNode);
        }
        DefaultMutableTreeNode objectNode = new DefaultMutableTreeNode(name);
        arrayNode.add(objectNode);
        arrayTreeModel.reload();
        expandAll(arrayTree, 0, arrayTree.getRowCount());
        pack();
        setLocationRelativeTo(null);
    }

    void addArrayCell(Object instance, String name, int index) {
        ArrayElement element = getArrayElement(name);
        element.setObjectElementAt(index, instance);
        arrayCellListModel.setElementAt(new ObjectElement(instance, name + "["
                + index + "]"), index);
        new ArrayCellSelectionListener().valueChanged(null);
    }

    /**
     * Check duplicates.
     * 
     * @param name Name of object
     * @return Exits
     */
    boolean exists(String name) {
        return getObjectElement(name) != null || getArrayElement(name) != null;
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

    /**
     * Get array object element by name.
     * 
     * @param name Name of object
     * @return Object element
     */
    private ArrayElement getArrayElement(String name) {
        for (ArrayElement e : arrays)
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
     * Get class node from array tree.
     * 
     * @param cls Class
     * @return node
     */
    private DefaultMutableTreeNode getArrayClassNode(Class<?> cls) {
        for (int i = 0; i < arrayRootNode.getChildCount(); i++)
            if (arrayRootNode.getChildAt(i).toString().equals(cls.getName()))
                return (DefaultMutableTreeNode) arrayRootNode.getChildAt(i);
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

    private class NewObjectActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String value = JOptionPane.showInputDialog(InterpretWindow.this,
                    "Input class:", "java.awt.Frame");
            if (value != null) {
                try {
                    new InstantiateWindow(InterpretWindow.this,
                            Class.forName(value), null, 0);
                } catch (ClassNotFoundException cnfe) {
                    JOptionPane.showMessageDialog(InterpretWindow.this,
                            "ClassNotFoundException", "ERROR",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        }
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
                    showErrorMessage("FATAL: ObjectElement is null!");
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

    /** List selection listener of field list */
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
                    Object value;
                    if (Modifier.isStatic(intField.getField().getModifiers()))
                        value = intField.getField().get(null);
                    else
                        value = intField.getField().get(intField.getObject());
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

            // Get field value
            Object value;
            Field field = intField.getField();
            try {
                if (Modifier.isStatic(field.getModifiers()))
                    value = field.get(null);
                else
                    value = field.get(intField.getObject());
            } catch (IllegalArgumentException e1) {
                showErrorMessage("IllegalArgumentException");
                return;
            } catch (IllegalAccessException e1) {
                showErrorMessage("IllegalAccessException");
                return;
            }

            // Get new value from input dialog
            while (true) {
                String newValue = JOptionPane.showInputDialog("Input value:",
                        value == null ? "(null)" : value);
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
                        field.setByte(object, Byte.parseByte(newValue));
                    else if (type.equals(short.class))
                        field.setShort(object, Short.parseShort(newValue));
                    else if (type.equals(int.class))
                        field.setInt(object, Integer.parseInt(newValue));
                    else if (type.equals(long.class))
                        field.setLong(object, Long.parseLong(newValue));
                    else if (type.equals(float.class))
                        field.setFloat(object, Float.parseFloat(newValue));
                    else if (type.equals(double.class))
                        field.setDouble(object, Double.parseDouble(newValue));
                    else if (type.equals(char.class))
                        field.setChar(object, (char) Integer.parseInt(newValue));
                    else if (type.equals(boolean.class))
                        field.setBoolean(object, Boolean.parseBoolean(newValue));
                    else
                        field.set(object, newValue);
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
                // Create input parameter
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
                // Object load
                if (inputParam.startsWith(OBJECT_PREFIX)) {
                    String name = inputParam.substring(OBJECT_PREFIX.length());
                    ObjectElement element = getObjectElement(name);
                    ArrayElement arrayElement = getArrayElement(name);
                    if (element == null && arrayElement == null) {
                        showErrorMessage("Object not found: " + name);
                        return;
                    }
                    if (element != null)
                        paramData[i] = element.getObject();
                    if (arrayElement != null)
                        paramData[i] = arrayElement.getObject();
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
            }

            Object result;
            try {
                result = intMethod.getMethod().invoke(intMethod.getObject(),
                        paramData);
            } catch (IllegalAccessException e1) {
                showErrorMessage("IllegalAccessException");
                return;
            } catch (IllegalArgumentException e1) {
                showErrorMessage("IllegalArgumentException");
                return;
            } catch (InvocationTargetException e1) {
                showErrorMessage("Exception cought: "
                        + System.getProperty("line.separator") + e1.getCause());
                return;
            }
            if (result == null)
                result = "(none)";
            if (intMethod.getMethod().getReturnType().equals(Void.TYPE))
                result = "(void)";
            invokeParamsField.setText("");
            JOptionPane.showMessageDialog(InterpretWindow.this, "Result: "
                    + result);
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
                    String text = invokeParamsField.getText();
                    if (text.length() == 0)
                        invokeParamsField.setText("=" + s);
                    else
                        invokeParamsField.setText(text + ",=" + s);
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

    private class NewArrayActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String value = JOptionPane.showInputDialog(InterpretWindow.this,
                    "Input class:", "java.lang.Integer");
            if (value != null) {
                if (value.equals("byte")) {
                    new CreateArrayWindow(InterpretWindow.this, byte.class);
                } else if (value.equals("short")) {
                    new CreateArrayWindow(InterpretWindow.this, short.class);
                } else if (value.equals("int")) {
                    new CreateArrayWindow(InterpretWindow.this, int.class);
                } else if (value.equals("long")) {
                    new CreateArrayWindow(InterpretWindow.this, long.class);
                } else if (value.equals("float")) {
                    new CreateArrayWindow(InterpretWindow.this, float.class);
                } else if (value.equals("double")) {
                    new CreateArrayWindow(InterpretWindow.this, double.class);
                } else if (value.equals("char")) {
                    new CreateArrayWindow(InterpretWindow.this, char.class);
                } else if (value.equals("boolean")) {
                    new CreateArrayWindow(InterpretWindow.this, boolean.class);
                } else {
                    try {
                        new CreateArrayWindow(InterpretWindow.this,
                                Class.forName(value));
                    } catch (ClassNotFoundException cnfe) {
                        JOptionPane.showMessageDialog(InterpretWindow.this,
                                "ClassNotFoundException", "ERROR",
                                JOptionPane.ERROR_MESSAGE);
                    }
                }

            }
        }
    }

    /** Tree selection listener of array object tree */
    public class ArrayObjectSelectionListener implements TreeSelectionListener {
        @Override
        public void valueChanged(TreeSelectionEvent e) {
            DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode) arrayTree
                    .getLastSelectedPathComponent();
            // Not choice
            if (selectedNode == null) {
                arrayCellListModel.clear();
                arrayCellList.setEnabled(false);
            }
            // Class choice
            else if (selectedNode.getParent().equals(arrayRootNode)) {
                arrayCellListModel.clear();
                arrayCellList.setEnabled(false);
            } else if (selectedNode.getParent().getParent()
                    .equals(arrayRootNode)) {
                ArrayElement element = getArrayElement(selectedNode.toString());
                if (element == null) {
                    showErrorMessage("FATAL: ArrayElement is null!");
                    return;
                }
                // Load members
                arrayCellListModel.clear();
                for (int i = 0; i < element.length(); i++) {
                    arrayCellListModel
                            .addElement(element.getObjectElementAt(i));
                }
                arrayCellList.setEnabled(true);
                pack();
                setLocationRelativeTo(null);
            }
        }
    }

    /** List selection listener of cell list */
    private class ArrayCellSelectionListener implements ListSelectionListener {

        @Override
        public void valueChanged(ListSelectionEvent e) {
            ObjectElement objectElement = arrayCellList.getSelectedValue();
            fieldListModel.clear();
            fieldList.setEnabled(false);
            changeFieldButton.setEnabled(false);
            valueLabel.setText(NOT_SELECTED);
            methodListModel.clear();
            methodList.setEnabled(false);
            invokeParamsField.setEnabled(false);
            invokeMethodButton.setEnabled(false);
            insertNewButton.setEnabled(false);
            if (objectElement != null) {
                insertNewButton.setEnabled(true);
                if (objectElement.getObject() != null) {
                    // Load fields
                    fieldListModel.clear();
                    for (InterpretField f : getAllFields(objectElement))
                        fieldListModel.addElement(f);
                    fieldList.setEnabled(true);
                    // Load methods
                    methodListModel.clear();
                    for (InterpretMethod m : getAllMethods(objectElement))
                        methodListModel.addElement(m);
                    methodList.setEnabled(true);
                    pack();
                    setLocationRelativeTo(null);
                }
            }
        }
    }

    private class InsertNewActionListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode) arrayTree
                    .getLastSelectedPathComponent();
            if (selectedNode != null
                    && selectedNode.getParent().getParent()
                            .equals(arrayRootNode)) {
                String name = selectedNode.toString();
                int index = arrayCellList.getSelectedIndex();
                System.out.println("" + name + " " + index);
                String className = selectedNode.getParent().toString();
                if (className.equals("byte")) {
                    new InstantiateWindow(InterpretWindow.this, Byte.class,
                            name, index);
                } else if (className.equals("short")) {
                    new InstantiateWindow(InterpretWindow.this, Short.class,
                            name, index);
                } else if (className.equals("int")) {
                    new InstantiateWindow(InterpretWindow.this, Integer.class,
                            name, index);
                } else if (className.equals("long")) {
                    new InstantiateWindow(InterpretWindow.this, Long.class,
                            name, index);
                } else if (className.equals("float")) {
                    new InstantiateWindow(InterpretWindow.this, Float.class,
                            name, index);
                } else if (className.equals("double")) {
                    new InstantiateWindow(InterpretWindow.this, Double.class,
                            name, index);
                } else if (className.equals("char")) {
                    new InstantiateWindow(InterpretWindow.this,
                            Character.class, name, index);
                } else if (className.equals("boolean")) {
                    new InstantiateWindow(InterpretWindow.this, Boolean.class,
                            name, index);
                } else {
                    try {
                        new InstantiateWindow(InterpretWindow.this,
                                Class.forName(className), name, index);
                    } catch (ClassNotFoundException e1) {
                        showErrorMessage("FATAL: ClassNotFoundException");
                        return;
                    }
                }
            }
        }
    }
}
