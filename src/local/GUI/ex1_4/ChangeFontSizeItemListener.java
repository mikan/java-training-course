/*
 * Copyright(C) 2014 Yutaka Kato
 */
package local.GUI.ex1_4;

import java.awt.Font;
import java.awt.Frame;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class ChangeFontSizeItemListener implements ItemListener {

    private Frame owner;
    private DigitalClockCanvas canvas;

    public ChangeFontSizeItemListener(Frame owner, DigitalClockCanvas canvas) {
        this.owner = owner;
        this.canvas = canvas;
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        Font currentFont = canvas.getClockFont();
        int size = currentFont.getSize();
        try {
            size = Integer.parseInt(e.getItem().toString());
        } catch (NumberFormatException nfe) {
            System.err.println("NumberFormatException: " + e.getItem().toString());
            return;
        }
        canvas.setClockFont(new Font(currentFont.getFamily(), currentFont.getStyle(), size));
        owner.pack();
    }

}
