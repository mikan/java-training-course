/*
 * Copyright(C) 2013 Yutaka Kato
 */
package local.JPL.ch01.ex01_15;

public interface Lookup {
	
	/**
	 * name と関連付けされた値を返す。そのような値がなければ null を返す。
	 * 
	 * @param name 名前
	 * @return 結果
	 */
	Object find(String name);
	
	/**
	 * 要素を追加します。
	 * 
	 * @param item 要素
	 * @return 追加できた場合 true、できなかった場合 false
	 */
	boolean add(Object item);
	
	/**
	 * 要素を削除します。
	 * 
	 * @param item 要素
	 * @return 削除できた場合 true、できなかった場合 false
	 */
	boolean remove(Object item);
}
