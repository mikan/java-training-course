/*
 * Copyright(C) 2014 Yutaka Kato
 */
package local.JPL.ch22.ex22_10;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CommentSkipScanner {
    
    private static final String PATH = "src/local/JPL/ch22/ex22_10/input.txt";

    public static void main(String[] args) {
        BufferedReader input = null;
        try {
            input = new BufferedReader(new FileReader(PATH));
            List<String> result = new CommentSkipScanner().readLines(input);
            for (String line : result) {
                System.out.println(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public List<String> readLines(Readable source) {
        Scanner in = null;
        List<String> lines = new ArrayList<String>();
        try {
            in = new Scanner(source);
            in.useDelimiter("//.*\n");
            while (in.hasNext()) {
                String line = in.next();
                if (!line.isEmpty())
                    lines.add(line);
            }
        } finally {
            in.close();
        }
        return lines;
    }
}
