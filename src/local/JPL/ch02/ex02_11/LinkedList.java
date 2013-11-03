/*
 * Copyright(C) 2013 Yutaka Kato
 */
package local.JPL.ch02.ex02_11;

public class LinkedList {

	Object element;
	Object nextElement;

	public LinkedList() {
		element = null;
		nextElement = null;
	}

	/**
	 * LinkedList を初期化します。
	 * 
	 * @param element 要素
	 * @param nextElement 次の要素
	 * @throws NullPointerException 要素が null の場合
	 */
	public LinkedList(Object element) {
		if (element == null) {
			throw new NullPointerException("Element is null.");
		}
		this.element = element;
		nextElement = null;
	}

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
	
	@Override
	public String toString() {
		return element.toString();
	}
}
