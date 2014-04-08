/*
 * Copyright(C) 2014 Yutaka Kato
 */
package local.JPL.ch20.ex20_03;

import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;

public class DecryptInputStream extends FilterInputStream {
    private final int key;

    public DecryptInputStream(InputStream in, int key) {
        super(in);
        this.key = key;
    }

    @Override
    public int read() throws IOException {
        int in = super.read();
        if (in != -1)
            in = in ^ key;
        return in;
    }
}
