/*
 * Copyright(C) 2014 Yutaka Kato
 */
package local.JPL.ch16.ex16_03;

import java.lang.reflect.Member;

public class ClassContents {
    public static void main(String[] args) {
        if (args == null || args.length == 0)
            return;
        try {
            Class<?> c = Class.forName(args[0]);
            System.out.println(c);
            printMembers(c.getFields(), c);
            printDeclaredMembers(c.getDeclaredFields(), c.getFields(), c);
            printMembers(c.getConstructors(), c);
            printDeclaredMembers(c.getDeclaredConstructors(),
                    c.getConstructors(), c);
            printMembers(c.getMethods(), c);
            printDeclaredMembers(c.getDeclaredMethods(), c.getMethods(), c);
        } catch (ClassNotFoundException e) {
            System.out.println("unknown class: " + args[0]);
        }
    }

    private static void printMembers(Member[] mems, Class<?> c) {
        for (Member m : mems) {
            print(m, c);
        }
    }

    private static void printDeclaredMembers(Member[] mems, Member[] except,
            Class<?> c) {
        for (Member m : mems) {
            boolean duplicate = false;
            for (Member em : except) {
                if (m.toString().equals(em.toString())) {
                    duplicate = true;
                    break;
                }
            }
            if (!duplicate)
                print(m, c);
        }
    }

    private static void print(Member m, Class<?> c) {
        if (m.getDeclaringClass() == Object.class)
            return;
        if (m.getDeclaringClass() == c.getClass())
            return;
        String decl = m.toString();
        System.out.print("   ");
        System.out.println(strip(decl, "java.lang."));
    }

    private static String strip(String source, String delete) {
        return source.replaceAll(delete, "");
    }
}