/*
 * Copyright(C) 2014 Yutaka Kato
 */
package local.Interpret;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;

public class InterpretField implements Comparable<InterpretField> {

    private final Field field;
    private final Object object;
    private final Class<?> cls;

    public InterpretField(Field field, InterpretObject element) {
        this.field = field;
        this.object = element.getObject();
        this.cls = object.getClass();
        this.field.setAccessible(true);
    }

    public String getName() {
	    String name = field.toString().replaceAll("java.lang.", "");
        if (null != cls.getPackage()) {
            name = name.replaceAll(cls.getName() + "\\.", "")
                    .replaceAll(cls.getPackage().getName() + "\\.", "");
        } else {
            name = name.replaceAll("Object\\.", "");
        }
		return name;
	}

    /** @see Field#get(Object) */
    public Object getData() throws IllegalArgumentException,
            IllegalAccessException {
        return Modifier.isStatic(field.getModifiers()) ? field.get(null)
                : field.get(object);
    }

    /** @see Field#set(Object, Object) */
    public void setData(String value) throws NumberFormatException,
            IllegalArgumentException, IllegalAccessException {
        Object target = object;
        if (Modifier.isStatic(field.getModifiers()))
            target = null;
        Class<?> type = field.getType();
        if (type.equals(byte.class))
            field.setByte(target, Byte.parseByte(value));
        else if (type.equals(short.class))
            field.setShort(target, Short.parseShort(value));
        else if (type.equals(int.class))
            field.setInt(target, Integer.parseInt(value));
        else if (type.equals(long.class))
            field.setLong(target, Long.parseLong(value));
        else if (type.equals(float.class))
            field.setFloat(target, Float.parseFloat(value));
        else if (type.equals(double.class))
            field.setDouble(target, Double.parseDouble(value));
        else if (type.equals(char.class))
            field.setChar(target, (char) Integer.parseInt(value));
        else if (type.equals(boolean.class))
            field.setBoolean(target, Boolean.parseBoolean(value));
        else
            field.set(target, value);
    }

    public boolean hasAnnotation() {
        return field.getAnnotations().length != 0;
    }

    public List<String> getAnnotations() {
        List<String> annotations = new ArrayList<>();
        for (Annotation a : field.getAnnotations())
            annotations.add(a.toString());
        return annotations;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null)
            return false;
        return obj instanceof InterpretField ? getName().equals(
                ((InterpretField) obj).getName()) : toString().equals(
                obj.toString());
    }

    @Override
    public int hashCode() {
        return getName().hashCode();
    }

    @Override
    public String toString() {
        return getName();
    }

    @Override
    public int compareTo(InterpretField o) {
        return field.getName().compareTo(o.getField().getName());
    }

    private Field getField() {
        return field;
    }
}
