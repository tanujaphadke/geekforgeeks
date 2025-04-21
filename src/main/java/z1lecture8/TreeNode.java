package z1lecture8;


public class TreeNode {
    public int val;
    public TreeNode left;
    public TreeNode right;

    public int color; //0 = black
    public TreeNode parent; //We do not need this
    public int d;
    @Override
    public String toString() {
        return "{\"val\":" + val + ", \"left\":" + left + ", \"right\":" + right
                + "}";
    }

    public TreeNode(int x) {
        val = x;
        color = 1;
    }
}
