/*
 * Copyright(C) 2014 Yutaka Kato
 */
package local.Interpret;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DropTarget;
import java.awt.dnd.DropTargetDropEvent;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import javax.swing.DefaultListModel;
import javax.swing.DropMode;
import javax.swing.JButton;
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
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

@SuppressWarnings("serial")
public class InterpretWindow extends AbstractWindow {

    static final String OBJECT_PREFIX = "=";
    private static final String BR = System.getProperty("line.separator");
    private static final String DEFAULT_INSTANCE_TYPE = "java.awt.Frame";
    private static final String DEFAULT_ARRAY_TYPE = "java.lang.String";
    private static final String NOT_SELECTED = "(not selected)";
    private static final String TOOLTIP_DRAGGABLE = "Each element is draggable to the parameter(s).";
    private static final String TOOLTIP_ANNOTATION = "Show annotations if available.";
    private List<InterpretObject> objects;
    private final JLabel objectsLabel;
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
    private final JButton showFieldAnnotationButton;
    private final JButton showMethodAnnotationButton;
    private final JTextField invokeParamsField;
    private boolean showArrayArea = false;
    private List<InterpretArray> arrays;
    private JLabel arraysLabel;
    private JTree arrayTree;
    private DefaultTreeModel arrayTreeModel;
    private DefaultMutableTreeNode arrayRootNode;
    private JLabel arrayCellLabel;
    private JList<InterpretObject> arrayCellList;
    private DefaultListModel<InterpretObject> arrayCellListModel;
    private JButton insertNewButton;
    private JMenuItem arrayMenuItem;
    private Dimension treePreferredSize;
    private JLabel cellIsNullLabel;

