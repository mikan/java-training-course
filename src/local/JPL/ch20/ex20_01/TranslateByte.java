/*
 * Copyright(C) 2014 Yutaka Kato
 */
package local.JPL.ch20.ex20_01;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class TranslateByte {

    public static void main(String[] args) {
        TranslateByte tb = new TranslateByte();
        InputStream in = System.in;
        OutputStream out = System.out;
        try {
            tb.translate(new String[] { "b", "B" }, in, out);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void translate(String[] input, InputStream in, OutputStream out)
            throws IOException {
        byte from = (byte) input[0].charAt(0);
        byte to = (byte) input[1].charAt(0);
        int b;
        while ((b = in.read()) != -1)
            out.write(b == from ? to : b);
    }

}
