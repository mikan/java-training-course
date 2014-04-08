/*
 * Copyright(C) 2013, 2014 Yutaka Kato
 */
package local.JPL.ch20.ex20_07;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class Attr {
    private final String name;
    private Object value = null;

    /** @since ch03 */
    public Attr(String name) {
        this.name = name;
    }

    /** @since ch03 */
    public Attr(String name, Object value) {
        this.name = name;
        this.value = value;
    }

    /** @since ch20 */
    public Attr(String name, InputStream in) throws IOException {
        this.name = name;
        DataInputStream input = new DataInputStream(in);
        value = input.readInt();
        input.close();
    }

    /** @since ch03 */
    public String getName() {
        return name;
    }

    /** @since ch03 */
    public Object getValue() {
        return value;
    }

    /** @since ch03 */
    public Object setValue(Object newValue) {
        Object oldVal = value;
        value = newValue;
        return oldVal;
    }

    /** @since ch20 */
    public void writeData(String file, Object value) throws IOException {
        DataOutputStream out = new DataOutputStream(new FileOutputStream(file));
        out.writeBytes(value.toString());
        out.close();
    }

    @Override
    public String toString() {
        return name + "='" + value + "'";
    }
}
