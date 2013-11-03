/*
 * Copyright(C) 2013 Yutaka Kato
 */
package local.JPL.ch02.ex02_14;

public class LinkedList {

	private Object element;
	private Object nextElement;

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
	
	public void setBody(Object element) {
		this.element = element;
	}

	public Object getNext() {
		return nextElement;
	}
	
	public void setNext(Object nextElement) {
		this.nextElement = nextElement;
	}

	public boolean isTail() {
		return nextElement == null;
	}
	
	@Override
	public String toString() {
		return element.toString();
	}
}
