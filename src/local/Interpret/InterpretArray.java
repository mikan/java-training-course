/*
 * Copyright(C) 2014 Yutaka Kato
 */
package local.Interpret;

import java.lang.reflect.Array;

public class InterpretArray extends InterpretObject {

	protected final int length;

	public InterpretArray(Object object, String name, int length) {
	    super(object, name);
		this.length = length;
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

	public int length() {
		return length;
	}
}
