/*
 * Copyright(C) 2014 Yutaka Kato
 */
package local.JPL.ch16.ex16_04;

import java.lang.annotation.Annotation;

public class PrintAnnotations {
    public static void main(String[] args) {
        if (args == null || args.length == 0)
            return;
        try {
            for (Annotation a : Class.forName(args[0]).getAnnotations())
                System.out.println(a);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
