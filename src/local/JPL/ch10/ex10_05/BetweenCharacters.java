/*
 * Copyright(C) 2014 Yutaka Kato
 */
package local.JPL.ch10.ex10_05;

/** String sequence between two characters */
public class BetweenCharacters {

    /**
     * Print sequence.
     * 
     * @param start Start character
     * @param end End character
     * @return sequence
     */
    public String print(char start, char end) {
        if (start > end) {
            char temp = start;
            start = end;
            end = temp;
        }
        String result = "";
        for (char c = start; c <= end; c++)
            result += String.valueOf(c);
        return result;
    }

    public static void main(String[] args) {
        System.out.println(new BetweenCharacters().print('0', 'z'));
        System.out.println(new BetweenCharacters().print('お', 'あ'));
        System.out.println(new BetweenCharacters().print('一', '三'));
        //System.out.println(new BetweenCharacters().print('加', '藤'));
    }

}
