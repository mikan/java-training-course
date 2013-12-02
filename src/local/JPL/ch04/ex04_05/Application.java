/*
 * Copyright(C) 2013 Yutaka Kato
 */
package local.JPL.ch04.ex04_05;

public class Application implements Drawable {
    
    public static void main(String[] args) {
        TreeNode rootNode = new TreeNode("root");
        rootNode.addChild(new TreeNode("Node 1.0"));
        rootNode.addChild(new TreeNode("Node 2.0"));
        TreeNode node3 = new TreeNode("Node 3.0");
        node3.addChild(new TreeNode("Node 3.1"));
        node3.addChild(new TreeNode("Node 3.2"));
        node3.addChild(new TreeNode("Node 3.3"));
        node3.addChild(new TreeNode("Node 3.4"));
        node3.addChild(new TreeNode("Node 3.5"));
        rootNode.addChild(node3);
        rootNode.addChild(new TreeNode("Node 4.0"));
        rootNode.addChild(new TreeNode("Node 5.0"));
        rootNode.addChild(new TreeNode("Node 6.0"));
        new TreeWalkerDepth().search(rootNode, "Node 3.5");
        new TreeWalkerWidth().search(rootNode, "Node 3.5");
    }

    @Override
    public void draw() {
        System.out.println("draw()");
    }
}
