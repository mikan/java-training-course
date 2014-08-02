/*
 * Copyright(C) 2014 Yutaka Kato
 */
package local.GUI.ex2_4;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JFrame;

public class ChangeFontSizeListener implements ItemListener, ActionListener {

    private JFrame owner;
    private DigitalClockPanel panel;

    public ChangeFontSizeListener(JFrame owner, DigitalClockPanel panel) {
        this.owner = owner;
        this.panel = panel;
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        setSize(e.getItem().toString());
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        setSize(e.getActionCommand());
    }

    private void setSize(String sizeStr) {
        Font currentFont = panel.getClockFont();
        int size = currentFont.getSize();
        try {
            size = Integer.parseInt(sizeStr);
        } catch (NumberFormatException nfe) {
            System.err.println("NumberFormatException: " + sizeStr);
            return;
        }
        panel.setClockFont(new Font(currentFont.getFamily(), currentFont.getStyle(), size));
        owner.pack();
    }
}
