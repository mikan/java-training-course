/*
 * Copyright(C) 2014 Yutaka Kato
 */
package local.JPL.ch22.ex22_04;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Observable;

public class AttributedImpl extends Observable implements Attributed<Integer> {

    protected Map<String, Attr<Integer>> attrTable = new HashMap<String, Attr<Integer>>();

    @Override
    public void add(Attr<Integer> newAttr) {
        attrTable.put(newAttr.getName(), newAttr);
        setChanged();
        notifyObservers();
    }

    @Override
    public Attr<Integer> find(String attrName) {
        return attrTable.get(attrName);
    }

    @Override
    public Attr<Integer> remove(String attrName) {
        Attr<Integer> ret = attrTable.remove(attrName);
        setChanged();
        notifyObservers();
        return ret;
    }

    @Override
    public Iterator<Attr<Integer>> attrs() {
        return attrs();
    }
}
