/*
 * Copyright(C) 2013-2014 Yutaka Kato
 */
package local.GUI.ex1_3;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Dialog;
import java.awt.Font;
import java.awt.Label;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

@SuppressWarnings("serial")
public class AboutDialog extends Dialog implements ActionListener {
    AboutDialog(Window owner) {
        super(owner);
        setLayout(new BorderLayout());
        setAlwaysOnTop(true);

        Label aboutLabel = new Label("Digital Clock");
        aboutLabel.setAlignment(Label.CENTER);
        aboutLabel.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 30));
        add(aboutLabel, BorderLayout.NORTH);

        Label copyLabel = new Label("2014 Yutaka Kato");
        copyLabel.setAlignment(Label.CENTER);
        copyLabel.setFont(new Font(Font.SERIF, Font.PLAIN, 15));
        add(copyLabel, BorderLayout.CENTER);

        Button okButton = new Button("OK");
        okButton.addActionListener(this);
        add(okButton, BorderLayout.SOUTH);

        setTitle("About");
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