/*
 * Copyright(C) 2013 Yutaka Kato
 */
package local.JPL.ch04.ex04_05;

import java.util.ArrayDeque;
import java.util.Deque;

public class TreeWalkerDepth extends TreeWalker {
    
    Deque<TreeNode> stack = new ArrayDeque<TreeNode>();

    @Override
    public TreeNode next() {
        return stack.pop();
    }

    @Override
    public boolean isEmpty() {
        return stack.isEmpty();
    }

    @Override
    public void enter(TreeNode n) {
        stack.push(n);
    }
    
}
