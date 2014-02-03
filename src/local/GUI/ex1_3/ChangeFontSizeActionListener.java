/*
 * Copyright(C) 2014 Yutaka Kato
 */
package local.GUI.ex1_3;

import java.awt.Font;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ChangeFontSizeActionListener implements ActionListener {

    private Window owner;
    private DigitalClockCanvas canvas;

    public ChangeFontSizeActionListener(Window owner, DigitalClockCanvas canvas) {
        this.owner = owner;
        this.canvas = canvas;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Font currentFont = canvas.getClockFont();
        int size = currentFont.getSize();
        try {
            size = Integer.parseInt(e.getActionCommand());
        } catch (NumberFormatException nfe) {
            System.err.println("NumberFormatException: " + e.getActionCommand());
            return;
        }
        canvas.setClockFont(new Font(currentFont.getFamily(), currentFont.getStyle(), size));
        owner.pack();
        owner.setLocationRelativeTo(null);
    }

}
