/*
 * Copyright(C) 2014 Yutaka Kato
 */
package local.JPL.ch13.ex13_02;


/**
 * String Utilities
 */
public class StringUtil2 {

    /**
     * Count text from source string.
     * 
     * @param source Source string
     * @param text Search string
     * @throws NullPointerException source or text is null
     * @throws IllegalArgumentException source or text is empty
     * @return count
     */
    public int countSubstring(String source, String text) {

        if (source == null || text == null)
            throw new NullPointerException("source or text is null");
        if (source.isEmpty() || text.isEmpty())
            throw new IllegalArgumentException("source or text is empty");

        // text is larger than source
        if (source.length() < text.length())
            return 0;

        // Perfect match!
        if (source.equals(text))
            return 1;

        int count = 0;
        for (int i = 0; i < source.length() - text.length() + 1; i++)
            if (source.substring(i, i + text.length()).equals(text))
                count++;
        return count;

    }
}
