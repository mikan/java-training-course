/*
 * Copyright(C) 2014 Yutaka Kato
 */
package local.GUI.ex1_4;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class ChangeBackgroundItemListener implements ItemListener {

    private DigitalClockCanvas canvas;

    public ChangeBackgroundItemListener(DigitalClockCanvas canvas) {
        this.canvas = canvas;
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        canvas.setBackground(DisplayColor.nameOf(e.getItem().toString())
                .getValue());
    }

}
