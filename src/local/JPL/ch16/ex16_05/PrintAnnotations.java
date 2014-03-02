/*
 * Copyright(C) 2014 Yutaka Kato
 */
package local.JPL.ch16.ex16_05;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class PrintAnnotations {
    
    private static Class<?> cls;
    public static void main(String[] args) {
        if (args == null || args.length == 0)
            return;
        try {
            cls = Class.forName(args[0]);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        // Class
        for (Annotation a : cls.getAnnotations())
            System.out.println(a);
        // Fields
        for (Field f : cls.getFields()) {
            System.out.println("   F " + f);
            for (Annotation a : f.getAnnotations())
                System.out.println("      " + a.toString().replace("java.lang.", ""));
        }
        // Constructors
        for (Constructor<?> c : cls.getConstructors()) {
            System.out.println("   C " + c.toString().replace("java.lang.", ""));
            for (Annotation a : c.getAnnotations())
                System.out.println("      " + a.toString().replace("java.lang.", ""));
        }
        // Methods
        for (Method m : cls.getMethods()) {
            System.out.println("   M " + m.toString().replace("java.lang.", ""));
            for (Annotation a : m.getAnnotations())
                System.out.println("      " + a);
        }
    }
}
