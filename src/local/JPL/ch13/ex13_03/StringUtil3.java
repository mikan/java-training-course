/*
 * Copyright(C) 2014 Yutaka Kato
 */
package local.JPL.ch13.ex13_03;

import java.util.ArrayList;
import java.util.List;

/**
 * String Utilities
 */
public class StringUtil3 {

    /** 教科書の delimitedString */
    public static String delimitedString(String from, char start, char end) {
        int startPos = from.indexOf(start);
        int endPos = from.lastIndexOf(end);
        if (startPos == -1) // 開始文字が見つからない
            return null;
        else if (endPos == -1) // 終了文字が見つからない
            return from.substring(startPos);
        else if (startPos > endPos) // 開始文字が終了文字の後にある
            return null;
        else
            // 開始文字と終了文字が見つかった
            return from.substring(startPos, endPos + 1);
    }

    /** 配列版 delimitedString */
    public static String[] delimitedStrings(String from, char start, char end) {
        if (from == null)
            throw new NullPointerException();
        String temp = delimitedString(from, start, end);
        List<String> result = new ArrayList<>();
        while (true) {
            int startPos = temp.indexOf(start);
            int endPos = temp.indexOf(end);
            if (startPos == -1 || endPos == -1)
                break;
            result.add(temp.substring(startPos, endPos + 1));
            temp = temp.substring(endPos + 1);
        }
        return result.toArray(new String[result.size()]);
    }
    
    public static void main(String[] args) {
        for (String s : delimitedStrings("x<<abc>y<abdc>z<aaaaaaaabc>X<><<", '<', '>'))
            System.out.println(s);
    }
}
