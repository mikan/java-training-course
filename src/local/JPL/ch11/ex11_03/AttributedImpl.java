/*
 * Copyright(C) 2014 Yutaka Kato
 */
package local.JPL.ch11.ex11_03;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class AttributedImpl implements Attributed<Integer> {

    protected Map<String, Attr<Integer>> attrTable = new HashMap<String, Attr<Integer>>();
    
    @Override
    public void add(Attr<Integer> newAttr) {
        attrTable.put(newAttr.getName(), newAttr);
    }

    @Override
    public Attr<Integer> find(String attrName) {
        return attrTable.get(attrName);
    }

    @Override
    public Attr<Integer> remove(String attrName) {
        return attrTable.remove(attrName);
    }

    @Override
    public Iterator<Attr<Integer>> attrs() {
        return attrs();
    }

}
