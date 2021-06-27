package trees.lecture11;


import trees.lecture8.PopulateTree;
import trees.lecture8.TreeNode;

/*
Given the root to a binary tree root, return a list of two numbers where the first number is the
number of leaves in the tree and the second number is the number of internal (non-leaf) nodes.


 */
public class LeavesAndNonLeaves {

/*
Returns an array of integer where the first integer is the no of leaf nodes and the second
element in the array is the number of internal nodes
 */
    public static int[] getAllNodes(TreeNode root) {
        int leavesount = 0;
        if (root == null) {
            return new int[]{0, 0};
        }
        if (root.left == null && root.right == null) {
            return new int[]{1, 0};
        }
        int[] leftCount = getAllNodes(root.left);
        int[] rightCount = getAllNodes(root.right);

        int leftLeavesCount = leftCount[0];
        int leftInternalNodes = leftCount[1];

        int rightLeavesCount = rightCount[0];
        int rightInternalNodes = rightCount[1];

        int internalNodes = 0; //We have to count the internal nodes only once.
        if (root.left != null || root.right != null) {
            internalNodes = rightInternalNodes + leftInternalNodes + 1;
        }

        return new int[]{leftLeavesCount + rightLeavesCount, internalNodes};

    }


    public static void main(String args[]) {
        /*Let us create following BST
            50
          /   \
        30      70
        / \   / \
       20 40 60 80 */

        TreeNode root1 = PopulateTree.populateBST();
        int[] counts = getAllNodes(root1);
        System.out.println("leaves : " + counts[0] + " internal Nodes: " + counts[1]);

    }
}


