/*
 * Copyright(C) 2014 Yutaka Kato
 */
package local.Interpret;

import java.lang.reflect.Field;

public class InterpretField implements Comparable<InterpretField> {

    private final Field field;
    private final Object object;
    private final Class<?> cls;

    public InterpretField(Field field, ObjectElement element) {
        this.field = field;
        this.object = element.getObject();
        this.cls = object.getClass();
    }

    public Field getField() {
        return field;
    }

    public String getFullName() {
        return field.toString();
    }

    public String getShortName() {
        return field.toString().replaceAll("java.lang.", "")
                .replaceAll(cls.getName() + ".", "")
                .replaceAll(cls.getPackage().getName() + ".", "");
    }

    public Object getObject() {
        return object;
    }

    @Override
    public boolean equals(Object obj) {
        return field.toString().equals(obj.toString());
    }

    @Override
    public int hashCode() {
        return field.toString().hashCode();
    }

    @Override
    public String toString() {
        return getShortName();
    }

    @Override
    public int compareTo(InterpretField o) {
        return field.getName().compareTo(o.getField().getName());
    }
}
