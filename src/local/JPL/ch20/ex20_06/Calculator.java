/*
 * Copyright(C) 2014 Yutaka Kato
 */
package local.JPL.ch20.ex20_06;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class Calculator {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(
                System.in));
        String line;
        Map<String, Integer> map = new HashMap<>();
        while ((line = reader.readLine()) != null) {
            if (line.isEmpty()) {
                for (String key : map.keySet())
                    System.out.println(key + " = " + map.get(key));
                break;
            }
            String[] strs = line.split(" ");
            String name = strs[0];
            String op = strs[1];
            String value = strs[2];
            Integer prev = map.get(name);
            if (prev == null)
                prev = 0;
            switch (op) {
            case "+":
                prev += Integer.parseInt(value);
                break;
            case "-":
                prev -= Integer.parseInt(value);
                break;
            case "=":
                prev = Integer.parseInt(value);
                break;
            default:
                throw new IllegalArgumentException("Undefined op: " + op);
            }
            map.put(name, prev);
        }
    }
}
