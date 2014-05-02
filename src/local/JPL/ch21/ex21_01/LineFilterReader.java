/*
 * Copyright(C) 2014 Yutaka Kato
 */
package local.JPL.ch21.ex21_01;

import java.io.FilterReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

public class LineFilterReader extends FilterReader {
    
    private static final char BR = System.getProperty("line.separator").charAt(0);

    protected LineFilterReader(Reader in) {
        super(in);
    }

    public List<String> readLine() throws IOException {
        List<String> list = new ArrayList<>();
        int c;
        StringBuilder sb = new StringBuilder();
        try {
            while ((c = read()) != -1) {
                if (c == BR) {
                    boolean inserted = false;
                    for (int i = 0; i < list.size(); i++) {
                        if (list.get(i).compareTo(sb.toString()) > 0) {
                            list.add(i + 1, sb.toString());
                            inserted = true;
                            break;
                        }
                    }
                    if (!inserted)
                        list.add(sb.toString());
                    sb.setLength(0);
                } else {
                    sb.append((char) c);
                }
            }
            list.add(sb.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }
}