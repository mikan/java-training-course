/*
 * Copyright(C) 2014 Yutaka Kato
 */
package local.GUI.ex2_4;

import java.awt.Font;
import java.awt.Point;

/**
 * Permanent configuration of digital clock properties.
 */
public interface DigitalClockConfiguration {
    
    public static final String KEY_LOCATION_X = "frame.location.x";
    public static final String KEY_LOCATION_Y = "frame.location.y";
    public static final String KEY_WIDTH = "frame.width";
    public static final String KEY_HEIGHT = "frame.height";
    public static final String KEY_FONT_NAME = "panel.font.name";
    public static final String KEY_FONT_SIZE = "panel.font.size";
    public static final String KEY_COLOR_FOREGROUND_R = "panel.color.fg.r";
    public static final String KEY_COLOR_FOREGROUND_G = "panel.color.fg.g";
    public static final String KEY_COLOR_FOREGROUND_B = "panel.color.fg.b";
    public static final String KEY_COLOR_BACKGROUND_R = "panel.color.bg.r";
    public static final String KEY_COLOR_BACKGROUND_G = "panel.color.bg.g";
    public static final String KEY_COLOR_BACKGROUND_B = "panel.color.bg.b";
    public static final int DEFAULT_WIDTH = 370;
    public static final int DEFAULT_HEIGHT = 80;
    public static final int DEFAULT_FONT_SIZE = 50;
    public static final String DEFAULT_FONT_NAME = Font.MONOSPACED;
    public static final DisplayColor DEFAULT_COLOR_FOREGROUND = DisplayColor.LIME;
    public static final DisplayColor DEFAULT_COLOR_BACKGROUND = DisplayColor.DARKGRAY;
    
    public void update(Point location, int width, int height);

    public void update(Point location, int width, int height, Font font, DisplayColor foreground,
            DisplayColor background);

    public Point getLocation();

    public int getWidth();

    public int getHeight();

    public String getFontName();

    public int getFontSize();

    public DisplayColor getForeground();

    public DisplayColor getBackground();
}
