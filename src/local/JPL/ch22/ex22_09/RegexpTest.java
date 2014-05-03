/*
 * Copyright(C) 2014 Yutaka Kato
 */
package local.JPL.ch22.ex22_09;

@SuppressWarnings("unused")
public class RegexpTest {
    
    private static final String PATTERN1 = "(.*),(.*)";
    private static final String PATTERN2 = "([^,]*),([^,]*)";
    private static final String PATTERN3 = "";
    private static final String PATTERN4 = "";
    
    private static final String SOURCE1 = "1,2";
    private static final String SOURCE2 = "12345678901234567890,12345678901234567890";
    
    private static final int MAX_TRY = 10000;
    
    public static void main(String[] args) {
        System.out.println("p1s1: " + exec(PATTERN1, SOURCE1));
        System.out.println("p1s2: " + exec(PATTERN1, SOURCE2));
        System.out.println("p2s1: " + exec(PATTERN2, SOURCE1));
        System.out.println("p2s2: " + exec(PATTERN2, SOURCE2));
    }
    
    private static long exec(String pattern, String source) {
        long start = System.currentTimeMillis();
        for (int i = 0; i < MAX_TRY; i++)
            source.matches(pattern);
        long end = System.currentTimeMillis();
        return end - start;
    }
}
