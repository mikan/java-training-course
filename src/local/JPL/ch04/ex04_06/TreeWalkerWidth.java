/*
 * Copyright(C) 2013 Yutaka Kato
 */
package local.JPL.ch04.ex04_06;

import java.util.ArrayDeque;
import java.util.Deque;

public class TreeWalkerWidth implements TreeWalker {
    
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

    @Override
    public void search(TreeNode root, String key) {
        enter(root);
        while (!isEmpty()) {
            TreeNode node = next();
            System.out.println("cur: " + node.toString());
            if (node.toString().equals(key)) {
                break;
            }
            for (TreeNode n : node.getChildren()) {
                enter(n);
            }
        }
    }
    
}
