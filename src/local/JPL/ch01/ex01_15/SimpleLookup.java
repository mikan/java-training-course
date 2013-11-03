/*
 * Copyright(C) 2013 Yutaka Kato
 */
package local.JPL.ch01.ex01_15;

public class SimpleLookup implements Lookup {

	private String[] names;
	private Object[] values;
	
	@Override
	public Object find(String name) {
		for (int i = 0; i < names.length; i++) {
			if (names[i].equals(name)) {
				return values[i];
			}
		}
		return null;	// 見つからなかった
	}

	@Override
	public boolean add(Object item) {
		for (int i = 0; i < values.length; i++) {
			if (values[i] == null) {
				values[i] = item;
				return true;
			}
		}
		return false;	// 追加できなかった
	}

	@Override
	public boolean remove(Object item) {
		for (int i = 0; i < values.length; i++) {
			if (values[i].equals(item)) {
				values[i] = null;
				return true;
			}
		}
		return false;	// 削除できなかった
	}
}
