/*
 * Copyright(C) 2013, 2014 Yutaka Kato
 */
package local.GUI.ex2_3;

import java.awt.BorderLayout;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JPopupMenu;
import javax.swing.JWindow;

/** Display window of the digital clock. */
@SuppressWarnings("serial")
public class DigitalClockFrame extends JWindow {

    private int pressedX;
    private int pressedY;
    private DigitalClockPanel panel;

    public DigitalClockFrame(String title) {
	// super(title);
	setLayout(new BorderLayout());
	panel = new DigitalClockPanel();
	JPopupMenu popupMenu = new DigitalClockPopupMenu(this, panel);
	panel.add(popupMenu);
	panel.addMouseListener(new ClockMouseAdapter(popupMenu));
	panel.addMouseMotionListener(new ClockMouseMotionAdapter());
	add(panel, BorderLayout.CENTER);
	// setJMenuBar(new DigitalClockMenu(this, panel));
	addWindowListener(new ApplicationCloseAdapter());
	// setResizable(false);
	Point location = DigitalClock.getConfiguration().getLocation();
	if (location == null)
	    setLocationRelativeTo(null);
	else
	    setLocation(location);
	pack();
	setVisible(true);
	new RepaintThread(panel, 3).start();
    }

    /**
     * Window adapter for the application closing.
     */
    private class ApplicationCloseAdapter extends WindowAdapter {

	@Override
	public void windowClosing(WindowEvent e) {
	    DigitalClock.getConfiguration().update(getLocation(), getWidth(), getHeight());
	    System.exit(0);
	}
    }

    /**
     * Mouse adapter for pop-up menu and D&D.
     */
    private class ClockMouseAdapter extends MouseAdapter {

	final JPopupMenu popupMenu;

	public ClockMouseAdapter(JPopupMenu popupMenu) {
	    this.popupMenu = popupMenu;
	}

	@Override
	public void mousePressed(MouseEvent e) {
	    pressedX = e.getX();
	    pressedY = e.getY();
	    if (e.isPopupTrigger())
		popupMenu.show(panel, e.getX(), e.getY());
	}

	@Override
	public void mouseReleased(MouseEvent e) {
	    mousePressed(e);
	}
    }

    /**
     * Mouse motion adapter for D&D handling.
     */
    private class ClockMouseMotionAdapter extends MouseMotionAdapter {

	@Override
	public void mouseDragged(MouseEvent e) {
	    setLocation(getX() + e.getX() - pressedX, getY() + e.getY() - pressedY);
	}
    }
}