/*
 * Copyright(C) 2014 Yutaka Kato
 */
package local.GUI.ex2_3;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.util.Calendar;

import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * Draw clock.
 */
@SuppressWarnings("serial")
public class DigitalClockPanel extends JPanel {

    private static final int MARGIN_TOP = 15;
    private static final int MARGIN_LEFT = 5;
    private static final int MARGIN_RIGHT = 5;
    private static final String CURRENT_TIME_DESC = "CURRENT TIME";
    private static final String SEP_ON = "%02d:%02d:%02d.%03d";
    private static final String SEP_OFF = "%02d %02d %02d.%03d";
    private boolean descVisible = true;
    private boolean blink = true;
    private boolean autoBlink = true;
    private Font labelFont = new Font(Font.SANS_SERIF, Font.PLAIN, 12);
    // private Font clockFont = new Font(Font.MONOSPACED, Font.BOLD, 50);
    private Font clockFont = new Font("ＭＳ ゴシック", Font.BOLD, 50);
    private Dimension size = new Dimension();

    public DigitalClockPanel() {
	DigitalClockConfiguration conf = DigitalClock.getConfiguration();
	size = new Dimension(conf.getWidth(), conf.getHeight());
	setForeground(conf.getForeground().getValue());
	setBackground(conf.getBackground().getValue());
	clockFont = new Font(conf.getFontName(), Font.BOLD, conf.getFontSize());
	setClockFont(clockFont);
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
     * @param font font
     * @throws NullPointerException if font is null
     */
    void setClockFont(Font font) {
	if (font == null)
	    throw new NullPointerException("font is null");
	clockFont = font;
	// Simulate preferred size
	size = getExpectedSize(font);
	size.height += 1;
	size.width += MARGIN_LEFT + MARGIN_RIGHT; // add margin
    }

    /**
     * Get visibility of current time's description.
     * 
     * @return visibility
     */
    boolean isDescVisible() {
	return descVisible;
    }

    /**
     * Set visibility of current time's description.
     * 
     * @param visible visibility
     */
    void setDescVisible(boolean visible) {
	descVisible = visible;
    }

    /**
     * Get blinking state.
     * 
     * @return blinking state
     */
    boolean isBlink() {
	return blink;
    }

    /**
     * Set blinking state.
     * 
     * @param blinking state
     */
    void setBlink(boolean blink) {
	this.blink = blink;
    }

    @Override
    public Dimension getPreferredSize() {
	return size;
    }

    @Override
    public Dimension getMinimumSize() {
	return size;
    }

    @Override
    public void paintComponent(Graphics g) {
	super.paintComponent(g);
	g.setFont(labelFont);
	g.drawString(descVisible ? CURRENT_TIME_DESC : "", MARGIN_LEFT, MARGIN_TOP);
	g.setFont(clockFont);
	g.drawString(getTime(), MARGIN_LEFT, MARGIN_TOP + clockFont.getSize());
    }

    /**
     * Get expected size with specified font.
     * 
     * @param font Font
     * @return expected size
     */
    private Dimension getExpectedSize(Font font) {
	JLabel tempLabel = new JLabel(getTime());
	tempLabel.setFont(font);
	Dimension size = tempLabel.getPreferredSize();
	return new Dimension(size.width, size.height + MARGIN_TOP);
    }

    /**
     * Get time string
     * 
     * @return time string
     */
    private String getTime() {
	Calendar now = Calendar.getInstance();
	int h = now.get(Calendar.HOUR_OF_DAY);
	int m = now.get(Calendar.MINUTE);
	int s = now.get(Calendar.SECOND);
	int ms = now.get(Calendar.MILLISECOND);
	if (autoBlink)
	    blink = isProportional(clockFont);
	return String.format(blink ? ms > 500 ? SEP_OFF : SEP_ON : SEP_ON, h, m, s, ms);
    }

    private boolean isProportional(Font font) {
	FontMetrics metrics = getFontMetrics(font);
	return metrics.charWidth(':') == metrics.charWidth(' ');
    }
}