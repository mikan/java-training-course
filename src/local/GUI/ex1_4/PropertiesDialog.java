/*
 * Copyright(C) 2013 Yutaka Kato
 */
package local.GUI.ex1_4;

import java.awt.Button;
import java.awt.Choice;
import java.awt.Dialog;
import java.awt.Font;
import java.awt.Frame;
import java.awt.GraphicsEnvironment;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Label;
import java.awt.Panel;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

@SuppressWarnings("serial")
public class PropertiesDialog extends Dialog implements ActionListener {

    private final Frame owner;
    private final DigitalClockCanvas canvas;
    private Choice fontChoice;
    private Choice fontSizeChoice;
    private Choice fontColorChoice;
    private Choice backgroundColorChoice;
    private Button okButton;
    private Button cancelButton;
    private static final int LEFT = 0;
    private static final int RIGHT = 1;
    private Point prevLocation;
    private Font prevFont;
    private DisplayColor prevForeground;
    private DisplayColor prevBackground;

    public PropertiesDialog(Frame owner, DigitalClockCanvas canvas) {
        super(owner);
        this.owner = owner;
        this.canvas = canvas;
        int row = 0;

        prevLocation = owner.getLocation();
        prevFont = canvas.getClockFont();
        try {
            prevForeground = DisplayColor.valueOf(canvas.getForeground());
        } catch (IllegalArgumentException e) {
            prevForeground = DisplayColor.LIME;
        }
        try {
            prevBackground = DisplayColor.valueOf(canvas.getBackground());
        } catch (IllegalArgumentException e) {
            prevBackground = DisplayColor.DARKGRAY;
        }
        setForeground(DisplayColor.DARKGRAY.getValue());
        setBackground(DisplayColor.SILVER.getValue());

        GridBagLayout layout = new GridBagLayout();
        GridBagConstraints constraints = new GridBagConstraints();

        // Font label
        Label fontLabel = new Label("Font");
        constraints.gridx = LEFT;
        constraints.gridy = row;
        constraints.anchor = GridBagConstraints.EAST;
        layout.setConstraints(fontLabel, constraints);
        add(fontLabel);

        // Font choice
        GraphicsEnvironment ge = GraphicsEnvironment
                .getLocalGraphicsEnvironment();
        Font[] fonts = ge.getAllFonts();
        fontChoice = new Choice();
        fontChoice.addItemListener(new ChangeFontItemListener(owner, canvas));
        for (Font f : fonts) {
            fontChoice.add(f.getName());
            if (f.getName().equals(prevFont.getName()))
                fontChoice.select(f.getName());
        }
        constraints.gridx = RIGHT;
        constraints.gridy = row;
        constraints.anchor = GridBagConstraints.WEST;
        layout.setConstraints(fontChoice, constraints);
        add(fontChoice);

        row++;

        // Font size label
        Label fontSizeLabel = new Label("Font size");
        constraints.gridx = LEFT;
        constraints.gridy = row;
        constraints.anchor = GridBagConstraints.EAST;
        layout.setConstraints(fontSizeLabel, constraints);
        add(fontSizeLabel);

        // Font size choice
        fontSizeChoice = new Choice();
        fontSizeChoice.addItemListener(new ChangeFontSizeItemListener(owner,
                canvas));
        for (int i = 10; i <= 250; i++) {
            if (i % 5 != 0)
                continue;
            fontSizeChoice.add(Integer.toString(i));
            if (i == prevFont.getSize())
                fontSizeChoice.select(Integer.toString(i));
        }
        constraints.gridx = RIGHT;
        constraints.gridy = row;
        constraints.anchor = GridBagConstraints.WEST;
        layout.setConstraints(fontSizeChoice, constraints);
        add(fontSizeChoice);
        row++;

        // Font color label
        Label fontColorLabel = new Label("Font color");
        constraints.gridx = LEFT;
        constraints.gridy = row;
        constraints.anchor = GridBagConstraints.EAST;
        layout.setConstraints(fontColorLabel, constraints);
        add(fontColorLabel);

        // Font color choice
        fontColorChoice = new Choice();
        fontColorChoice
                .addItemListener(new ChangeForegroundItemListener(canvas));
        for (DisplayColor c : DisplayColor.values()) {
            fontColorChoice.add(c.toString());
            if (c.toString().equals(prevForeground.toString()))
                fontColorChoice.select(c.toString());
        }
        constraints.gridx = RIGHT;
        constraints.gridy = row;
        constraints.anchor = GridBagConstraints.WEST;
        layout.setConstraints(fontColorChoice, constraints);
        add(fontColorChoice);

        row++;

        // Background color label
        Label backgroundColorLabel = new Label("Background color");
        constraints.gridx = LEFT;
        constraints.gridy = row;
        constraints.anchor = GridBagConstraints.EAST;
        layout.setConstraints(backgroundColorLabel, constraints);
        add(backgroundColorLabel);

        // Background color choice
        backgroundColorChoice = new Choice();
        backgroundColorChoice.addItemListener(new ChangeBackgroundItemListener(
                canvas));
        for (DisplayColor c : DisplayColor.values()) {
            backgroundColorChoice.add(c.toString());
            if (c.toString().equals(prevBackground.toString()))
                backgroundColorChoice.select(c.toString());
        }
        constraints.gridx = RIGHT;
        constraints.gridy = row;
        constraints.anchor = GridBagConstraints.WEST;
        layout.setConstraints(backgroundColorChoice, constraints);
        add(backgroundColorChoice);

        row++;

        // Buttons panel
        Panel buttonPanel = new Panel();
        GridBagLayout buttonLayout = new GridBagLayout();
        buttonPanel.setLayout(buttonLayout);
        constraints.gridx = RIGHT;
        constraints.gridy = row;
        constraints.anchor = GridBagConstraints.EAST;
        layout.setConstraints(buttonPanel, constraints);
        add(buttonPanel);

        // OK button
        okButton = new Button("OK");
        okButton.addActionListener(this);
        constraints.gridx = RIGHT;
        constraints.gridy = 1;
        constraints.anchor = GridBagConstraints.EAST;
        buttonLayout.setConstraints(okButton, constraints);
        buttonPanel.add(okButton);

        // Cancel button
        cancelButton = new Button("Cancel");
        cancelButton.addActionListener(this);
        constraints.gridx = LEFT;
        constraints.gridy = 1;
        constraints.anchor = GridBagConstraints.EAST;
        buttonLayout.setConstraints(cancelButton, constraints);
        buttonPanel.add(cancelButton);

        setLayout(layout);
        setTitle("Properties");
        setLocationRelativeTo(null);
        addWindowListener(new DialogCloseAdapter(this));
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(okButton)) {
            // Save properties
            DigitalClockProperties prop = new DigitalClockProperties();
            prop.update(owner.getLocation(), owner.getWidth(),
                    owner.getHeight(), canvas.getClockFont(),
                    DisplayColor.valueOf(canvas.getForeground()),
                    DisplayColor.valueOf(canvas.getBackground()));
            setVisible(false);
        } else if (e.getSource().equals(cancelButton)) {
            // Roll back
            canvas.setClockFont(prevFont);
            canvas.setForeground(prevForeground.getValue());
            canvas.setBackground(prevBackground.getValue());
            owner.pack();
            owner.setLocation(prevLocation);
            setVisible(false);
        }
    }
}
