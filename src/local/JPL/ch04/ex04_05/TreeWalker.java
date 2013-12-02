/*
 * Copyright(C) 2013 Yutaka Kato
 */
package local.JPL.ch04.ex04_05;

public abstract class TreeWalker {
    public final void search(TreeNode root, String key) {
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
    public abstract TreeNode next();
    public abstract boolean isEmpty();
    public abstract void enter(TreeNode n);
}
