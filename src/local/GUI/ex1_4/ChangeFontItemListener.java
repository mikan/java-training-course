/*
 * Copyright(C) 2014 Yutaka Kato
 */
package local.GUI.ex1_4;

import java.awt.Font;
import java.awt.Frame;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class ChangeFontItemListener implements ItemListener {

    private Frame owner;
    private DigitalClockCanvas canvas;

    public ChangeFontItemListener(Frame owner, DigitalClockCanvas canvas) {
        this.owner = owner;
        this.canvas = canvas;
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        Font currentFont = canvas.getClockFont();
        canvas.setClockFont(new Font(e.getItem().toString(), currentFont
                .getStyle(), currentFont.getSize()));
        owner.pack();
    }
}
