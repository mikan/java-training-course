/*
 * Copyright(C) 2014 Yutaka Kato
 */
package local.JPL.ch16.ex16_09;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Member;
import java.lang.reflect.Method;

public class ClassContents {
    private static final String INDENT = "   ";
    private static String input;

    public static void main(String[] args) {
        if (args == null || args.length == 0)
            return;
        input = args[0];
        try {
            Class<?> c = Class.forName(args[0]);
            System.out.println(c + " {");
            printMembers(c.getFields(), c, "");
            printDeclaredMembers(c.getDeclaredFields(), c.getFields(), c, "");
            printMembers(c.getConstructors(), c, "");
            printDeclaredMembers(c.getDeclaredConstructors(),
                    c.getConstructors(), c, "");
            printMembers(c.getMethods(), c, "");
            printDeclaredMembers(c.getDeclaredMethods(), c.getMethods(), c, "");
            if (c.getDeclaredClasses() != null) {
                for (Class<?> dc : c.getDeclaredClasses()) {
                    System.out.println(INDENT
                            + dc.toString().replace(input + "$", "") + " {");
                    printMembers(dc.getFields(), dc, INDENT);
                    printDeclaredMembers(dc.getDeclaredFields(),
                            dc.getFields(), dc, INDENT);
                    printMembers(dc.getConstructors(), dc, INDENT);
                    printDeclaredMembers(dc.getDeclaredConstructors(),
                            dc.getConstructors(), dc, INDENT);
                    printMembers(dc.getMethods(), dc, INDENT);
                    printDeclaredMembers(dc.getDeclaredMethods(),
                            dc.getMethods(), dc, INDENT);
                    System.out.println(INDENT + "}");
                }
            }
            System.out.println("}");
        } catch (ClassNotFoundException e) {
            System.out.println("unknown class: " + input);
        }
    }

    private static void printMembers(Member[] mems, Class<?> c, String prefix) {
        for (Member m : mems) {
            print(m, c, prefix);
        }
    }

    private static void printDeclaredMembers(Member[] mems, Member[] except,
            Class<?> c, String prefix) {
        for (Member m : mems) {
            boolean duplicate = false;
            for (Member em : except) {
                if (m.toString().equals(em.toString())) {
                    duplicate = true;
                    break;
                }
            }
            if (!duplicate)
                print(m, c, prefix);

        }
    }

    private static void print(Member m, Class<?> c, String prefix) {
        if (m.getDeclaringClass() == Object.class)
            return;
        if (m.getDeclaringClass() == c.getClass())
            return;
        if (m instanceof Field)
            for (Annotation a : ((Field) m).getAnnotations())
                System.out.println(prefix + INDENT + a.toString().replaceAll("java.lang.", ""));
        else if (m instanceof Constructor<?>)
            for (Annotation a : ((Constructor<?>) m).getAnnotations())
                System.out.println(prefix + INDENT + a.toString().replaceAll("java.lang.", ""));
        else if (m instanceof Method)
            for (Annotation a : ((Method) m).getAnnotations())
                System.out.println(prefix + INDENT + a.toString().replaceAll("java.lang.", ""));
        String decl = m.toString();
        System.out.print(prefix + INDENT);
        if (m instanceof Constructor<?>)
            System.out.println(decl.replaceAll("java.lang.", "").replaceAll(
                    input.substring(0, input.lastIndexOf('.') + 1), "")
                    + ";");
        else
            System.out.println(decl.replaceAll("java.lang.", "").replaceAll(
                    input + ".", "")
                    + ";");
    }
}