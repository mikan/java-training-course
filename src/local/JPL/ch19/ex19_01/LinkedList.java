/*
 * Copyright(C) 2013-2014 Yutaka Kato
 */
package local.JPL.ch19.ex19_01;

/**
 * Provides an element of linked list.
 */
public class LinkedList {
	
	/** Content of this element */
	private Object element;
	
	/** Next element */
	private LinkedList next;
	
	/**
	 * Initialize element.
	 * 
	 * @param element element
	 * @throws NullPointerException if element is null
	 */
	public LinkedList(Object element) {
		if (element == null) {
			throw new NullPointerException("Element is null.");
		}
		this.element = element;
		this.next = null;
	}
	
	/**
	 * Initialize element with next element.
	 * 
	 * @param element element
	 * @param nextElement next element
	 * @throws NullPointerException if element is null.
	 */
	public LinkedList(Object element, LinkedList next) {
		if (element == null) {
			throw new NullPointerException("Element is null.");
		}
		this.element = element;
		this.next = next;
	}
	
	/**
	 * Get content of this element.
	 * 
	 * @return content
	 */
	public Object getBody() {
		return element;
	}
	
	/**
	 * Get next element.
	 * 
	 * @return next element, or null if next element is nothing.
	 */
	public LinkedList getNext() {
		return next;
	}
	
	/**
	 * Set next element.
	 * 
	 * @param next next element
	 */
	public void setNext(LinkedList next) {
		this.next = next;
	}
	
	/**
	 * Check next element.
	 * 
	 * @return true if next element found, false otherwise.
	 */
	public boolean hasNext() {
		return next == null ? false : true;
	}
	
	/**
	 * Get number of elements.
	 * 
	 * @return
	 */
	public int size() {
		int count = 0;
		if (getBody() == null) {
			return count;
		} else {
			count++;
		}
		LinkedList current = this;
		while (current.hasNext()) {
			count++;
			current = current.getNext();
		}
		return count;
	}
	
	@Override
	public String toString() {
		return element.toString();
	}
}
