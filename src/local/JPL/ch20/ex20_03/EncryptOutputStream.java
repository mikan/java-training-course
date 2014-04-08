/*
 * Copyright(C) 2014 Yutaka Kato
 */
package local.JPL.ch20.ex20_03;

import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class EncryptOutputStream extends FilterOutputStream {

    private final int key;

    public EncryptOutputStream(OutputStream out, int key) {
        super(out);
        this.key = key;
    }

    @Override
    public void write(int data) throws IOException {
        int enccypted = data ^ key;
        super.write(enccypted);
    }
}
