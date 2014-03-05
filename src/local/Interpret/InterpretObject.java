/*
 * Copyright(C) 2014 Yutaka Kato
 */
package local.Interpret;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class InterpretObject {
	private final Object object;
	private final String name;

	public InterpretObject(Object object, String name) {
		this.object = object;
		this.name = name;
	}

	public Object getObject() {
		return object;
	}

	public String getName() {
		return name;
	}

	/**
	 * Get all fields.
	 * 
	 * @return List of fields
	 */
	public List<InterpretField> getFields() {
		Set<InterpretField> fieldSet = new HashSet<>();
		for (Field f : object.getClass().getFields())
			fieldSet.add(new InterpretField(f, this));
		for (Field f : object.getClass().getDeclaredFields())
			fieldSet.add(new InterpretField(f, this));
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
			methodSet.add(new InterpretMethod(m, this));
		for (Method m : object.getClass().getDeclaredMethods())
			methodSet.add(new InterpretMethod(m, this));
		List<InterpretMethod> methodList = new ArrayList<>(methodSet);
		Collections.sort(methodList);
		return methodList;
	}

	@Override
	public String toString() {
		return name;
	}
}
