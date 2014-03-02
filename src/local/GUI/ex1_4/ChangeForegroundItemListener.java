/*
 * Copyright(C) 2014 Yutaka Kato
 */
package local.GUI.ex1_4;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class ChangeForegroundItemListener implements ItemListener {

    private DigitalClockCanvas canvas;

    public ChangeForegroundItemListener(DigitalClockCanvas canvas) {
        this.canvas = canvas;
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        canvas.setForeground(DisplayColor.nameOf(e.getItem().toString()).getValue());
    }
}
