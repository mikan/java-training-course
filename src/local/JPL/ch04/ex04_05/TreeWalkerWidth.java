/*
 * Copyright(C) 2013 Yutaka Kato
 */
package local.JPL.ch04.ex04_05;

import java.util.ArrayDeque;
import java.util.Deque;

public class TreeWalkerWidth extends TreeWalker {
    
    Deque<TreeNode> queue = new ArrayDeque<TreeNode>();

    @Override
    public TreeNode next() {
        return queue.poll();
    }

    @Override
    public boolean isEmpty() {
        return queue.isEmpty();
    }

    @Override
    public void enter(TreeNode n) {
        queue.offer(n);
    }
    
}
