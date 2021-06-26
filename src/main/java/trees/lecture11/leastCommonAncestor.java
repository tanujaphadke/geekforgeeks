package trees.lecture12;


import trees.lecture8.PopulateTree;
import trees.lecture8.TreeNode;

//http://www.geeksforgeeks.org/maximum-element-two-nodes-bst/
//Input : arr[] = { 18, 36, 9, 6, 12, 10, 1, 8 },
//a = 1,
//b = 10.
//Output : 12

public class leastCommonAncestor {
    private static TreeNode root;

    /**
     *Given values of two values n1 and n2 in a Binary Search Tree,
     * find the Lowest Common Ancestor (LCA). You may assume that both the values exist in the tree.
     * Let T be a rooted tree. The lowest common ancestor between two nodes n1 and n2 is defined as
     * the lowest node in T that has both n1 and n2 as descendants
     * (where we allow a node to be a descendant of itself).
     * @param root
     * @param a  a node in the tree
     * @param b  another node on the tree
     * @return
     */
    public static TreeNode lca( TreeNode root,TreeNode a, TreeNode b ){
        if ( root == null) return null;
        if ( root.val > a.val && root.val >b.val  ){
            return lca(a.left,a,b);
        } else if ( root.val < a.val && root.val<b.val ){
            return lca(root.right, a, b);
        }else {
            return  root ; //as this is the lca
        }

    }

    public static void main(String args[]) {
        //just get some BST
        TreeNode root = PopulateTree.populateBST();
        TreeNode a = new TreeNode(20);
        TreeNode b = new TreeNode(40);
        System.out.println(lca(root,a,b));


    }
}
