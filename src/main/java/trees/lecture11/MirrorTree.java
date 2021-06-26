package trees.lecture11;

import trees.lecture8.TreeNode;

public class MirrorTree {
    //http://www.geeksforgeeks.org/write-an-efficient-c-function-to-convert-a-tree-into-its-mirror-tree/
    //Mirror a Tree
    public static TreeNode mirrorTree(TreeNode node) {
        if (node == null) {
            return null;
        }
        TreeNode left = mirrorTree(node.left);
        TreeNode right = mirrorTree(node.right);
        node.left = right;
        node.right = left;
        return node;

    }
}
