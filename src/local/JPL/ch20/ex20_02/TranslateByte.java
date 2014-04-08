/*
 * Copyright(C) 2014 Yutaka Kato
 */
package local.JPL.ch20.ex20_02;

import java.io.FilterReader;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;

public class TranslateByte {

    public static void main(String[] args) {
        if (args.length == 0)
            args = new String[] { "b", "B", "C" };
        StringReader in = new StringReader(args[0]);
        TranslateByteFilterReader reader = new TranslateByteFilterReader(in,
                (byte) args[1].charAt(0), (byte) args[2].charAt(0));
        int c;
        try {
            while ((c = reader.read()) != -1)
                System.out.print((char) c);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

class TranslateByteFilterReader extends FilterReader {

    private final byte from;
    private final byte to;

    public TranslateByteFilterReader(Reader in, byte from, byte to) {
        super(in);
        this.from = from;
        this.to = to;
    }

    @Override
    public int read() throws IOException {
        int c = super.read();
        return (c == from ? to : c);
    }

    @Override
    public int read(char[] buf, int offset, int count) throws IOException {
        int nread = super.read(buf, offset, count);
        int last = offset + nread;
        for (int i = offset; i < last; i++)
            buf[i] = Character.toUpperCase(buf[i]);
        return nread;
    }
}
