/*
 * Copyright(C) 2014 Yutaka Kato
 */
package local.GUI.ex1_3;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ChangeFontColorActionListener implements ActionListener {

    protected DigitalClockCanvas canvas;

    public ChangeFontColorActionListener(DigitalClockCanvas canvas) {
        this.canvas = canvas;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        canvas.setForeground(findColorByName(e.getActionCommand()));
    }
    
    protected Color findColorByName(String name) {
        if (name.equals("black"))
            return new Color(0, 0, 0);
        if (name.equals("silver"))
            return new Color(192, 192, 192);
        if (name.equals("gray"))
            return new Color(128, 128, 128);
        if (name.equals("white"))
            return new Color(255, 255, 255);
        if (name.equals("maroon"))
            return new Color(128, 0, 0);
        if (name.equals("red"))
            return new Color(255, 0, 0);
        if (name.equals("purple"))
            return new Color(128, 0, 128);
        if (name.equals("fuchsia"))
            return new Color(255, 0, 255);
        if (name.equals("green"))
            return new Color(0, 128, 0);
        if (name.equals("lime"))
            return new Color(0, 255, 0);
        if (name.equals("olive"))
            return new Color(128, 128, 0);
        if (name.equals("yellow"))
            return new Color(255, 255, 0);
        if (name.equals("navy"))
            return new Color(0, 0, 128);
        if (name.equals("blue"))
            return new Color(0, 0, 256);
        if (name.equals("teal"))
            return new Color(0, 128, 128);
        if (name.equals("aqua"))
            return new Color(0, 255, 255);
        return new Color(0, 0, 0);
    }
}
