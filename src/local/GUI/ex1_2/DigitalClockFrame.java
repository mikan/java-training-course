/*
 * Copyright(C) 2013 Yutaka Kato
 */
package local.GUI.ex1_2;

import java.awt.BorderLayout;
import java.awt.Dialog;
import java.awt.Frame;
import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.MenuItem;
import java.awt.MenuShortcut;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * AWT Frame of the digital clock.
 */
@SuppressWarnings("serial")
public class DigitalClockFrame extends Frame implements ActionListener {

    private DigitalClockCanvas canvas;
    private MenuItem changeFontMenu;
    private MenuItem changeFontSizeMenu;
    private MenuItem changeFontColorMenu;
    private MenuItem changeBackgroundColorMenu;
    private MenuItem aboutMenu;

    public DigitalClockFrame(String title, DigitalClockCanvas canvas) {
        super(title);
        this.canvas = canvas;

        // Frame configuration
        setLayout(new BorderLayout());
        addWindowListener(new ApplicationCloseAdapter());
        setResizable(false);
        setIgnoreRepaint(true);
        add(canvas, BorderLayout.CENTER);

        // Menu configuration
        MenuBar menuBar = new MenuBar();
        setMenuBar(menuBar);
        // [View]
        Menu viewMenu = new Menu("View");
        viewMenu.setShortcut(new MenuShortcut('V'));
        menuBar.add(viewMenu);
        // [View] -> [Change font...]
        changeFontMenu = new MenuItem("Change font...", new MenuShortcut('F'));
        changeFontMenu.addActionListener(this);
        viewMenu.add(changeFontMenu);
        // [View] -> [Change font size...]
        changeFontSizeMenu = new MenuItem("Change font size...",
                new MenuShortcut('S'));
        changeFontSizeMenu.addActionListener(this);
        viewMenu.add(changeFontSizeMenu);
        // [View] -> [Change font color...]
        changeFontColorMenu = new MenuItem("Change font color...",
                new MenuShortcut('C'));
        changeFontColorMenu.addActionListener(this);
        viewMenu.add(changeFontColorMenu);
        // [View] -> [Change background color...]
        changeBackgroundColorMenu = new MenuItem("Change background color...",
                new MenuShortcut('B'));
        changeBackgroundColorMenu.addActionListener(this);
        viewMenu.add(changeBackgroundColorMenu);
        // [Help]
        Menu helpMenu = new Menu("Help");
        helpMenu.setShortcut(new MenuShortcut('H'));
        menuBar.add(helpMenu);
        // [Help] -> [About...]
        aboutMenu = new MenuItem("About...", new MenuShortcut('A'));
        aboutMenu.addActionListener(this);
        helpMenu.add(aboutMenu);

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (source.equals(changeFontMenu)) {
            new ChangeFontDialog(this, canvas);
        } else if (source.equals(changeFontSizeMenu)) {
            new ChangeFontSizeDialog(this, canvas);
        } else if (source.equals(changeFontColorMenu)) {
            new ChangeFontColorDialog(this, canvas);
        } else if (source.equals(changeBackgroundColorMenu)) {
            new ChangeBackgroundColorDialog(this, canvas);
        } else if (source.equals(aboutMenu)) {
            new AboutDialog(this);
        }
    }

    void resize() {
        pack();
    }
}

/**
 * Window adapter for the application closing.
 */
class ApplicationCloseAdapter extends WindowAdapter {
    @Override
    public void windowClosing(WindowEvent e) {
        System.exit(0);
    }
}

/**
 * Window adapter for the dialog closing.
 */
class DialogCloseAdapter extends WindowAdapter {
    Dialog dialog;

    public DialogCloseAdapter(Dialog dialog) {
        this.dialog = dialog;
    }

    @Override
    public void windowClosing(WindowEvent e) {
        dialog.setVisible(false);
    }
}