/*
 * Copyright(C) 2013-2014 Yutaka Kato
 */
package local.Interpret;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Dialog;
import java.awt.Font;
import java.awt.Label;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JDialog;

@SuppressWarnings("serial")
public class AboutDialog extends JDialog implements ActionListener {
    AboutDialog(Window owner) {
        super(owner);
        setLayout(new BorderLayout());
        setAlwaysOnTop(true);

        Label aboutLabel = new Label(" Interpret ");
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

    /**
     * Window adapter for the dialog closing.
     */
    private static class DialogCloseAdapter extends WindowAdapter {
        Dialog dialog;

        public DialogCloseAdapter(Dialog dialog) {
            this.dialog = dialog;
        }

        @Override
        public void windowClosing(WindowEvent e) {
            dialog.setVisible(false);
        }
    }
}