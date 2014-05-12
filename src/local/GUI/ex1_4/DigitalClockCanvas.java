/*
 * Copyright(C) 2013-2014 Yutaka Kato
 */
package local.GUI.ex1_4;

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Label;
import java.awt.RenderingHints;
import java.awt.image.BufferStrategy;
import java.util.Calendar;

/**
 * Drawing clock area.
 */
@SuppressWarnings("serial")
class DigitalClockCanvas extends Canvas implements Runnable {

    private static final int BUFFER_SIZE = 2;
    private static final int CLOCK_BASELINE = 15;
    private static final int MARGIN_LEFT = 20;
    private static final int MARGIN_RIGHT = 10;
    private Font labelFont;
    private Font clockFont;
    private Frame frame;
    private Dimension dimension;
    private BufferStrategy bufferStrategy;

    public DigitalClockCanvas(String title) {
        DigitalClockConfiguration conf = DigitalClock.getConfiguration();
        dimension = new Dimension(conf.getWidth(), conf.getHeight());
        setForeground(conf.getForeground().getValue());
        setBackground(conf.getBackground().getValue());
        labelFont = new Font(conf.getFontName(), Font.PLAIN, 11);
        clockFont = new Font(conf.getFontName(), Font.BOLD, conf.getFontSize());
        frame = new DigitalClockFrame(title, this);
        createBufferStrategy(BUFFER_SIZE);
        bufferStrategy = getBufferStrategy();
        setClockFont(clockFont);
        new Thread(this).start();
    }

    private void draw() {
        Graphics graphics = bufferStrategy.getDrawGraphics();
        if (!bufferStrategy.contentsLost()) {
            graphics.clearRect(0, 0, getSize().width, getSize().height);
            ((Graphics2D) graphics).setRenderingHint(
                    RenderingHints.KEY_TEXT_ANTIALIASING,
                    RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
            graphics = updateClock(graphics);
            bufferStrategy.show();
            graphics.dispose();
        }
    }

    /**
     * Update clock string
     * 
     * @param graphics Source graphics
     * @return Updated graphics
     */
    private Graphics updateClock(Graphics graphics) {
        graphics.setFont(labelFont);
        graphics.drawString("現在時刻", MARGIN_LEFT, CLOCK_BASELINE);
        graphics.setFont(clockFont);
        graphics.drawString(getTime(), MARGIN_LEFT,
                CLOCK_BASELINE + clockFont.getSize());
        return graphics;
    }

    /**
     * Get current clock font.
     * 
     * @return Font
     */
    Font getClockFont() {
        return clockFont;
    }

    /**
     * Set clock font.
     * 
     * @param font Font
     */
    void setClockFont(Font font) {
        clockFont = font;
        // Simulate preferred size
        Label tempLabel = new Label(getTime());
        tempLabel.setFont(font);
        Frame frame = new Frame();
        frame.add(tempLabel);
        frame.pack();
        dimension = frame.getPreferredSize();
        dimension.height += 1;
        dimension.width += MARGIN_LEFT + MARGIN_RIGHT; // add margin
        draw();
        frame.pack();
    }

    @Override
    public Dimension getPreferredSize() {
        return dimension;
    }

    @Override
    public Dimension getMinimumSize() {
        return getPreferredSize();
    }

    @Override
    public void run() {
        while (true) {
            // repaint();
            draw();
            frame.pack();
            try {
                Thread.sleep(10); // Update in 10ms
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 日付表示を取得します。
     * 
     * @return 日付表示
     */
    private String getTime() {
        Calendar now = Calendar.getInstance();
        int h = now.get(Calendar.HOUR_OF_DAY);
        int m = now.get(Calendar.MINUTE);
        int s = now.get(Calendar.SECOND);
        int ms = now.get(Calendar.MILLISECOND);
        return String.format("%02d:%02d:%02d:%03d", h, m, s, ms);
    }
}
