/*
 * Copyright(C) 2014 Yutaka Kato
 */
package local.JPL.ch22.ex22_12;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Observable;

public class AttributedImpl extends Observable implements Attributed<String> {

    protected Map<String, Attr<String>> attrTable = new HashMap<String, Attr<String>>();

    @Override
    public void add(Attr<String> newAttr) {
        attrTable.put(newAttr.getName(), newAttr);
        setChanged();
        notifyObservers();
    }

    @Override
    public Attr<String> find(String attrName) {
        return attrTable.get(attrName);
    }

    @Override
    public Attr<String> remove(String attrName) {
        Attr<String> ret = attrTable.remove(attrName);
        setChanged();
        notifyObservers();
        return ret;
    }

    @Override
    public Iterator<Attr<String>> attrs() {
        return attrTable.values().iterator();
    }
}
