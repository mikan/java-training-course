/*
 * Copyright(C) 2014 Yutaka Kato
 */
package local.Interpret;

public class ObjectElement {
    private final Object object;
    private final String name;
    public ObjectElement(Object object, String name) {
        this.object = object;
        this.name = name;
    }
    
    public Object getObject() {
        return object;
    }
    
    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return name;
    }
}
