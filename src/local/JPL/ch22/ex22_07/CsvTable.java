/*
 * Copyright(C) 2014 Yutaka Kato
 */
package local.JPL.ch22.ex22_07;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.MatchResult;
import java.util.regex.Pattern;

public class CsvTable {

    private static final String PATH = "src/local/JPL/ch22/ex22_07/input.csv";

    public static void main(String[] args) {
        BufferedReader input = null;
        try {
            input = new BufferedReader(new FileReader(PATH));
            List<String[]> result = new CsvTable().readCSVTable(input, 3);
            for (String[] values : result) {
                for (int i = 0; i < values.length; i++)
                    System.out.print(values[i] + "\t");
                System.out.println();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public List<String[]> readCSVTable(Readable source, int nCells)
            throws IOException {
        if (nCells < 1)
            throw new IllegalArgumentException();
        Scanner in = new Scanner(source);
        List<String[]> vals = new ArrayList<>();
        try {
            String exp = "^(.*)";
            for (int i = 1; i < nCells; i++)
                exp += ",(.*)";
            Pattern pat = Pattern.compile(exp, Pattern.MULTILINE);
            while (in.hasNextLine()) {
                String line = in.findInLine(pat);
                if (line != null) {
                    String[] cells = new String[nCells];
                    MatchResult match = in.match();
                    for (int i = 0; i < cells.length; ++i)
                        cells[i] = match.group(i + 1);
                    vals.add(cells);
                    in.nextLine();
                } else {
                    throw new IOException("input format error");
                }
            }
            IOException ex = in.ioException();
            if (ex != null)
                throw ex;            
        } finally {
            in.close();
        }
        return vals;
    }
}
