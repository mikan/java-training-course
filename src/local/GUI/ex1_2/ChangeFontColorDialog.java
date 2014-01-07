/*
 * Copyright(C) 2013 Yutaka Kato
 */
package local.GUI.ex1_2;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Label;
import java.awt.Panel;
import java.awt.Scrollbar;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;

@SuppressWarnings("serial")
public class ChangeFontColorDialog extends Dialog implements ActionListener,
        AdjustmentListener {

    private DigitalClockCanvas canvas;
    private Scrollbar redBar, greenBar, blueBar;
    private Label redLabel, greenLabel, blueLabel;
    private Color color;
    private Panel colorPanel;

    ChangeFontColorDialog(Frame owner, DigitalClockCanvas canvas) {
        super(owner);
        this.canvas = canvas;
        this.color = canvas.getForeground();

        setLayout(new BorderLayout());
        setTitle("Change font color");

        colorPanel = new Panel();
        colorPanel.setBackground(color);
        colorPanel.setPreferredSize(new Dimension(300, 110));
        add(colorPanel, BorderLayout.NORTH);

        Panel barPanel = new Panel(new BorderLayout());
        redBar = new Scrollbar(Scrollbar.HORIZONTAL);
        redBar.setMinimum(0);
        redBar.setMaximum(255);
        redBar.setValue(color.getRed());
        redBar.addAdjustmentListener(this);
        barPanel.add(redBar, BorderLayout.NORTH);
        greenBar = new Scrollbar(Scrollbar.HORIZONTAL);
        greenBar.setMinimum(0);
        greenBar.setMaximum(255);
        greenBar.setValue(color.getGreen());
        greenBar.addAdjustmentListener(this);
        barPanel.add(greenBar, BorderLayout.CENTER);
        blueBar = new Scrollbar(Scrollbar.HORIZONTAL);
        blueBar.setMinimum(0);
        blueBar.setMaximum(255);
        blueBar.setValue(color.getBlue());
        blueBar.addAdjustmentListener(this);
        barPanel.add(blueBar, BorderLayout.SOUTH);
        barPanel.setPreferredSize(new Dimension(270, 50));
        add(barPanel, BorderLayout.CENTER);
        
        Panel valuePanel = new Panel(new BorderLayout());
        redLabel = new Label(Integer.toString(color.getRed()));
        redLabel.setAlignment(Label.RIGHT);
        valuePanel.add(redLabel, BorderLayout.NORTH);
        greenLabel = new Label(Integer.toString(color.getGreen()));
        greenLabel.setAlignment(Label.RIGHT);
        valuePanel.add(greenLabel, BorderLayout.CENTER);
        blueLabel = new Label(Integer.toString(color.getBlue()));
        blueLabel.setAlignment(Label.RIGHT);
        valuePanel.add(blueLabel, BorderLayout.SOUTH);
        valuePanel.setPreferredSize(new Dimension(30, 50));
        add(valuePanel, BorderLayout.EAST);

        Button okButton = new Button("OK");
        okButton.addActionListener(this);
        add(okButton, BorderLayout.SOUTH);
        pack();
        setLocationRelativeTo(null);
        addWindowListener(new DialogCloseAdapter(this));
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        canvas.setForeground(color);
        setVisible(false);
    }

    @Override
    public void adjustmentValueChanged(AdjustmentEvent e) {
        int r = redBar.getValue();
        int g = greenBar.getValue();
        int b = blueBar.getValue();
        redLabel.setText(Integer.toString(r));
        greenLabel.setText(Integer.toString(g));
        blueLabel.setText(Integer.toString(b));
        color = new Color(r, g, b);
        colorPanel.setBackground(color);
    }
}