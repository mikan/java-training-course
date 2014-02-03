/*
 * Copyright(C) 2014 Yutaka Kato
 */
package local.JPL.ch13.ex13_01;

/**
 * String Utilities
 */
public class StringUtil {

    /**
     * Count characters from source string. If source is null, returns 0.
     * 
     * @param source Source string
     * @param ch Character
     * @return count
     */
    public int countChar(String source, char ch) {
        if (source == null)
            return 0;
        int count = 0;
        for (int i = 0; i < source.length(); i++) {
            if (source.charAt(i) == ch)
                count++;
        }
        return count;
    }
}
