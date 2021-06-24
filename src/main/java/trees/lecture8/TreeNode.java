package trees.lecture8;


import java.util.Objects;

public class TreeNode {
    public int val;
    public TreeNode left;
    public TreeNode right;

    //The below are unused fields
    public int color; //0 = black
    public TreeNode parent; //We do not need this
    public int d;


    @Override
    public String toString() {
//        return "{\"val\":" + val + ", \"left\":" + left + ", \"right\":" + right
//               + "}";

        return val + " ";
    }

    public TreeNode(int x) {
        val = x;
        color = 1;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TreeNode)) return false;
        TreeNode treeNode = (TreeNode) o;
        return val == treeNode.val && color == treeNode.color && d == treeNode.d && left.equals(treeNode.left) && right.equals(treeNode.right) && parent.equals(treeNode.parent);
    }

    @Override
    public int hashCode() {
        return Objects.hash(val, left, right, color, parent, d);
    }
}
