/*
 * Copyright(C) 2013 Yutaka Kato
 */
package local.JPL.ch05.ex05_01;

public class Attr {
    private final String name;
    private Object value = null;

    public Attr() {
        name = "(empty)";
    }

    public Attr(String name) {
        this.name = name;
    }

    public Attr(String name, Object value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public Object getValue() {
        return value;
    }

    public Object setValue(Object newValue) {
        Object oldVal = value;
        value = newValue;
        return oldVal;
    }

    @Override
    public String toString() {
        return name + "='" + value + "'";
    }
    
    // ?
    public interface Attributed {
        void add(Attr newAttr);
        Attr find(String attrName);
        Attr remove(String attrName);
        java.util.Iterator<Attr> attrs();
    }
}
