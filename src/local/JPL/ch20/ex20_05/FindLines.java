/*
 * Copyright(C) 2014 Yutaka Kato
 */
package local.JPL.ch20.ex20_05;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;

public class FindLines {
    public static void main(String[] args) {
        if (args.length != 2)
            throw new IllegalArgumentException("need char and file");

        FileReader fileIn;
        LineNumberReader in;
        try {
            fileIn = new FileReader(args[1]);
            in = new LineNumberReader(fileIn);
        } catch (FileNotFoundException e1) {
            System.out.println("File not found: " + e1.getMessage());
            return;
        }
        String line;
        try {
            while ((line = in.readLine()) != null)
                if (line.contains(args[0]))
                    System.out.printf("%3d: %s%n", in.getLineNumber(), line);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

/*
 * $ java local.JPL.ch20.ex20_05.FindLines Reader local/JPL/ch20/ex20_05/input.txt 
 *  4: import java.io.FileReader;
 *  6: import java.io.LineNumberReader;
 * 16:         FileReader fileIn;
 * 17:         LineNumberReader in;
 * 19:             fileIn = new FileReader(args[1]);
 * 20:             in = new LineNumberReader(fileIn);
 */
