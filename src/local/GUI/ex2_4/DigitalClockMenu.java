/*
 * Copyright(C) 2014 Yutaka Kato
 */
package local.GUI.ex2_4;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.UnsupportedLookAndFeelException;

@SuppressWarnings("serial")
public class DigitalClockMenu extends JMenuBar {

    private JFrame frame;
    private DigitalClockPanel panel;
    private JMenuItem propertiesMenu;
    private JMenuItem aboutMenu;

    public DigitalClockMenu(JFrame frame, DigitalClockPanel panel) {
        
        this.frame = frame;
        this.panel = panel;

        MenuActionListener listener = new MenuActionListener();

        // [View]
        JMenu viewMenu = new JMenu("View");
        viewMenu.setMnemonic('V');
        add(viewMenu);
        
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

        // [View] -> [Properties...]
        propertiesMenu = new JMenuItem("Properties...");
        propertiesMenu.setMnemonic('P');
        propertiesMenu.addActionListener(listener);
        viewMenu.add(propertiesMenu);

        // [Help]
        JMenu helpMenu = new JMenu("Help");
        viewMenu.setMnemonic('H');
        add(helpMenu);
        
        // [Help] -> [About...]
        aboutMenu = new JMenuItem("About...");
        aboutMenu.setMnemonic('A');
        aboutMenu.addActionListener(listener);
        helpMenu.add(aboutMenu);
    }

    private class MenuActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            Object source = e.getSource();
            if (source.equals(propertiesMenu)) {
                new PropertiesDialog(frame, panel);
            } else if (source.equals(aboutMenu)) {
                new AboutDialog(frame);
            }
        }
    }
    
    private class LFActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if (((JMenuItem) e.getSource()).getText()
                        .equals(info.getName())) {
                    try {
                        UIManager.setLookAndFeel(info.getClassName());
                    } catch (ClassNotFoundException | InstantiationException
                            | IllegalAccessException
                            | UnsupportedLookAndFeelException e1) {
                        e1.printStackTrace();
                    }
                    SwingUtilities.updateComponentTreeUI(frame);
                    frame.pack();
                    break;
                }
            }
        }
    }
}
