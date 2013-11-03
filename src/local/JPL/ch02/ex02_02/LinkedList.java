/*
 * Copyright(C) 2013 Yutaka Kato
 */
package local.JPL.ch02.ex02_02;

public class LinkedList {
	
	Object element;
	Object nextElement;
	
	/**
	 * LinkedList を初期化します。
	 * 
	 * @param element 要素
	 * @param nextElement 次の要素
	 * @throws NullPointerException 要素が null の場合
	 */
	public LinkedList(Object element, Object nextElement) {
		if (element == null) {
			throw new NullPointerException("Element is null.");
		}
		this.element = element;
		this.nextElement = nextElement;
	}
	
	public Object getBody() {
		return element;
	}
	
	public Object getNext() {
		return nextElement;
	}
	
	public boolean isTail() {
		return nextElement == null;
	}
}
