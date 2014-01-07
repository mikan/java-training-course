/*
 * Copyright(C) 2013 Yutaka Kato
 */
package local.GUI.ex1_2;

import java.awt.Button;
import java.awt.Dialog;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Label;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

@SuppressWarnings("serial")
public class ChangeFontSizeDialog extends Dialog implements ActionListener {

    private Frame owner;
    private TextField sizeTextField;
    private DigitalClockCanvas canvas;

    ChangeFontSizeDialog(Frame owner, DigitalClockCanvas canvas) {
        super(owner);
        this.canvas = canvas;
        this.owner = owner;

        setLayout(new FlowLayout());
        setTitle("Change font size");

        Label sizeLabel = new Label("Font size (px): ");
        add(sizeLabel);

        sizeTextField = new TextField("", 3);
        sizeTextField.addActionListener(this);
        add(sizeTextField);

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
        int size = currentFont.getSize();
        try {
            size = Integer.parseInt(sizeTextField.getText());
        } catch (NumberFormatException nfe) {
            new IllegalInputDialog(this, "数字を入れてね☆");
            return;
        }
        if (size < 10) {
            new IllegalInputDialog(this, "数字は 10 以上で入れてね☆");
            return;
        } else if (size > 500) {
            new IllegalInputDialog(this, "数字がでかすぎるよ☆");
            return;
        }
        canvas.setClockFont(new Font(currentFont.getFamily(), currentFont
                .getStyle(), size));
        owner.pack();
        owner.setLocationRelativeTo(null);
        setVisible(false);
    }
}

@SuppressWarnings("serial")
class IllegalInputDialog extends Dialog implements ActionListener {

    public IllegalInputDialog(Dialog owner, String message) {
        super(owner);
        setLayout(new FlowLayout());
        setTitle("Change font size");

        add(new Label(message));

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
        setVisible(false);
    }

}