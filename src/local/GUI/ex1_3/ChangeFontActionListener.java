/*
 * Copyright(C) 2014 Yutaka Kato
 */
package local.GUI.ex1_3;

import java.awt.Font;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ChangeFontActionListener implements ActionListener {

    private Window owner;
    private DigitalClockCanvas canvas;

    public ChangeFontActionListener(Window owner, DigitalClockCanvas canvas) {
        this.owner = owner;
        this.canvas = canvas;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Font currentFont = canvas.getClockFont();
        canvas.setClockFont(new Font(e.getActionCommand(), currentFont
                .getStyle(), currentFont.getSize()));
        if (!System.getProperty("os.name").startsWith("Windows"))
            owner.pack(); // Windows ではまともに動かん！
        owner.setLocationRelativeTo(null);
    }
}
