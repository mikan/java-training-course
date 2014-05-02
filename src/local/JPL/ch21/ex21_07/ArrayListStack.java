/*
 * Copyright(C) 2014 Yutaka Kato
 */
package local.JPL.ch21.ex21_07;

import java.util.ArrayList;
import java.util.EmptyStackException;
import java.util.List;

/**
 * Stack implementation with ArrayList data structure.
 *
 * @param <E> type of elements
 */
public class ArrayListStack<E> {
    
    private List<E> list = new ArrayList<>();
    
    /**
     * Push operation.
     * 
     * @param element insert element
     */
    public E push(E element) {
        list.add(element);
        return element;
    }
    
    /**
     * Pop operation.
     * 
     * @return element
     * @throws EmptyStackException if this stack is empty
     */
    public E pop() {
        if (list.isEmpty())
            throw new EmptyStackException();
        return list.remove(list.size() - 1);
    }
    
    /** @see ArrayList#indexOf(Object) */
    public int search(E element) {
        return list.indexOf(element);
    }
}

/**
 * Stack implementation with ArrayList extended.
 *
 * @param <E> type of elements
 */
@SuppressWarnings("serial")
class ArrayListExtendedStack<E> extends ArrayList<E> {
    
    /**
     * Push operation.
     * 
     * @param element insert element
     */
    public E push(E element) {
        add(element);
        return element;
    }
    
    /**
     * Pop operation.
     * 
     * @return element
     * @throws EmptyStackException if this stack is empty
     */
    public E pop() {
        if (isEmpty())
            throw new EmptyStackException();
        return remove(size() - 1);
    }
    
    /** @see ArrayList#indexOf(Object) */
    public int search(E element) {
        return indexOf(element);
    }
}

// Answer: どっちでも良くね？