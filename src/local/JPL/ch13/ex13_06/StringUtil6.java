/*
 * Copyright(C) 2014 Yutaka Kato
 */
package local.JPL.ch13.ex13_06;


public class StringUtil6 {

    /**
     * Insert comma. For example, 1000 to 1,000
     * 
     * @param source Source string
     * @throws NullPointerException source is null
     * @return Comma-inserted string
     */
    public static String insertComma(String source) {
        return insertComma(source, ',', 3);
    }
    
    /**
     * Insert separator with specified interval. For example, 1000 to 1,000
     * 
     * @param source Source string
     * @param separator Separator character
     * @param interval Separator interval
     * @throws NullPointerException source is null
     * @throws IllegalArgumentException interval < 1
     * @return Comma-inserted string
     */
    public static String insertComma(String source, char separator, int interval) {
        if (source == null)
            throw new NullPointerException();
        if (interval < 1)
            throw new IllegalArgumentException();
        for (int count = 0, i = source.length() - 1; i > 0; i--)
            if (++count % interval == 0)
                source = source.substring(0, i) + separator + source.substring(i);
        return source;
    }

    public static void main(String[] args) {
        System.out.println(insertComma("100000", ',', 1));
    }
}
