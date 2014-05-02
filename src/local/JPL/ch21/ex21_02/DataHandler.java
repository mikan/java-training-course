/*
 * Copyright(C) 2014 Yutaka Kato
 */
package local.JPL.ch21.ex21_02;

import java.io.File;
import java.lang.ref.WeakReference;
import java.util.WeakHashMap;

public class DataHandler {
    private WeakReference<File> lastFile;
    private WeakHashMap<Integer, byte[]> lastDataMap = new WeakHashMap<>();

    byte[] readFile(File file) {
        byte[] data;

        if (file.equals(lastFile.get())) {
            data = lastDataMap.get(1);
            if (data != null)
                return data;
        }

        data = readBytesFromFile(file);
        lastFile = new WeakReference<File>(file);
        lastDataMap.put(1, data);
        return data;
    }

    byte[] readBytesFromFile(File file) {
        return null;
    }
}
