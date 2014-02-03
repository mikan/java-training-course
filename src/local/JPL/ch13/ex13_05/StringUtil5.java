/*
 * Copyright(C) 2014 Yutaka Kato
 */
package local.JPL.ch13.ex13_05;

public class StringUtil5 {

    /**
     * Insert comma. For example, 1000 to 1,000
     * 
     * @param source Source string
     * @throws NullPointerException source is null
     * @return Comma-inserted string
     */
    public static String insertComma(String source) {
        if (source == null)
            throw new NullPointerException();
        for (int count = 0, i = source.length() - 1; i > 0; i--)
            if (++count % 3 == 0)
                source = source.substring(0, i) + "," + source.substring(i);
        return source;
    }

    public static void main(String[] args) {
        System.out.println(insertComma("100000"));
    }
}
