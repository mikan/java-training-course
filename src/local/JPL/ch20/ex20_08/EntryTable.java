/*
 * Copyright(C) 2014 Yutaka Kato
 */
package local.JPL.ch20.ex20_08;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.List;

public class EntryTable {

    public static void main(String[] args) {
        if (args.length == 0)
            throw new IllegalArgumentException("specify name of file");
        List<Long> table = new ArrayList<>();
        RandomAccessFile file;
        try {
            file = new RandomAccessFile(new File(args[0]), "r");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return;
        }
        try {
            // Read entries
            String line;
            boolean first = true;
            while ((line = file.readLine()) != null) {
                if (line.startsWith("%%")) {
                    first = true;
                    continue;                    
                }
                if (first)
                    table.add(file.getFilePointer() - line.length() - 1);
                first = false;
            }
            
            // Print table
            for (Long l : table)
                System.out.println("Entry Position: " + l);
            
            // Choice target
            int target = (int) (Math.random() * 1000) % table.size();
            
            // Print target
            file.seek(table.get(target));
            while ((line = file.readLine()) != null) {
                if (line.startsWith("%%"))
                    break;
                System.out.println(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                file.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        // %%で始まる行で分割されているエントリーを持つファイルを読み込み、
        // 各エントリーの開始位置を持つテーブルファイルを作成するプログラムを書きなさい。
        // そして、そのテーブルを使用してランダムにエントリーを表示するプログラムを作成しなさい。
    }
}
