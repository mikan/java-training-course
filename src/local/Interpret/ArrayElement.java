/*
 * Copyright(C) 2014 Yutaka Kato
 */
package local.Interpret;

import java.lang.reflect.Array;

public class ArrayElement {

    private final Object object;
    private final String name;
    private final int length;
    public ArrayElement(Object object, String name, int length) {
        this.object = object;
        this.name = name;
        this.length = length;
    }
    
    public Object getObject() {
        return object;
    }
    
    public Object getObjectAt(int index) {
        return Array.get(object, index);
    }
    
    public ObjectElement getObjectElementAt(int index) {
        return new ObjectElement(Array.get(object, index), name + "[" + index + "]");
    }
    
    public void setObjectElementAt(int index, Object value) {
        Array.set(object, index, value);
    }
    
    public String getName() {
        return name;
    }
    
    public int length() {
        return length;
    }
}
