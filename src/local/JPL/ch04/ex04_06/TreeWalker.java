/*
 * Copyright(C) 2013 Yutaka Kato
 */
package local.JPL.ch04.ex04_06;

public interface TreeWalker {
    public void search(TreeNode root, String key);
    public TreeNode next();
    public boolean isEmpty();
    public void enter(TreeNode n);
}
