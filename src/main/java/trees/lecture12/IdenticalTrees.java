package trees.lecture12;

import trees.lecture8.TreeNode;

public class IdenticalTrees {
    /**
     *https://www.geeksforgeeks.org/write-c-code-to-determine-if-two-trees-are-identical/
     * @param root1
     * @param root2
     * @return
     */
    public boolean areIdenticalTrees(TreeNode root1, TreeNode root2) {
        if (root1 == null && root2 == null) {
            return true;
        }
        if ((root1 != null && root2 != null) &&  (root1.val != root2.val)) {
            return false;
        }
        if ((root1 == null || root2 == null)){
            return false;
        }
        boolean leftIdentical = areIdenticalTrees(root1.left, root2.left);
        boolean rightIdentical = areIdenticalTrees(root1.right, root2.right);

        return leftIdentical && rightIdentical;
    }

}
