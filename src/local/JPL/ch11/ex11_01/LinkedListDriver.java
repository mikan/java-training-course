/*
 * Copyright(C) 2013-2014 Yutaka Kato
 */
package local.JPL.ch11.ex11_01;

/** LinkedList のテストドライバです。 */
public class LinkedListDriver {

	public static void main(String[] args) {
		
		LinkedList<String> root = new LinkedList<>(new String("Object-A"), null);
		root.getBody();
		System.out.println(root.getBody());
		root.setNext(new LinkedList<>(new String("Object-B"), null));
		System.out.println(root.getNext().getBody());
		root.getNext().setNext(new LinkedList<>(new String("Object-C"), null));
		System.out.println(root.getNext().getNext().getBody());
	}

}
