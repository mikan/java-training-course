/*
 * Copyright(C) 2014 Yutaka Kato
 */
package local.GUI.ex1_4;

import java.awt.Color;
import java.awt.Font;
import java.awt.Point;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class DigitalClockProperties {

    private Properties prop;
    private static final String FILE_NAME = "digitalclock_yk.properties";
    private static final String KEY_LOCATION_X = "frame.location.x";
    private static final String KEY_LOCATION_Y = "frame.location.y";
    private static final String KEY_WIDTH = "frame.width";
    private static final String KEY_HEIGHT = "frame.height";
    private static final String KEY_FONT_NAME = "canvas.font.name";
    private static final String KEY_FONT_SIZE = "canvas.font.size";
    private static final String KEY_COLOR_FOREGROUND_R = "canvas.color.fg.r";
    private static final String KEY_COLOR_FOREGROUND_G = "canvas.color.fg.g";
    private static final String KEY_COLOR_FOREGROUND_B = "canvas.color.fg.b";
    private static final String KEY_COLOR_BACKGROUND_R = "canvas.color.bg.r";
    private static final String KEY_COLOR_BACKGROUND_G = "canvas.color.bg.g";
    private static final String KEY_COLOR_BACKGROUND_B = "canvas.color.bg.b";
    private static final int DEFAULT_WIDTH = 370;
    private static final int DEFAULT_HEIGHT = 80;
    private static final int DEFAULT_FONT_SIZE = 50;
    private static final String DEFAULT_FONT_NAME = Font.SANS_SERIF;
    private static final DisplayColor DEFAULT_COLOR_FOREGROUND = DisplayColor.LIME;
    private static final DisplayColor DEFAULT_COLOR_BACKGROUND = DisplayColor.DARKGRAY;

    public DigitalClockProperties() {
        prop = new Properties();
        File propFile = new File(FILE_NAME);
        FileInputStream input = null;
        try {
            propFile.createNewFile();
            input = new FileInputStream(propFile);
            prop.load(input);
        } catch (FileNotFoundException e) {
            System.err.println("File not found: " + FILE_NAME);
        } catch (IOException e) {
            System.err.println("IOException");
        } finally {
            try {
                if (input != null)
                    input.close();
            } catch (IOException e) {
                System.err.println("IOException");
            }
        }
    }
    
    public void update(Point location, int width, int height) {
        if (location == null)
            throw new NullPointerException();
        File propFile = new File(FILE_NAME);
        FileOutputStream output = null;
        prop.setProperty(KEY_LOCATION_X, Integer.toString(location.x));
        prop.setProperty(KEY_LOCATION_Y, Integer.toString(location.y));
        prop.setProperty(KEY_WIDTH, Integer.toString(width));
        prop.setProperty(KEY_HEIGHT, Integer.toString(height));
        try {
            output = new FileOutputStream(propFile);
            prop.store(output, null);
        } catch (FileNotFoundException e) {
            System.err.println("File not found: " + FILE_NAME);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (output != null)
                    output.close();
            } catch (IOException e) {
                System.err.println("IOException");
            }
        }
    }

    public void update(Point location, int width, int height, Font font, DisplayColor foreground,
            DisplayColor background) {
        if (location == null || font == null || foreground == null || background == null)
            throw new NullPointerException();
        File propFile = new File(FILE_NAME);
        FileOutputStream output = null;
        prop.setProperty(KEY_LOCATION_X, Integer.toString(location.x));
        prop.setProperty(KEY_LOCATION_Y, Integer.toString(location.y));
        prop.setProperty(KEY_WIDTH, Integer.toString(width));
        prop.setProperty(KEY_HEIGHT, Integer.toString(height));
        prop.setProperty(KEY_FONT_NAME, font.getName());
        prop.setProperty(KEY_FONT_SIZE, Integer.toString(font.getSize()));
        prop.setProperty(KEY_COLOR_FOREGROUND_R, Integer.toString(foreground.getValue().getRed()));
        prop.setProperty(KEY_COLOR_FOREGROUND_G, Integer.toString(foreground.getValue().getGreen()));
        prop.setProperty(KEY_COLOR_FOREGROUND_B, Integer.toString(foreground.getValue().getBlue()));
        prop.setProperty(KEY_COLOR_BACKGROUND_R, Integer.toString(background.getValue().getRed()));
        prop.setProperty(KEY_COLOR_BACKGROUND_G, Integer.toString(background.getValue().getGreen()));
        prop.setProperty(KEY_COLOR_BACKGROUND_B, Integer.toString(background.getValue().getBlue()));
        try {
            output = new FileOutputStream(propFile);
            prop.store(output, null);
        } catch (FileNotFoundException e) {
            System.err.println("File not found: " + FILE_NAME);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (output != null)
                    output.close();
            } catch (IOException e) {
                System.err.println("IOException");
            }
        }
    }

    public Point getLocation() {
        String x = prop.getProperty(KEY_LOCATION_X);
        String y = prop.getProperty(KEY_LOCATION_Y);
        if (x == null || y == null)
            return null;
        return new Point(Integer.parseInt(x), Integer.parseInt(y));
    }
    
    public int getWidth() {
        String width = prop.getProperty(KEY_WIDTH);
        return width == null ? DEFAULT_WIDTH : Integer.parseInt(width);
    }
    
    public int getHeight() {
        String height = prop.getProperty(KEY_HEIGHT);
        return height == null ? DEFAULT_HEIGHT : Integer.parseInt(height);
    }

    public String getFontName() {
        return prop.getProperty(KEY_FONT_NAME, DEFAULT_FONT_NAME);
    }

    public int getFontSize() {
        String size = prop.getProperty(KEY_FONT_SIZE);
        return size == null ? DEFAULT_FONT_SIZE : Integer.parseInt(size);
    }

    public DisplayColor getForeground() {
        String r = prop.getProperty(KEY_COLOR_FOREGROUND_R);
        String g = prop.getProperty(KEY_COLOR_FOREGROUND_G);
        String b = prop.getProperty(KEY_COLOR_FOREGROUND_B);
        if (r == null || g == null || b == null)
            return DEFAULT_COLOR_FOREGROUND;
        return DisplayColor.valueOf(new Color(Integer.parseInt(r), Integer
                .parseInt(g), Integer.parseInt(b)));
    }

    public DisplayColor getBackground() {
        String r = prop.getProperty(KEY_COLOR_BACKGROUND_R);
        String g = prop.getProperty(KEY_COLOR_BACKGROUND_G);
        String b = prop.getProperty(KEY_COLOR_BACKGROUND_B);
        if (r == null || g == null || b == null)
            return DEFAULT_COLOR_BACKGROUND;
        return DisplayColor.valueOf(new Color(Integer.parseInt(r), Integer
                .parseInt(g), Integer.parseInt(b)));
    }
}
