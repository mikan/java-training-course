/*
 * Copyright(C) 2013 Yutaka Kato
 */
package local.GUI.ex1_2;

import java.awt.Button;
import java.awt.Choice;
import java.awt.Dialog;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Frame;
import java.awt.GraphicsEnvironment;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

@SuppressWarnings("serial")
public class ChangeFontDialog extends Dialog implements ActionListener {

    private Frame owner;
    private Choice fontChoise;
    private DigitalClockCanvas canvas;

    ChangeFontDialog(Frame owner, DigitalClockCanvas canvas) {
        super(owner);
        this.canvas = canvas;
        this.owner = owner;

        setLayout(new FlowLayout());
        setTitle("Change font");

        GraphicsEnvironment ge = GraphicsEnvironment
                .getLocalGraphicsEnvironment();
        Font[] fonts = ge.getAllFonts();
        fontChoise = new Choice();
        for (Font f : fonts) {
            fontChoise.add(f.getName());
        }
        add(fontChoise);

        Button okButton = new Button("OK");
        okButton.addActionListener(this);
        add(okButton);

        pack();
        setLocationRelativeTo(null);
        addWindowListener(new DialogCloseAdapter(this));
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Font currentFont = canvas.getClockFont();
        canvas.setClockFont(new Font(fontChoise.getSelectedItem(), currentFont
                .getStyle(), currentFont.getSize()));
        owner.pack();
        owner.setLocationRelativeTo(null);
        setVisible(false);
    }
}