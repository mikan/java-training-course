/*
 * Copyright(C) 2014 Yutaka Kato
 */
package local.GUI.ex2_2;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JFrame;

public class ChangeFontListener implements ItemListener, ActionListener {

    private JFrame owner;
    private DigitalClockPanel panel;

    public ChangeFontListener(JFrame owner, DigitalClockPanel panel) {
        this.owner = owner;
        this.panel = panel;
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        setFont(e.getItem().toString());
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        setFont(e.getActionCommand());
    }

    private void setFont(String font) {
        Font currentFont = panel.getClockFont();
        panel.setClockFont(new Font(font, currentFont.getStyle(), currentFont.getSize()));
        owner.pack();
    }
}
