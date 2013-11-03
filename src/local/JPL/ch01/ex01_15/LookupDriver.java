/*
 * Copyright(C) 2013 Yutaka Kato
 */
package local.JPL.ch01.ex01_15;

public class LookupDriver {
	
	public static void main(String[] args) {
		
	}
	
	public void processvalues(String[] names, Lookup table) {
		for (int i = 0; i < names.length; i++) {
			Object value = table.find(names[i]);
			if (value != null)  {
				processValue(names[i], value);
			}
		}
	}
	
	public void processValue(String name, Object value) {
		// TODO: not implemented
	}
}