    /** Create and show the interpret window. */
    public InterpretWindow() {

        objects = new ArrayList<InterpretObject>();
        arrays = new ArrayList<InterpretArray>();
        treePreferredSize = new Dimension(100, 100);

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

        // Menu [View]
        JMenu viewMenu = new JMenu("View");
        viewMenu.setMnemonic('V');
        menuBar.add(viewMenu);

        // Menu [View] -> [Show array area]
        arrayMenuItem = new JMenuItem("Show array area");
        arrayMenuItem.setMnemonic('S');
        arrayMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                showArrayArea();
            }
        });
        viewMenu.add(arrayMenuItem);

        // Menu [View] -> [Look & Feel]
        JMenu lfMenu = new JMenu("Look & Feel");
        lfMenu.setMnemonic('L');
        viewMenu.add(lfMenu);
        ActionListener lfActionListener = new LFActionListener();
        for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
            JMenuItem item = new JMenuItem(info.getName());
            item.addActionListener(lfActionListener);
            lfMenu.add(item);
        }

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
        objectTree.addMouseListener(new ObjectMouseAdapter());
        objectTree.setRootVisible(false);
        objectTree.setSize(300, 300);
        objectTree.setDragEnabled(true);
        objectTree.setToolTipText(TOOLTIP_DRAGGABLE);
        objectTreeModel = (DefaultTreeModel) objectTree.getModel();
        objectsLabel = new JLabel("Objects            ");
        addGrid(objectsLabel, 1, 1);
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
        showFieldAnnotationButton = new JButton("Show annotations...");
        showFieldAnnotationButton
                .addActionListener(new ShowAnnotationsActionListener());
        showFieldAnnotationButton.setEnabled(false);
        showFieldAnnotationButton.setToolTipText(TOOLTIP_ANNOTATION);
        fieldControlPanel.add(showFieldAnnotationButton);
        addGrid(fieldControlPanel, 3, 2);

        // Method control panel
        JPanel methodControlpanel = new JPanel();
        FlowLayout methodControlPanelLayout = new FlowLayout();
        methodControlPanelLayout.setAlignment(FlowLayout.LEFT);
        methodControlpanel.setPreferredSize(new Dimension(180, 100));
        methodControlpanel.setLayout(methodControlPanelLayout);
        methodControlpanel.add(new JLabel("Parameter(s):"));
        invokeParamsField = new JTextField();
        invokeParamsField.setPreferredSize(new Dimension(150, 20));
        invokeParamsField.setDropMode(DropMode.INSERT);
        invokeParamsField.setDropTarget(new ObjectDropTarget());
        invokeParamsField.addActionListener(new TextFieldActionListener());
        invokeParamsField
                .setToolTipText("[ex1] null  [ex2] abc,123  [ex3] =(object name)");
        methodControlpanel.add(invokeParamsField);
        JLabel ex1 = new JLabel("ex1) null              ");
        JLabel ex2 = new JLabel("ex2) abc,123           ");
        JLabel ex3 = new JLabel("ex3) =(object name)    ");
        ex1.setForeground(Color.gray);
        ex2.setForeground(Color.gray);
        ex3.setForeground(Color.gray);
        methodControlpanel.add(ex1);
        methodControlpanel.add(ex2);
        methodControlpanel.add(ex3);
        invokeMethodButton = new JButton("Invoke");
        invokeMethodButton.setEnabled(false);
        invokeMethodButton.addActionListener(new InvokeMethodActionListener());
        methodControlpanel.add(invokeMethodButton);
        showMethodAnnotationButton = new JButton("Show annotations...");
        showMethodAnnotationButton
                .addActionListener(new ShowAnnotationsActionListener());
        showMethodAnnotationButton.setEnabled(false);
        showMethodAnnotationButton.setToolTipText(TOOLTIP_ANNOTATION);
        methodControlpanel.add(showMethodAnnotationButton);
        addGrid(methodControlpanel, 3, 4);

        // Window
        getRootPane().setJMenuBar(menuBar);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("INTERPRET");
        setResizable(false);

        setLF();
        pack();
        setSize(getPreferredSize());
        setLocationRelativeTo(null);
        setVisible(true);
    }

    /** Create and show array area. */
    private void showArrayArea() {
        if (showArrayArea)
            return;

        // Array tree
        arraysLabel = new JLabel("Arrays");
        addGrid(arraysLabel, 1, 5);
        arrayRootNode = new DefaultMutableTreeNode("Arrays");
        arrayTree = new JTree(arrayRootNode);
        arrayTree.setRootVisible(false);
        arrayTree.addTreeSelectionListener(new ArrayObjectSelectionListener());
        arrayTree.addMouseListener(new ArrayMouseAdapter());
        arrayTree.setDragEnabled(true);
        arrayTree.setPreferredSize(treePreferredSize);
        arrayTree.setToolTipText(TOOLTIP_DRAGGABLE);
        arrayTreeModel = (DefaultTreeModel) arrayTree.getModel();
        JScrollPane arrayTreeScroll = new JScrollPane(arrayTree);
        arrayTreeScroll.setPreferredSize(treePreferredSize);
        addGrid(arrayTreeScroll, 1, 6);

        // Array cell list
        arrayCellLabel = new JLabel("Cells");
        addGrid(arrayCellLabel, 2, 5);
        arrayCellListModel = new DefaultListModel<>();
        arrayCellList = new JList<>();
        arrayCellList.setModel(arrayCellListModel);
        arrayCellList
                .addListSelectionListener(new ArrayCellSelectionListener());
        arrayCellList.setDragEnabled(true);
        arrayCellList.setToolTipText(TOOLTIP_DRAGGABLE);
        addGrid(new JScrollPane(arrayCellList), 2, 6);

        // Array cell control panel
        JPanel arrayCellControlPanel = new JPanel();
        FlowLayout arrayCellControlPanelLayout = new FlowLayout();
        arrayCellControlPanelLayout.setAlignment(FlowLayout.LEFT);
        arrayCellControlPanel.setPreferredSize(new Dimension(180, 100));
        arrayCellControlPanel.setLayout(arrayCellControlPanelLayout);
        cellIsNullLabel = new JLabel("");
        cellIsNullLabel.setForeground(Color.red);
        arrayCellControlPanel.add(cellIsNullLabel);
        addGrid(arrayCellControlPanel, 3, 6);
        insertNewButton = new JButton("Insert new...");
        insertNewButton.setEnabled(false);
        insertNewButton.addActionListener(new InsertNewActionListener());
        arrayCellControlPanel.add(insertNewButton);
        pack();
        arrayMenuItem.setEnabled(false);
        showArrayArea = true;
    }

    /**
     * Add object to this window.
     * 
     * @param cls Class
     * @param instance Instance of specified class
     * @param name Name of object
     */
    void addObject(Class<?> cls, Object instance, String name) {
        objects.add(new InterpretObject(instance, name));
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
     * Add array object to this window.
     * 
     * @param cls Class
     * @param instance Instance of specified class
     * @param name Name of array
     * @param length
     */
    void addArray(Class<?> cls, Object instance, String name, int length) {
        showArrayArea();
        arrays.add(new InterpretArray(instance, name, length));
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

    /**
     * Add cell object to array area of this window.
     * 
     * @param instance Instance
     * @param name Name Name of array
     * @param index Index of array
     */
    void addArrayCell(Object instance, String name, int index) {
        InterpretArray element = getArrayElement(name);
        element.setObjectElementAt(index, instance);
        arrayCellListModel.setElementAt(new InterpretObject(instance, name
                + "[" + index + "]"), index);
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
    InterpretObject getObjectElement(String name) {
        for (InterpretObject e : objects)
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
    InterpretArray getArrayElement(String name) {
        for (InterpretArray e : arrays)
            if (e.getName().equals(name))
                return e;
        return null;
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

    /**
     * Get string list of parameters from text field.
     * 
     * @param Text field
     * @return List of parameters, or empty list if text field is empty.
     */
    private List<String> getParameters(JTextField textField) {
        List<String> list = new ArrayList<>();
        if (textField.getText() == null)
            return list;
        StringTokenizer token = new StringTokenizer(textField.getText(), ",");
        while (token.hasMoreTokens()) {
            list.add(token.nextToken());
        }
        return list;
    }

    /** Invoke selected method. */
    private void invoke() {
        InterpretMethod method = methodList.getSelectedValue();
        if (method == null) {
            showErrorMessage("Method not selected.");
            return;
        }

        // Load parameters
        List<String> inputParams = getParameters(invokeParamsField);
        Class<?>[] params = method.getParameterTypes();
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
                if (name.contains("[") && name.endsWith("]")) {
                    String arrayName = name.substring(0, name.indexOf("["));
                    String indexStr = name.substring(name.indexOf("[") + 1,
                            name.lastIndexOf("]"));
                    InterpretArray arrayElement = getArrayElement(arrayName);
                    if (arrayElement == null) {
                        showErrorMessage("Array not found: " + name);
                        return;
                    }
                    try {
                        int index = Integer.parseInt(indexStr);
                        paramData[i] = arrayElement.getObjectAt(index);
                    } catch (NumberFormatException ex) {
                        showErrorMessage("Illegal index: " + indexStr);
                        return;
                    }
                } else {
                    InterpretObject element = getObjectElement(name);
                    InterpretArray arrayElement = getArrayElement(name);
                    if (element == null && arrayElement == null) {
                        showErrorMessage("Object not found: " + name);
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
                            paramData[i] = (char) Integer.parseInt(inputParam);
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
            result = method.invoke(paramData);
        } catch (IllegalAccessException e1) {
            showErrorMessage("IllegalAccessException");
            return;
        } catch (IllegalArgumentException e1) {
            showErrorMessage("IllegalArgumentException");
            return;
        } catch (InvocationTargetException e1) {
            showErrorMessage("Exception cought: " + BR + e1.getCause());
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
        if (result == null)
            result = "(none)";
        if (method.isReturnVoid())
            result = "(void)";
        invokeParamsField.setText("");
        JOptionPane
                .showMessageDialog(InterpretWindow.this, "Result: " + result);
    }

    private void updateCurrentSelectedPane(JLabel label) {
        Font font = objectsLabel.getFont();
        Font plain = new Font(font.getName(), Font.PLAIN, font.getSize());
        Font bold = new Font(font.getName(), Font.BOLD, font.getSize());
        if (label == null) {
            objectsLabel.setFont(plain);
            if (showArrayArea) {
                arraysLabel.setFont(plain);
                arrayCellLabel.setFont(plain);
            }
        } else if (label.equals(objectsLabel)) {
            objectsLabel.setFont(bold);
            if (showArrayArea) {
                arraysLabel.setFont(plain);
                arrayCellLabel.setFont(plain);
            }
        } else if (label.equals(arraysLabel)) {
            objectsLabel.setFont(plain);
            arraysLabel.setFont(bold);
            arrayCellLabel.setFont(plain);
        } else if (label.equals(arrayCellLabel)) {
            objectsLabel.setFont(plain);
            arraysLabel.setFont(plain);
            arrayCellLabel.setFont(bold);
        }
        pack();
    }

    /** Action listener of [New object...] menu item. */
    private class NewObjectActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String value = JOptionPane.showInputDialog(InterpretWindow.this,
                    "Input class or primitive type:", DEFAULT_INSTANCE_TYPE);
            if (value == null) {
                return;
            }
            if (value != null) {
                if (value.equals("byte")) {
                    new CreatePrimitiveWindow(InterpretWindow.this, Byte.TYPE);
                } else if (value.equals("short")) {
                    new CreatePrimitiveWindow(InterpretWindow.this, Short.TYPE);
                } else if (value.equals("int")) {
                    new CreatePrimitiveWindow(InterpretWindow.this,
                            Integer.TYPE);
                } else if (value.equals("long")) {
                    new CreatePrimitiveWindow(InterpretWindow.this, Long.TYPE);
                } else if (value.equals("float")) {
                    new CreatePrimitiveWindow(InterpretWindow.this, Float.TYPE);
                } else if (value.equals("double")) {
                    new CreatePrimitiveWindow(InterpretWindow.this, Double.TYPE);
                } else if (value.equals("char")) {
                    new CreatePrimitiveWindow(InterpretWindow.this,
                            Character.TYPE);
                } else if (value.equals("boolean")) {
                    new CreatePrimitiveWindow(InterpretWindow.this,
                            Boolean.TYPE);
                } else {
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

    /** Tree selection listener of object tree. */
    private class ObjectSelectionListener implements TreeSelectionListener {
        @Override
        public void valueChanged(TreeSelectionEvent e) {
            DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode) objectTree
                    .getLastSelectedPathComponent();
            updateCurrentSelectedPane(objectsLabel);
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
                InterpretObject element = getObjectElement(selectedNode
                        .toString());
                if (element == null) {
                    showErrorMessage("FATAL: ObjectElement is null!");
                    return;
                }
                // Load fields
                fieldListModel.clear();
                for (InterpretField f : element.getFields())
                    fieldListModel.addElement(f);
                fieldList.setEnabled(true);
                // Load methods
                methodListModel.clear();
                for (InterpretMethod m : element.getMethods())
                    methodListModel.addElement(m);
                methodList.setEnabled(true);
                pack();
                setLocationRelativeTo(null);
            }
        }
    }

    /** Mouse adapter (mouse listener) of object tree. */
    private class ObjectMouseAdapter extends MouseAdapter {
        @Override
        public void mouseClicked(MouseEvent arg0) {
            new ObjectSelectionListener().valueChanged(null);
        }
    }

    /** List selection listener of list of fields. */
    private class FieldSelectionListener implements ListSelectionListener {

        @Override
        public void valueChanged(ListSelectionEvent e) {
            InterpretField field = fieldList.getSelectedValue();
            if (field == null) {
                valueLabel.setText(NOT_SELECTED);
                valueLabel.setForeground(Color.black);
                changeFieldButton.setEnabled(false);
                showFieldAnnotationButton.setEnabled(false);
            } else {
                try {
                    Object value = field.getData();
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
                showFieldAnnotationButton.setEnabled(field.hasAnnotation());
                pack();
            }
        }
    }

    /** Action listener of [Change...] button. */
    private class ChangeFieldActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            InterpretField field = fieldList.getSelectedValue();
            if (field == null) {
                showErrorMessage("Field not selected.");
                return;
            }

            // Get field value
            Object value;
            try {
                value = field.getData();
            } catch (IllegalArgumentException e1) {
                showErrorMessage("IllegalArgumentException");
                return;
            } catch (IllegalAccessException e1) {
                showErrorMessage("IllegalAccessException");
                return;
            } catch (ExceptionInInitializerError e1) {
                showErrorMessage("ExceptionInInitializerError");
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
                    field.setData(newValue);
                    valueLabel.setText(field.getData().toString());
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
                } catch (ExceptionInInitializerError e1) {
                    showErrorMessage("ExceptionInInitializerError");
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
    }

    /** Selection listener of list of methods. */
    private class MethodSelectionListener implements ListSelectionListener {
        @Override
        public void valueChanged(ListSelectionEvent e) {
            InterpretMethod method = methodList.getSelectedValue();
            if (method == null) {
                invokeMethodButton.setEnabled(false);
                showMethodAnnotationButton.setEnabled(false);
            } else if (method.getParameterTypes().length == 0) {
                invokeMethodButton.setEnabled(true);
                invokeParamsField.setEnabled(false);
                showMethodAnnotationButton.setEnabled(method.hasAnnotation());
            } else {
                invokeMethodButton.setEnabled(true);
                invokeParamsField.setEnabled(true);
                showMethodAnnotationButton.setEnabled(method.hasAnnotation());
            }
        }
    }

    /** Action listener of [Invoke] button. */
    private class InvokeMethodActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            invoke();
        }
    }

    /** Action listener of [New array...] menu item. */
    private class NewArrayActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String value = JOptionPane.showInputDialog(InterpretWindow.this,
                    "Input class or primitive type:", DEFAULT_ARRAY_TYPE);
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

    /** Tree selection listener of array object tree. */
    private class ArrayObjectSelectionListener implements TreeSelectionListener {
        @Override
        public void valueChanged(TreeSelectionEvent e) {
            DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode) arrayTree
                    .getLastSelectedPathComponent();
            updateCurrentSelectedPane(arraysLabel);
            // Not choice
            if (selectedNode == null) {
                arrayCellListModel.clear();
                arrayCellList.setEnabled(false);
                fieldListModel.clear();
                fieldList.setEnabled(false);
                methodListModel.clear();
                methodList.setEnabled(false);
            }
            // Class choice
            else if (selectedNode.getParent().equals(arrayRootNode)) {
                arrayCellListModel.clear();
                arrayCellList.setEnabled(false);
                fieldListModel.clear();
                fieldList.setEnabled(false);
                methodListModel.clear();
                methodList.setEnabled(false);
            } else if (selectedNode.getParent().getParent()
                    .equals(arrayRootNode)) {
                InterpretArray element = getArrayElement(selectedNode
                        .toString());
                if (element == null) {
                    showErrorMessage("FATAL: ArrayElement is null!");
                    return;
                }
                // Load fields
                fieldListModel.clear();
                for (InterpretField f : element.getFields())
                    fieldListModel.addElement(f);
                fieldList.setEnabled(true);
                // Load methods
                methodListModel.clear();
                for (InterpretMethod m : element.getMethods())
                    methodListModel.addElement(m);
                methodList.setEnabled(true);

                // Load members
                arrayCellListModel.clear();
                for (int i = 0; i < element.length(); i++) {
                    arrayCellListModel
                            .addElement(element.getObjectElementAt(i));
                }
                arrayCellList.setEnabled(true);
                pack();
                // setLocationRelativeTo(null);
            }
        }
    }

    /** Mouse adapter (mouse listener) of array tree. */
    private class ArrayMouseAdapter extends MouseAdapter {
        @Override
        public void mouseClicked(MouseEvent e) {
            new ArrayObjectSelectionListener().valueChanged(null);
        }
    }

    /** List selection listener of list of cells. */
    private class ArrayCellSelectionListener implements ListSelectionListener {

        @Override
        public void valueChanged(ListSelectionEvent e) {
            updateCurrentSelectedPane(arrayCellLabel);
            InterpretObject element = arrayCellList.getSelectedValue();
            fieldListModel.clear();
            fieldList.setEnabled(false);
            changeFieldButton.setEnabled(false);
            valueLabel.setText(NOT_SELECTED);
            methodListModel.clear();
            methodList.setEnabled(false);
            invokeParamsField.setEnabled(false);
            invokeMethodButton.setEnabled(false);
            insertNewButton.setEnabled(false);
            cellIsNullLabel.setText("");
            if (element != null) {
                insertNewButton.setEnabled(true);
                if (element.getObject() != null) {
                    // Load fields
                    fieldListModel.clear();
                    for (InterpretField f : element.getFields())
                        fieldListModel.addElement(f);
                    fieldList.setEnabled(true);
                    // Load methods
                    methodListModel.clear();
                    for (InterpretMethod m : element.getMethods())
                        methodListModel.addElement(m);
                    methodList.setEnabled(true);
                    pack();
                    setLocationRelativeTo(null);
                } else {
                    cellIsNullLabel.setText("null");
                    pack();
                }
            } else {
            }
        }
    }

    /** Action listener of [Insert new...] button. */
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

    /** Action listener of [Show annotation...] buttons */
    private class ShowAnnotationsActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            StringBuilder builder = new StringBuilder();
            if (e.getSource().equals(showFieldAnnotationButton)) {
                InterpretField field = fieldList.getSelectedValue();
                if (field == null) {
                    showErrorMessage("Field not selected.");
                    return;
                }
                if (!field.hasAnnotation()) {
                    showErrorMessage("This field hasn't annotation.");
                    return;
                }
                for (String s : field.getAnnotations())
                    builder.append(s + BR);
                showInformationMessage(builder.toString(), field.getName());
            } else if (e.getSource().equals(showMethodAnnotationButton)) {
                InterpretMethod method = methodList.getSelectedValue();
                if (method == null) {
                    showErrorMessage("Method not selected.");
                    return;
                }
                if (!method.hasAnnotation()) {
                    showErrorMessage("This method hasn't annotation.");
                    return;
                }
                for (String s : method.getAnnotations())
                    builder.append(s + BR);
                showInformationMessage(builder.toString(), method.getName());
            }
        }
    }

    /** Drop target of parameter(s) text field. */
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

    /** Action (= Enter key is pressed) listener for text fields. */
    private class TextFieldActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource().equals(invokeParamsField))
                invoke();
        }
    }
}
