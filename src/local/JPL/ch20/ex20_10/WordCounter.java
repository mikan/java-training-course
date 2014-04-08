/*
 * Copyright(C) 2014 Yutaka Kato
 */
package local.JPL.ch20.ex20_10;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.StreamTokenizer;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class WordCounter {
    public static void main(String[] args) throws IOException {
        StreamTokenizer tokenizer = new StreamTokenizer(new FileReader(new File(
                args[0])));
        Map<String, Integer> map = new HashMap<>();
        int token;
        while ((token = tokenizer.nextToken()) != StreamTokenizer.TT_EOF) {
            if (token == StreamTokenizer.TT_WORD) {
                String value = tokenizer.sval;
                Integer c = map.get(value);
                if (c == null)
                    map.put(value, 1);
                else {
                    map.put(value, ++c);
                }
            }
        }
        for (Entry<String, Integer> e : map.entrySet())
            System.out.println(e.getKey() + ": " + e.getValue());
    }
}
