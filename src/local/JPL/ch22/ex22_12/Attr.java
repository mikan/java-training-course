/*
 * Copyright(C) 2013-2014 Yutaka Kato
 */
package local.JPL.ch22.ex22_12;

public class Attr<V> {
	private final String name;
	private V value = null;
	
	public Attr(String name) {
		this.name = name;
	}
	
	public Attr(String name, V value) {
		this.name = name;
		this.value = value;
	}
	
	public String getName() {
		return name;
	}
	
	public Object getValue() {
		return value;
	}
	
	public Object setValue(V newValue) {
		Object oldVal = value;
		value = newValue;
		return oldVal;
	}
	
	@Override
	public String toString() {
		return name + "='" + value + "'";
	}
}
