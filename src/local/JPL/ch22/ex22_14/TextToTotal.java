/*
 * Copyright(C) 2013-2014 Yutaka Kato
 */
package local.JPL.ch22.ex22_14;

import java.util.StringTokenizer;

public class TextToTotal {
    
    public static void main(String[] args) {
        System.out.println(new TextToTotal().total("1.0 1.0000 -1.5 +1.5 1.00000000001"));
    }

    public double total(String str) {
        StringTokenizer tokenizer = new StringTokenizer(str, " ");
        double total = 0;
        while (tokenizer.hasMoreTokens())
            total += Double.valueOf(tokenizer.nextToken());
        return total;
    }
}
