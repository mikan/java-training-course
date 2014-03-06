/*
 * Copyright(C) 2014 Yutaka Kato
 */
package local.Interpret;

import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class InterpretArray {

	private final Object object;
	private final String name;
	private final int length;

	public InterpretArray(Object object, String name, int length) {
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

	public InterpretObject getObjectElementAt(int index) {
		return new InterpretObject(Array.get(object, index), name + "[" + index
				+ "]");
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
	
	   /**
     * Get all fields.
     * 
     * @return List of fields
     */
    public List<InterpretField> getFields() {
        Set<InterpretField> fieldSet = new HashSet<>();
        for (Field f : object.getClass().getFields())
            fieldSet.add(new InterpretField(f, new InterpretObject(this, name)));
        for (Field f : object.getClass().getDeclaredFields())
            fieldSet.add(new InterpretField(f, new InterpretObject(this, name)));
        return new ArrayList<>(fieldSet);
    }

    /**
     * Get all methods.
     * 
     * @return List of methods
     */
    public List<InterpretMethod> getMethods() {
        Set<InterpretMethod> methodSet = new HashSet<>();
        for (Method m : object.getClass().getMethods())
            methodSet.add(new InterpretMethod(m, new InterpretObject(this, name)));
        for (Method m : object.getClass().getDeclaredMethods())
            methodSet.add(new InterpretMethod(m, new InterpretObject(this, name)));
        List<InterpretMethod> methodList = new ArrayList<>(methodSet);
        Collections.sort(methodList);
        return methodList;
    }
}
