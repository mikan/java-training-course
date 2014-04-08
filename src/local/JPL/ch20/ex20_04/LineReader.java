/*
 * Copyright(C) 2014 Yutaka Kato
 */
package local.JPL.ch20.ex20_04;

import java.io.FilterReader;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;

public class LineReader {

    public static void main(String[] args) {
        StringReader sr = new StringReader("12345\nABC");
        LineFilterReader reader = new LineFilterReader(sr);
        try {
            reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

class LineFilterReader extends FilterReader {
    
    private static final char BR = System.getProperty("line.separator").charAt(0);

    protected LineFilterReader(Reader in) {
        super(in);
    }

    public void readLine() throws IOException {
        int c;
        StringBuilder sb = new StringBuilder();
        try {
            while ((c = read()) != -1) {
                sb.append((char) c);
                if (c == BR) {
                    System.out.println(sb);
                    sb.setLength(0);
                }
            }
            System.out.println(sb);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
