package trees.lecture12;


import trees.lecture8.PopulateTree;
import trees.lecture8.TreeNode;

import java.util.ArrayList;
import java.util.List;

//http://www.geeksforgeeks.org/maximum-element-two-nodes-bst/
//Input : arr[] = { 18, 36, 9, 6, 12, 10, 1, 8 },
//a = 1,
//b = 10.
//Output : 12

public class PathBetweenTwoNodes {
    private static TreeNode root;

    //Finds path between a and b in any tree that has distinct nodes.This tree need not be a Binary Search Tree
    public static List<TreeNode> getPathAnyTree(TreeNode root, TreeNode someNode) {
        if (root == null) return new ArrayList<>();
        if (someNode == null) return new ArrayList<>();
        if (root.val == someNode.val) {
            List p = new ArrayList();
            p.add(root);
            return p;
        }
        List p = getPathAnyTree(root.left, someNode);
        if (p.isEmpty()) {
            p = getPathAnyTree(root.right, someNode);
            if (p.isEmpty()) {
                return new ArrayList<>();
            }
        }
        p.add(root);
        return p;
    }

    /**
     * The helper class will be exactly same.
     * @param root
     * @param a
     * @param b
     * @return
     */
    public static List<TreeNode> getPathHelperBST(TreeNode root, TreeNode a, TreeNode b) {
        return getPathHelper(root, a, b);
    }


    public static List<TreeNode> getPathHelper(TreeNode root, TreeNode a, TreeNode b) {
        List<TreeNode> pathA = getPathAnyTree(root, a);
        List<TreeNode> pathB = getPathAnyTree(root, b);
        TreeNode previousSameNodeA = null, previousSameNodeB = null;
        int i = -1;
        int j = -1;
        int lastCommonIndexA = -1;
        for (i = pathA.size() - 1, j = pathB.size() - 1; i >= 0; i--, j--) {
            TreeNode nodeA = pathA.get(i);
            TreeNode nodeB = pathB.get(j);
            if (nodeA.val == nodeB.val) {
                //Store the common TreeNode in one pf the paths
                //Since this is common we need not store it twice.
                lastCommonIndexA = i;
                continue;
            } else {
                //Now we have found the first non common node; and we have the index
                break;
            }
        }
        List<TreeNode> revPathB = new ArrayList<>();
        List<TreeNode> path = new ArrayList<>();

        for (int k = j; k >= 0; k--) {
            revPathB.add(pathB.get(k));
        }
        //Merge the revPathB with pathA.
        for (int k = 0; k <= lastCommonIndexA; k++) {
            path.add(pathA.get(k));
        }
        path.addAll(revPathB);
        return path;

    }

    /*
    Gets Path from Root to some node in a BST
     */
    public static List<TreeNode> getPathBST(TreeNode root, TreeNode someNode) {
        if (root == null) return new ArrayList<>();
        if (someNode == null) return new ArrayList<>();
        if (root.val == someNode.val) {
            List p = new ArrayList();
            p.add(root);
            return p;
        }
        List p = new ArrayList();
        if (someNode.val < root.val) {
            p = getPathBST(root.left, someNode);
        } else {//means it is greater
            p = getPathBST(root.right, someNode);

        }
        if (!p.isEmpty()) {
            p.add(root);
        }
        return p;
    }

    public static void main(String args[]) {
        //Populate some tree here
        //Here populate a BST
        TreeNode root = PopulateTree.populateBST();
        //What will happen if the tree has common nodes?
        //This code will work for any tree that has distinct nodes.
        //Find path between 30 and 80
        /* Let us create following BST
            50
          /   \
        30      70
        / \   / \
       20 40 60 80 */

        TreeNode a = new TreeNode(30);
        TreeNode b = new TreeNode(60);

        List<TreeNode> path = getPathHelper(root, a, b);

        print(path);

        //Call for BST
        path = getPathHelper(root, a, b);
        System.out.println("For BST : ");
        print( path);
    }


    public static void print(List<TreeNode> p2nodes) {
        System.out.println();
        for (TreeNode node : p2nodes) {
            System.out.print(node.val + " : ");
        }
    }

    private static void addToPathRev(List<TreeNode> path, TreeNode parent,
                                     List<TreeNode> p) {
        path.add(parent);
        path.addAll(p);
    }

}
