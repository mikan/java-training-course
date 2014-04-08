/*
 * Copyright(C) 2014 Yutaka Kato
 */
package local.JPL.ch20.ex20_09;

import java.io.File;
import java.io.IOException;

public class FileInfo {

    public static void main(String[] args) {
        for (String name : args) {
            File file = new File(name);
            if (!file.exists()) {
                System.err.println("not exists: " + name);
                break;
            }
            try {
                System.out.println("name: " + name);
                System.out.println("\tgetAbsolutePath(): " + file.getAbsolutePath());
                System.out.println("\tCanonicalPath(): " + file.getCanonicalPath());
                System.out.println("\tgetFreeSpace(): " + file.getFreeSpace());
                System.out.println("\tgetName(): " + file.getName());
                System.out.println("\tgetParent(): " + file.getParent());
                System.out.println("\tgetPath(): " + file.getPath());
                System.out.println("\tgetTotalSpace(): " + file.getTotalSpace());
                System.out.println("\tgetUsableSpace(): " + file.getUsableSpace());
                System.out.println("\tgetAbsoluteFile(): " + file.getAbsoluteFile());
                System.out.println("\tgetCanonicalFile(): " + file.getCanonicalFile());
                System.out.println("\tgetParentFile(): " + file.getParentFile());
                System.out.println("\tisAbsolute(): " + file.isAbsolute());
                System.out.println("\tisDirectory(): " + file.isDirectory());
                System.out.println("\tisFile(): " + file.isFile());
                System.out.println("\tisHidden(): " + file.isHidden());
                System.out.println("\texists(): " + file.exists());
                System.out.println("\tlastModified(): " + file.lastModified());
                System.out.println("\thashCode(): " + file.hashCode());
                System.out.println("\tlength(): " + file.length());
                System.out.println("\ttoString(): " + file.toString());
                System.out.println("\tcanExecute(): " + file.canExecute());
                System.out.println("\tcanRead(): " + file.canRead());
                System.out.println("\tcanWrite(): " + file.canWrite());
                System.out.println("\ttoURI(): " + file.toURI());
                System.out.println("\ttoPath(): " + file.toPath());
                
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

/*
$ java local.JPL.ch20.ex20_09.FileInfo /bin/bash
name: /bin/bash
    getAbsolutePath(): /bin/bash
    CanonicalPath(): /bin/bash
    getFreeSpace(): 11659726848
    getName(): bash
    getParent(): /bin
    getPath(): /bin/bash
    getTotalSpace(): 80000000000
    getUsableSpace(): 11397582848
    getAbsoluteFile(): /bin/bash
    getCanonicalFile(): /bin/bash
    getParentFile(): /bin
    isAbsolute(): true
    isDirectory(): false
    isFile(): true
    isHidden(): false
    exists(): true
    lastModified(): 1382611632000
    hashCode(): 1840881050
    length(): 1228240
    toString(): /bin/bash
    canExecute(): true
    canRead(): true
    canWrite(): false
    toURI(): file:/bin/bash
    toPath(): /bin/bash
 */
