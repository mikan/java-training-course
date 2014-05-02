/*
 * Copyright(C) 2014 Yutaka Kato
 */
package local.JPL.ch22.ex22_06;

import java.util.Map;
import java.util.Random;
import java.util.TreeMap;

public class CheckNextGaussian {
    
    private static final int SPLIT = 1;
    private static final int SCALE = 1000;
    private static final int MAX_TRY = 100000;

    public static void main(String[] args) {
        Random random = new Random();
        Map<Integer, Integer> map = new TreeMap<>();
        for (int i = 0; i < MAX_TRY; i++) {
            double num = random.nextGaussian();
            int key = (int) (num * SPLIT);
            Integer count = map.get(key);
            if (count == null)
                count = 0;
            map.put(key, ++count);
        }
        for (int i : map.keySet())
            System.out.printf("[%" + (SPLIT + 1) + "d]%s%n", i , bar(map.get(i)));
        System.out.println("(* = 0~" + SCALE + ")");
    }
    
    private static String bar(int num) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < num; i++)
            if (i % SCALE == 0)
                sb.append("*");
        return sb.toString();
    }
}
