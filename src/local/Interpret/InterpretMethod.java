/*
 * Copyright(C) 2014 Yutaka Kato
 */
package local.Interpret;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class InterpretMethod implements Comparable<InterpretMethod> {

    private final Method method;
    private final Object object;
    private final Class<?> cls;
    private final List<String> superClassNames;

    public InterpretMethod(Method method, ObjectElement element) {
        this.method = method;
        this.object = element.getObject();
        this.cls = object.getClass();
        superClassNames = new ArrayList<>();
        Class<?> c = cls;
        while (true) {
            c = c.getSuperclass();
            if (c.equals(Object.class))
                break;
            superClassNames.add(c.getSimpleName());
        }
    }

    public Method getMethod() {
        return method;
    }

    public String getFillName() {
        return method.toString();
    }

    public String getShortName() {
        String name = method.toString().replaceAll("java.lang.", "")
                .replaceAll(cls.getPackage().getName() + "\\.", "")
                .replaceAll(cls.getSimpleName() + "\\.", "");
        for (String s : superClassNames)
            name = name.replaceAll(s + "\\.", "");
        if (name.contains(" throws "))
            name = name.substring(0, name.indexOf(" throws "));
        
        return name;
    }

    public Object getObject() {
        return object;
    }

    @Override
    public boolean equals(Object obj) {
        return method.toString().equals(obj.toString());
    }

    @Override
    public int hashCode() {
        return method.toString().hashCode();
    }

    @Override
    public String toString() {
        return getShortName();
    }

    @Override
    public int compareTo(InterpretMethod o) {
        return method.getName().compareTo(o.getMethod().getName());
    }
}
