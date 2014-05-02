/*
 * Copyright(C) 2013-2014 Yutaka Kato
 */
package local.JPL.ch22.ex22_04;


public interface Attributed<V> {
    void add(Attr<V> newAttr);
    Attr<V> find(String attrName);
    Attr<V> remove(String attrName);
    java.util.Iterator<Attr<V>> attrs();

}
