/*
 * Copyright(C) 2013-2014 Yutaka Kato
 */
package local.GUI.ex2_4;

import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class PropertiesDialog extends JDialog {

    private final JFrame owner;
    private final DigitalClockPanel panel;
    private JComboBox<String> fontComboBox;
    private JComboBox<String> fontSizeComboBox;
    private JComboBox<String> fontColorComboBox;
    private JComboBox<String> backgroundColorComboBox;
    private JButton okButton;
    private JButton cancelButton;
    private Point prevLocation;
    private Font prevFont;
    private DisplayColor prevForeground;
    private DisplayColor prevBackground;
    private static final int LEFT = 0;
    private static final int RIGHT = 1;
    private static final String DIALOG_TITLE = "Properties";
    private static final String LABEL_FONT = " Font ";
    private static final String LABEL_FONT_SIZE = " Font size ";
    private static final String LABEL_FONT_COLOR = " Font color ";
    private static final String LABEL_BACKGROUND_COLOR = " Background color ";

    public PropertiesDialog(JFrame owner, DigitalClockPanel panel) {
        super(owner);
        this.owner = owner;
        this.panel = panel;
        int row = 0;

        PropertiesDialogActionListener listener = new PropertiesDialogActionListener();

        prevLocation = owner.getLocation();
        prevFont = panel.getClockFont();
        try {
            prevForeground = DisplayColor.valueOf(panel.getForeground());
        } catch (IllegalArgumentException e) {
            prevForeground = DisplayColor.LIME;
        }
        try {
            prevBackground = DisplayColor.valueOf(panel.getBackground());
        } catch (IllegalArgumentException e) {
            prevBackground = DisplayColor.DARKGRAY;
        }
        setForeground(DisplayColor.DARKGRAY.getValue());
        setBackground(DisplayColor.SILVER.getValue());

        GridBagLayout layout = new GridBagLayout();
        GridBagConstraints constraints = new GridBagConstraints();

        // Font label
        JLabel fontLabel = new JLabel(LABEL_FONT);
        constraints.gridx = LEFT;
        constraints.gridy = row;
        constraints.anchor = GridBagConstraints.EAST;
        layout.setConstraints(fontLabel, constraints);
        add(fontLabel);

        // Font choice
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        Font[] fonts = ge.getAllFonts();
        fontComboBox = new JComboBox<>();
        for (Font f : fonts) {
            fontComboBox.addItem(f.getName());
            if (f.getName().equals(prevFont.getName()))
                fontComboBox.setSelectedItem(f.getName());
        }
        fontComboBox.addItemListener(new ChangeFontListener(owner, panel));
        constraints.gridx = RIGHT;
        constraints.gridy = row;
        constraints.anchor = GridBagConstraints.WEST;
        layout.setConstraints(fontComboBox, constraints);
        add(fontComboBox);

        row++;

        // Font size label
        JLabel fontSizeLabel = new JLabel(LABEL_FONT_SIZE);
        constraints.gridx = LEFT;
        constraints.gridy = row;
        constraints.anchor = GridBagConstraints.EAST;
        layout.setConstraints(fontSizeLabel, constraints);
        add(fontSizeLabel);

        // Font size choice
        fontSizeComboBox = new JComboBox<>();
        for (int i = 10; i <= 250; i++) {
            if (i % 5 != 0)
                continue;
            fontSizeComboBox.addItem(Integer.toString(i));
            if (i == prevFont.getSize())
                fontSizeComboBox.setSelectedItem(Integer.toString(i));
        }
        fontSizeComboBox.addItemListener(new ChangeFontSizeListener(owner, panel));
        constraints.gridx = RIGHT;
        constraints.gridy = row;
        constraints.anchor = GridBagConstraints.WEST;
        layout.setConstraints(fontSizeComboBox, constraints);
        add(fontSizeComboBox);
        row++;

        // Font color label
        JLabel fontColorLabel = new JLabel(LABEL_FONT_COLOR);
        constraints.gridx = LEFT;
        constraints.gridy = row;
        constraints.anchor = GridBagConstraints.EAST;
        layout.setConstraints(fontColorLabel, constraints);
        add(fontColorLabel);

        // Font color choice
        fontColorComboBox = new JComboBox<>();
        fontColorComboBox.addItemListener(new ChangeForegroundListener(panel));
        for (DisplayColor c : DisplayColor.values()) {
            fontColorComboBox.addItem(c.getHtml());
            if (c.toString().equals(prevForeground.toString()))
                fontColorComboBox.setSelectedItem(c.getHtml());
        }
        constraints.gridx = RIGHT;
        constraints.gridy = row;
        constraints.anchor = GridBagConstraints.WEST;
        layout.setConstraints(fontColorComboBox, constraints);
        add(fontColorComboBox);

        row++;

        // Background color label
        JLabel backgroundColorLabel = new JLabel(LABEL_BACKGROUND_COLOR);
        constraints.gridx = LEFT;
        constraints.gridy = row;
        constraints.anchor = GridBagConstraints.EAST;
        layout.setConstraints(backgroundColorLabel, constraints);
        add(backgroundColorLabel);

        // Background color choice
        backgroundColorComboBox = new JComboBox<>();
        backgroundColorComboBox.addItemListener(new ChangeBackgroundListener(panel));
        for (DisplayColor c : DisplayColor.values()) {
            backgroundColorComboBox.addItem(c.getHtml());
            if (c.toString().equals(prevBackground.toString()))
                backgroundColorComboBox.setSelectedItem(c.getHtml());
        }
        constraints.gridx = RIGHT;
        constraints.gridy = row;
        constraints.anchor = GridBagConstraints.WEST;
        layout.setConstraints(backgroundColorComboBox, constraints);
        add(backgroundColorComboBox);

        row++;

        // Buttons panel
        JPanel buttonPanel = new JPanel();
        GridBagLayout buttonLayout = new GridBagLayout();
        buttonPanel.setLayout(buttonLayout);
        constraints.gridx = RIGHT;
        constraints.gridy = row;
        constraints.anchor = GridBagConstraints.EAST;
        layout.setConstraints(buttonPanel, constraints);
        add(buttonPanel);

        // OK button
        okButton = new JButton("OK");
        okButton.addActionListener(listener);
        constraints.gridx = RIGHT;
        constraints.gridy = 1;
        constraints.anchor = GridBagConstraints.EAST;
        buttonLayout.setConstraints(okButton, constraints);
        buttonPanel.add(okButton);

        // Cancel button
        cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(listener);
        constraints.gridx = LEFT;
        constraints.gridy = 1;
        constraints.anchor = GridBagConstraints.EAST;
        buttonLayout.setConstraints(cancelButton, constraints);
        buttonPanel.add(cancelButton);

        setLayout(layout);
        setTitle(DIALOG_TITLE);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private class PropertiesDialogActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource().equals(okButton)) {
                // Save properties
                DigitalClockConfiguration conf = DigitalClock.getConfiguration();
                conf.update(owner.getLocation(), owner.getWidth(), owner.getHeight(),
                        panel.getClockFont(), DisplayColor.valueOf(panel.getForeground()),
                        DisplayColor.valueOf(panel.getBackground()));
                setVisible(false);
            } else if (e.getSource().equals(cancelButton)) {
                // Roll back
                panel.setClockFont(prevFont);
                panel.setForeground(prevForeground.getValue());
                panel.setBackground(prevBackground.getValue());
                owner.pack();
                owner.setLocation(prevLocation);
                setVisible(false);
            }
        }
    }

}
