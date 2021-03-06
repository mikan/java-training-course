/*
 * Copyright(C) 2014 Yutaka Kato
 */
package local.JPL.ch12.ex12_01;

/**
 * リンクリストの要素を示します。ジェネリック版。
 */
public class LinkedList<E> {
	
	/** 要素の中身 */
	E element;
	
	/** 次の要素 */
	LinkedList<E> next;
	
	/**
	 * LinkedList を初期化します。
	 * 
	 * @param element 要素
	 * @param nextElement 次の要素
	 * @throws NullPointerException 要素が null の場合
	 */
	public LinkedList(E element, LinkedList<E> next) {
		if (element == null) {
			throw new NullPointerException("Element is null.");
		}
		this.element = element;
		this.next = next;
	}
	
	/**
	 * 本体を取得します。
	 * @return 本体
	 */
	public E getBody() {
		return element;
	}
	
	/**
	 * 次の要素を取得します。
	 * @return 次の要素
	 * @throws ObjectNotFoundException 次の要素がない場合
	 */
	public LinkedList<E> getNext() throws ObjectNotFoundException {
	    if (next == null)
	        throw new ObjectNotFoundException();
		return next;
	}
	
	/**
	 * 次の要素を設定します。
	 * @param next 次の要素
	 */
	public void setNext(LinkedList<E> next) {
		this.next = next;
	}
	
	/**
	 * 次の要素があるか判定します。
	 * @return 次の要素がある場合は ture、ない場合は false
	 */
	public boolean hasNext() {
		return next == null ? false : true;
	}
	
	/**
	 * 要素数を取得します。
	 * @return 要素数
	 */
	public int size() {
		int count = 0;
		if (getBody() == null) {
			return count;
		} else {
			count++;
		}
		LinkedList<E> current = this;
		while (current.hasNext()) {
			count++;
			try {
                current = current.getNext();
            } catch (ObjectNotFoundException e) {
                break;
            }
		}
		return count;
	}
}
