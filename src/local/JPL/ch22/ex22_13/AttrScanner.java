package local.JPL.ch22.ex22_13;

/*
 * Copyright(C) 2014 Yutaka Kato
 */
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.Iterator;
import java.util.Scanner;

public class AttrScanner {

    private static final String PATH = "src/local/JPL/ch22/ex22_13/input.txt";

    public static void main(String[] args) throws IOException {
        BufferedReader in = null;
        try {
            in = new BufferedReader(new FileReader(PATH));
            Attributed<String> attrs = readAttrs(in);
            Iterator<Attr<String>> it = attrs.attrs();
            while (it.hasNext())
                System.out.println(it.next());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (in != null)
                in.close();
        }
    }

    public static Attributed<String> readAttrs(Reader source) {
        Attributed<String> attrs = new AttributedImpl();
        Scanner in = null;
        try {
            in = new Scanner(source);
            while (in.hasNextLine()) {
                String[] kv = in.nextLine().split("=");
                try {
                    attrs.add(new Attr<>(kv[0], kv[1]));                    
                } catch (ArrayIndexOutOfBoundsException e) {
                    throw e;
                }
            }
        } finally {
            if (in != null)
                in.close();
        }
        return attrs;
    }

}
