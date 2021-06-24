package trees.lecture9;

import trees.lecture8.PopulateTree;
import trees.lecture8.TreeNode;

public class TreeExamples {

    public static void main(String args[]) {

        TreeExamples treeExamples = new TreeExamples();
        TreeNode treeWith3Node = PopulateTree.get3NodeTree();
        TreeNode root2 = PopulateTree.populateTree();

        System.out.println("Height of tree is : " + treeExamples.findHeight(treeWith3Node));
        System.out.println("Height of tree is : " + treeExamples.findHeightOrig(root2));
        System.out.println("size of the tree is: " + treeExamples.sizeOfTree(root2));
        System.out.println("Max of the tree is: " + treeExamples.findMaxOfTree(root2).val);
        System.out.println("\nIs the given tree BST : " + treeExamples.isBST(root2));
        root2 = PopulateTree.populateTreeForIsHeightBalanced();
        System.out.println("\n Is Height Balanced \n" + treeExamples.isHeightbalanced(root2));
        System.out.println("\n Is Height Balanced using Boolean \n" + treeExamples.isHeightbalancedUsingBoolean(root2));

        System.out.println(
                "\nsum of all nodes is: \n" + treeExamples.sumOfAllNodes(root2));

        TreeNode nodeToInsert = new TreeNode(23);
        root2 = PopulateTree.populateBST();
        TreeNode ans = treeExamples.insertInBinarySearchTree(root2, nodeToInsert);

    }

    /**
     * https://www.techiedelight.com/check-given-binary-tree-is-height-balanced-not/
     * Given a sorted array write an algo to find if trees are balances. Balanced if
     * height of two subtrees never differ by more than one Cracking the coding
     * interview Chapter4 ques 4.4
     */
    public boolean isHeightbalanced(TreeNode node) {
        int heightdiff = findHeightDiff(node);
        if (heightdiff == Integer.MAX_VALUE)
            return false;
        return true;
    }

    /**
     * THis method uses Difference in height of trees to find out if the tree is  balanced
     *
     * @param node
     * @return
     */

    public int findHeightDiff(TreeNode node) {
        if (node == null) {
            return -1;
        }
        int lhDiff = findHeightDiff(node.left);
        if (lhDiff < Integer.MAX_VALUE) {
            lhDiff = lhDiff + 1;
        } else { ///return early
            return Integer.MAX_VALUE;
        }
        int rhDiff = findHeightDiff(node.right);
        if (rhDiff < Integer.MAX_VALUE) {
            rhDiff = rhDiff + 1;
        } else {
            return Integer.MAX_VALUE;
        }
        int diff = Math.abs(lhDiff - rhDiff);
        if (diff > 1) {
            System.out.println("Returning " + Integer.MAX_VALUE + " from " + node.val);
            return Integer.MAX_VALUE;
        }
        return diff;

    }

    class HeightBalanced {
        int hdiff;
        boolean isBalanced;

        public HeightBalanced(int hdiff, boolean isBalanced) {
            this.hdiff = hdiff;
            this.isBalanced = isBalanced;
        }
    }

    /**
     * Using Boolean flag
     *
     * @param node
     * @return
     */
    public HeightBalanced findHeightDiffUsingBoolean(TreeNode node, HeightBalanced heightBalanced) {
        if (node == null) {
            return new HeightBalanced(-1, true);
        }
        if (!heightBalanced.isBalanced) return heightBalanced;

        HeightBalanced lheightBalanced = findHeightDiffUsingBoolean(node.left, heightBalanced);
        if (!lheightBalanced.isBalanced) return lheightBalanced;

        lheightBalanced.hdiff = lheightBalanced.hdiff + 1;

        HeightBalanced rheightBalanced = findHeightDiffUsingBoolean(node.right, heightBalanced);
        if (!rheightBalanced.isBalanced) return rheightBalanced;

        rheightBalanced.hdiff = rheightBalanced.hdiff + 1;
        int diff = Math.abs(lheightBalanced.hdiff - rheightBalanced.hdiff);

        if (diff > 1) {
            return new HeightBalanced(diff, false);
        }
        return new HeightBalanced(diff, true);

    }

    public boolean isHeightbalancedUsingBoolean(TreeNode node) {
        HeightBalanced hb = findHeightDiffUsingBoolean(node, new HeightBalanced(0, true));
        if (!hb.isBalanced)
            return false;
        return true;
    }

    @Deprecated
    public boolean isBalanced(TreeNode node) {
        if (node == null)
            return true;

        int lheight = findHeightOrig(node.left);
        int rheight = findHeightOrig(node.right);
        if (lheight - rheight <= 0)
            return true;
        else
            return false;
    }

    /**
     * https://www.youtube.com/watch?v=9ejFkjEgK3k
     *
     * @param root
     * @return
     */
    public int findHeight(TreeNode root) {
        if (root == null) return -1;
        int lheight = findHeight(root.left);
        int rheight = findHeight(root.right);
        int height = Math.max(lheight, rheight) + 1;
        return height;
    }

