package local.GUI.ex2_4;

import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

@SuppressWarnings("serial")
public class DigitalClockPopupMenu extends JPopupMenu {

    private JMenuItem aboutMenu;
    private JMenuItem exitMenu;

    public DigitalClockPopupMenu(JFrame frame, DigitalClockPanel panel) {

        PopupMenuActionListener listener = new PopupMenuActionListener();

        // [Font]
        JMenu fontMenu = new JMenu("Font");
        add(fontMenu);

        // [Font] -> (fonts...)
        GraphicsEnvironment ge = GraphicsEnvironment
                .getLocalGraphicsEnvironment();
        for (Font f : ge.getAllFonts()) {
            JMenuItem item = new JMenu(f.getName());
            item.addActionListener(new ChangeFontListener(frame, panel));
            fontMenu.add(item);
        }

        // [Font size]
        JMenu fontSizeMenu = new JMenu("Font size");
        add(fontSizeMenu);

        // [Font size] -> (size...)
        ChangeFontSizeListener fontSizeListener = new ChangeFontSizeListener(
                frame, panel);
        for (int i = 10; i <= 250; i++) {
            if (i % 5 != 0)
                continue;
            JMenuItem item = new JMenuItem(Integer.toString(i));
            item.addActionListener(fontSizeListener);
            fontSizeMenu.add(item);
        }

        // [Font color]
        JMenu fontColorMenu = new JMenu("Font color");
        add(fontColorMenu);

        // [Font color] -> (colors...)
        for (DisplayColor c : DisplayColor.values()) {
            JMenuItem item = new JMenuItem(c.toString());
            item.addActionListener(new ChangeForegroundListener(panel));
            fontColorMenu.add(item);
        }

        // [Background color]
        JMenu backgroundColorMenu = new JMenu("Background color");
        add(backgroundColorMenu);

        // [Background color] -> (colors...)
        for (DisplayColor c : DisplayColor.values()) {
            JMenuItem item = new JMenuItem(c.toString());
            item.addActionListener(new ChangeBackgroundListener(panel));
            backgroundColorMenu.add(item);
        }

        // [About...]
        aboutMenu = new JMenuItem("About...");
        aboutMenu.setMnemonic('A');
        aboutMenu.addActionListener(listener);
        add(aboutMenu);

        // [Exit]
        exitMenu = new JMenuItem("Exit");
        exitMenu.setMnemonic('E');
        exitMenu.addActionListener(listener);
        add(exitMenu);
    }

    private class PopupMenuActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            Object source = e.getSource();
            if (source.equals(exitMenu)) {
                System.out.println();
                System.exit(0);
            } else if (source.equals(aboutMenu)) {
                new AboutDialog(null);
            }
        }
    }
}
