/*
 * Copyright(C) 2013 Yutaka Kato
 */
package local.JPL.ch02.ex02_07;

import java.util.ArrayList;
import java.util.List;

/**
 * リンクリストの要素を示します。
 */
public class LinkedList {
	
	/** 要素の中身 */
	Object element;
	
	/** 次の要素 */
	LinkedList next;
	
	/**
	 * LinkedList を初期化します。
	 * 
	 * @param element 要素
	 * @param nextElement 次の要素
	 * @throws NullPointerException 要素が null の場合
	 */
	public LinkedList(Object element, LinkedList next) {
		if (element == null) {
			throw new NullPointerException("Element is null.");
		}
		this.element = element;
		this.next = next;
	}
	
	   /**
     * main
     * 
     * @param args コマンドライン引数
     */
    public static void main(String[] args) {
        List<Vehicle> vehicles = new ArrayList<Vehicle>();
        vehicles.add(new Vehicle("mycar"));
        vehicles.add(new Vehicle("mytruck"));
        vehicles.add(new Vehicle("mysportscar"));
        vehicles.add(new Vehicle());
        vehicles.add(new Vehicle());
        for (Vehicle v : vehicles) {
            System.out.println(v);
        }
    }
	
	/**
	 * 本体を取得します。
	 * @return 本体
	 */
	public Object getBody() {
		return element;
	}
	
	/**
	 * 次の要素を取得します。次の要素がない場合は null が返されます。
	 * @return 次の要素
	 */
	public LinkedList getNext() {
		return next;
	}
	
	/**
	 * 次の要素を設定します。
	 * @param next 次の要素
	 */
	public void setNext(LinkedList next) {
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
}
