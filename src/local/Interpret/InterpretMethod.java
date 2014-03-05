/*
 * Copyright(C) 2014 Yutaka Kato
 */
package local.Interpret;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class InterpretMethod implements Comparable<InterpretMethod> {

	private final Method method;
	private final Object object;
	private final Class<?> cls;
	private final List<String> superClassNames;

	public InterpretMethod(Method method, InterpretObject element) {
		this.method = method;
		this.object = element.getObject();
		this.cls = object.getClass();
		this.method.setAccessible(true);
		superClassNames = new ArrayList<>();
		Class<?> c = cls;
		while (true) {
			c = c.getSuperclass();
			if (c.equals(Object.class))
				break;
			superClassNames.add(c.getSimpleName());
		}
	}

	public String getName() {
		String name = method.toString().replaceAll("java.lang.", "")
				.replaceAll(cls.getPackage().getName() + "\\.", "")
				.replaceAll("Object\\.", "")
				.replaceAll(cls.getSimpleName() + "\\.", "");
		for (String s : superClassNames)
			name = name.replaceAll(s + "\\.", "");
		if (name.contains(" throws "))
			name = name.substring(0, name.indexOf(" throws "));

		return name;
	}

	/** @see Method#invoke(Object, Object...) */
	public Object invoke(Object[] params) throws IllegalAccessException,
			IllegalArgumentException, InvocationTargetException {
		return method.invoke(object, params);
	}
	
	public boolean isReturnVoid() {
		return method.getReturnType().equals(Void.TYPE);
	}
	
	public Class<?>[] getParameterTypes() {
		return method.getParameterTypes();
	}
	
	public boolean hasAnnotation() {
		return method.getAnnotations().length != 0;
	}
	
	public List<String> getAnnotations() {
		List<String> annotations = new ArrayList<>();
		for (Annotation a : method.getAnnotations())
			annotations.add(a.toString());
		return annotations;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null)
			return false;
		return obj instanceof InterpretMethod ? getName().equals(
				((InterpretMethod) obj).getName()) : toString().equals(
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
	public int compareTo(InterpretMethod o) {
		return method.getName().compareTo(o.getMethod().getName());
	}

	private Method getMethod() {
		return method;
	}
}