    public int findHeightOrig(TreeNode node) {
        if (node == null) {
            return -1;
        }
        int lh = findHeightOrig(node.left) + 1;
        int rh = findHeightOrig(node.right) + 1;
        return Math.max(lh, rh);
    }


    /**
     * Starttime 1:25 Size of a tree is the number of elements present in the tree.
     * Size of the below tree is 5. http://www.geeksforgeeks.org/write-a-c-
     * program-to-calculate-size-of-a-tree/ end time 1:38
     */
    public int sizeOfTree(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int leftCount = sizeOfTree(root.left);
        int rightCount = sizeOfTree(root.right);
        return leftCount + rightCount + 1;

    }

    /**
     * Finds the sum of all the nodes
     *
     * @param node
     * @return
     */
    public int sumOfAllNodes(TreeNode node) {
        if (node == null)
            return 0;
        int leftSum = sumOfAllNodes(node.left);
        int rightSum = sumOfAllNodes(node.right);
        return node.val + leftSum + rightSum;

    }

    /**
     * Starttime 1:55 end time 11:59 The max will be the right most of the right
     * tree https://ide.geeksforgeeks.org/aSdcEKGjXG
     *
     * @param root
     * @return
     */
    public TreeNode findMaxOfBinarySearchTree(TreeNode root) {
        if (root == null)
            return null;
        if (root.right == null)
            return root;
        return findMaxOfBinarySearchTree(root.right);

    }

    /**
     * This finds max of any given tree
     *
     * @param root
     * @return
     */
    public TreeNode findMaxOfTree(TreeNode root) {
        if (root == null)
            return null;
        TreeNode leftMax = findMaxOfTree(root.left);
        TreeNode rightMax = findMaxOfTree(root.right);
        TreeNode max = null;
        if (leftMax != null && rightMax == null) {
            max = leftMax;
        } else if (leftMax == null && rightMax != null) {
            max = rightMax;
        } else if (leftMax == null && rightMax == null) {
            max = null;
        } else if (leftMax.val >= rightMax.val) {
            max = leftMax;
        } else {
            max = rightMax;
        }
        if (max == null)
            return root;
        else if (max.val >= root.val) {
            return max;
        }
        return root;

    }

    // This is correct and small and simple implementaion
    public TreeNode findmaxOfATree(TreeNode node) {
        if (node == null) {
            return null;
        }
        TreeNode lmax = findmaxOfATree(node.left);
        TreeNode rmax = findmaxOfATree(node.right);
        // here find max of leftmax right max and root;
        TreeNode max = null;
        if (lmax == null && rmax == null)
            max = node;
        if (lmax == null && rmax != null)
            max = (node.val > rmax.val) ? node : rmax;
        if (rmax == null && lmax != null)
            max = (node.val > lmax.val) ? node : lmax;
        return max;

    }

    //This is second version of finding max of any tree
    public static TreeNode findmaxOfATreeV2(TreeNode root) {
        if (root == null) return null;
        TreeNode maxLeft = findmaxOfATreeV2(root.left);
        TreeNode   maxRight = findmaxOfATreeV2(root.right);
        TreeNode maxNode = null;
        if (maxLeft != null && (maxLeft.val > root.val)) {
            maxNode = maxLeft;
        } else {
            maxNode = root;
        }
        if ((maxRight != null) && maxRight.val > maxNode.val) {
            maxNode = maxRight;
        }
        return maxNode;
    }





    /**
     * Standard Tree procedures root can be a global varaible
     */
    /**
     * Inserts a node into a tree with root Since there is no parent pointer needed
     * except for root ?
     *
     * @param root
     * @param node
     */
    /**
     * Inserts a node into a tree with root Since there is no parent pointer needed
     * except for root ?
     *
     * @param root
     * @param node
     */
    public TreeNode insertInBinarySearchTree(TreeNode root, TreeNode node) {
        if (root == null) {
            root = node;
            return root;
        } else {
            TreeNode ret;
            if (root.val > node.val) {
                ret = insertInBinarySearchTree(root.left, node);
                if (root.left == null)
                    root.left = ret;

            } else {
                ret = insertInBinarySearchTree(root.right, node);
                if (root.right == null) {
                    root.right = ret;
                }
            }
            return root;
        }

    }


    /**
     * returns true if the tree is BST
     *
     * @param root
     * @return
     */
    public boolean isBST(TreeNode root) {
        if (root == null)
            return true;
        boolean isLeftBST = isBST(root.left);
        boolean isrightBST = isBST(root.right);
        boolean isCurrentBST = true;
        if (root.left != null) {
            isCurrentBST = root.val >= root.left.val;
        }
        if (root.right != null) {
            isCurrentBST = isCurrentBST && root.val <= root.right.val;
        }
        return isLeftBST & isrightBST && isCurrentBST;

    }

}
