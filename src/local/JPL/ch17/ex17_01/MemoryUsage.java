/*
 * Copyright(C) 2014 Yutaka Kato
 */
package local.JPL.ch17.ex17_01;

import java.util.HashMap;
import java.util.Map;

public class MemoryUsage {

    private static int num = 0;

    public static void main(String[] args) {

        printUsage();

        Map<String, String> map = new HashMap<String, String>(1);
        for (int i = 0; i < 10000; i++)
            map.put("#" + i, "abc");
        
        printUsage();

        System.gc();

        printUsage();

        System.gc();

        printUsage();

        System.gc();

        printUsage();
    }

    public static void printUsage() {
        Runtime runtime = Runtime.getRuntime();
        double par = runtime.freeMemory() / (double) runtime.totalMemory();
        System.out.printf("%d %f%%%n", ++num, par * 100);
    }
    
    /* 
     * <pre>
     * 1 94.994841%
     * 2 79.713074%
     * 3 90.575402%
     * 4 90.575402%
     * 5 90.573642%
     * </pre>
     */
}
