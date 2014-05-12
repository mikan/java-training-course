/*
 * Copyright(C) 2013 Yutaka Kato
 */
package local.GUI.ex1_4;

import java.awt.BorderLayout;
import java.awt.Dialog;
import java.awt.Frame;
import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.MenuItem;
import java.awt.MenuShortcut;
import java.awt.Point;
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
    private MenuItem propertiesMenu;
    private MenuItem aboutMenu;

    public DigitalClockFrame(String title, DigitalClockCanvas canvas) {
        super(title);
        this.canvas = canvas;

        // Frame configuration
        setLayout(new BorderLayout());
        addWindowListener(new ApplicationCloseAdapter(this));
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
        // [View] -> [Properties...]
        propertiesMenu = new MenuItem("Properties...", new MenuShortcut('P'));
        propertiesMenu.addActionListener(this);
        viewMenu.add(propertiesMenu);
        // [Help]
        Menu helpMenu = new Menu("Help");
        helpMenu.setShortcut(new MenuShortcut('H'));
        menuBar.add(helpMenu);
        // [Help] -> [About...]
        aboutMenu = new MenuItem("About...", new MenuShortcut('A'));
        aboutMenu.addActionListener(this);
        helpMenu.add(aboutMenu);

        pack();
        Point location = DigitalClock.getConfiguration().getLocation();
        if (location == null)
            setLocationRelativeTo(null);
        else
            setLocation(location);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (source.equals(propertiesMenu)) {
            new PropertiesDialog(this, canvas);
        } else if (source.equals(aboutMenu)) {
            new AboutDialog(this);
        }
    }
}

/**
 * Window adapter for the application closing.
 */
class ApplicationCloseAdapter extends WindowAdapter {
    private Frame frame;

    public ApplicationCloseAdapter(Frame frame) {
        this.frame = frame;
    }

    @Override
    public void windowClosing(WindowEvent e) {
        DigitalClock.getConfiguration().update(frame.getLocation(),
                frame.getWidth(), frame.getHeight());
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