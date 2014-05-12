/*
 * Copyright(C) 2013-2014 Yutaka Kato
 */
package local.GUI.ex1_3;

import java.awt.AWTException;
import java.awt.BorderLayout;
import java.awt.Dialog;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.Menu;
import java.awt.MenuItem;
import java.awt.MenuShortcut;
import java.awt.PopupMenu;
import java.awt.SystemTray;
import java.awt.TrayIcon;
import java.awt.TrayIcon.MessageType;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * AWT Frame of the digital clock.
 */
@SuppressWarnings("serial")
public class DigitalClockWindow extends Window implements ActionListener {

    private DigitalClockCanvas canvas;
    private MenuItem aboutMenu;
    private MenuItem exitMenu, trayExitMenu;
    private int pressedX;
    private int pressedY;

    // CSS3 base 16 colors
    private static final String[] COLORS = new String[] { "black", "silver",
            "gray", "white", "maroon", "red", "purple", "fuchsia", "green",
            "lime", "olive", "yellow", "navy", "blue", "teal", "aqua" };

    public DigitalClockWindow(String title, DigitalClockCanvas canvas) {
        super(null, null);
        this.canvas = canvas;

        // Frame configuration
        setLayout(new BorderLayout());
        addWindowListener(new ApplicationCloseAdapter());
        setIgnoreRepaint(true);
        add(canvas, BorderLayout.CENTER);
        setAlwaysOnTop(true);

        // Menu configuration
        PopupMenu popupMenu = new PopupMenu(title);

        // [Font]
        Menu fontMenu = new Menu("Font");
        popupMenu.add(fontMenu);

        // [Font] -> (fonts...)
        GraphicsEnvironment ge = GraphicsEnvironment
                .getLocalGraphicsEnvironment();
        for (Font f : ge.getAllFonts()) {
            MenuItem item = new MenuItem(f.getName());
            item.addActionListener(new ChangeFontActionListener(this, canvas));
            fontMenu.add(item);
        }

        // [Font size]
        Menu fontSizeMenu = new Menu("Font size");
        popupMenu.add(fontSizeMenu);

        // [Font size] -> (size...)
        ChangeFontSizeActionListener fontSizeListener = new ChangeFontSizeActionListener(
                this, canvas);
        for (int i = 10; i <= 250; i++) {
            if (i % 5 != 0)
                continue;
            MenuItem item = new MenuItem(Integer.toString(i));
            item.addActionListener(fontSizeListener);
            fontSizeMenu.add(item);
        }

        // [Font color]
        Menu fontColorMenu = new Menu("Font color");
        popupMenu.add(fontColorMenu);

        // [Font color] -> (colors...)
        for (String s : COLORS) {
            MenuItem item = new MenuItem(s);
            item.addActionListener(new ChangeFontColorActionListener(canvas));
            fontColorMenu.add(item);
        }

        // [Background color]
        Menu backgroundColorMenu = new Menu("Background color");
        popupMenu.add(backgroundColorMenu);

        // [Background color] -> (colors...)
        for (String s : COLORS) {
            MenuItem item = new MenuItem(s);
            item.addActionListener(new ChangeBackgroundColorActionListener(
                    canvas));
            backgroundColorMenu.add(item);
        }

        // [About...]
        aboutMenu = new MenuItem("About...", new MenuShortcut('A'));
        aboutMenu.addActionListener(this);
        popupMenu.add(aboutMenu);

        // [Exit]
        exitMenu = new MenuItem("Exit", new MenuShortcut('E'));
        exitMenu.addActionListener(this);
        popupMenu.add(exitMenu);

        pack();
        canvas.add(popupMenu);
        canvas.addMouseListener(new ClockMouseAdapter(popupMenu));
        canvas.addMouseMotionListener(new ClockMouseMotionAdapter());
        setLocationRelativeTo(null);
        setVisible(true);

        // Task tray
        try {
            Image image = ImageIO.read(this.getClass().getResourceAsStream("icon.png"));
            TrayIcon icon = new TrayIcon(image);
            PopupMenu trayPopupMenu = new PopupMenu(title);
            trayExitMenu = new MenuItem("Exit", new MenuShortcut('E'));
            trayExitMenu.addActionListener(this);
            trayPopupMenu.add(trayExitMenu);
            icon.setPopupMenu(trayPopupMenu);
            icon.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    new AboutDialog(null);
                }
            });
            SystemTray.getSystemTray().add(icon);
            icon.displayMessage("DigitalClock", "ここからも時計を終了できます.", MessageType.INFO);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (AWTException e1) {
            e1.printStackTrace();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (source.equals(exitMenu) || source.equals(trayExitMenu)) {
            System.out.println();
            System.exit(0);
        } else if (source.equals(aboutMenu)) {
            new AboutDialog(null);
        }
    }

    void resize() {
        pack();
    }

    /**
     * Mouse adapter for pop-up menu and D&D.
     */
    class ClockMouseAdapter extends MouseAdapter {

        PopupMenu popupMenu;

        public ClockMouseAdapter(PopupMenu popupMenu) {
            this.popupMenu = popupMenu;
        }

        @Override
        public void mousePressed(MouseEvent e) {
            pressedX = e.getX();
            pressedY = e.getY();
            if (e.isPopupTrigger())
                popupMenu.show(canvas, e.getX(), e.getY());
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            mousePressed(e);
        }
    }

    class ClockMouseMotionAdapter extends MouseMotionAdapter {

        @Override
        public void mouseDragged(MouseEvent e) {
            setLocation(getX() + e.getX() - pressedX, getY() + e.getY()
                    - pressedY);
        }
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