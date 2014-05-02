/*
 * Copyright(C) 2014 Yutaka Kato
 */
package local.JPL.ch21.ex21_01;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CollectionFromFile {
    
    private static final String DEFAULT_FILE = "src/local/JPL/ch21/ex21_01/input.txt";

    public static void main(String[] args) {
        File file = new File(args.length == 0 ? DEFAULT_FILE : args[0]);
        if (!file.exists()) {
            System.err.println("File not found");
            return;
        }
        List<String> list = new ArrayList<>();
        LineFilterReader reader = null;
        try {
            reader = new LineFilterReader(new FileReader(file));
            list = reader.readLine();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (reader != null)
                    reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        Collections.sort(list);
        System.out.println("List size: " + list.size());
        for (String s : list)
            System.out.println(s);
    }
}
