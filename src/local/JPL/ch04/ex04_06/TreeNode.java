/*
 * Copyright(C) 2013 Yutaka Kato
 */
package local.JPL.ch04.ex04_06;

import java.util.ArrayList;
import java.util.List;

public class TreeNode {
    private String value;
    private List<TreeNode> children = new ArrayList<TreeNode>();

    public TreeNode(String value) {
        this.value = value;
    }
   
    /**
     * 子ノードを追加する。
     * @param child 子ノード
     */
    public void addChild(TreeNode child) {
        children.add(child);
    }

    /**
     * 子ノードを取得する。
     * 
     * @return 子ノード一覧
     */
    public List<TreeNode> getChildren() {
        return children;
    }

    @Override
    public String toString() {
        return value;
    }
}
