package trees.lecture12;

import trees.lecture8.PopulateTree;
import trees.lecture8.TreeNode;

import java.util.List;
import java.util.Vector;
/*
Write a method that will return all paths at each level in a tree such that they are always "downwards" and can
begin at any node but, can end at any node.( -1, 4) is a valid path but (4, -1, 5) is not a valid path
The class AllPAthsProblems returns this value instead of just pronting it
 */
public class PrintAllPaths {
    static void printVector(List<Integer> v, int i) {
        for (int j = i; j < v.size(); j++)
            System.out.print(v.get(j) + " ");
        System.out.println();
    }

    static List<Integer> path = new Vector<Integer>();

    // This function prints all paths that have sum k
    static void printAllPathUtil(TreeNode root) {
        // empty node
        if (root == null)
            return;

        // add current node to the path
        path.add(root.val);

        // check if there's any k sum path
        // in the left sub-tree.
        printAllPathUtil(root.left);

        // check if there's any k sum path
        // in the right sub-tree.
        printAllPathUtil(root.right);

        // check if there's any k sum path that
        // terminates at this node
        // Traverse the entire path as
        // there can be negative elements too
        // int f = 0;
        for (int j = path.size() - 1; j >= 0; j--) {
            //  f = f + path.get(j);

            // If path sum is k, print the path
            //if (f == k)
            printVector(path, j);
        }

        // Remove the current element from the path
        path.remove(path.size() - 1);
    }

    // A wrapper over printKPathUtil()
    static void printAllPath(TreeNode root) {
        path = new Vector<Integer>();
        printAllPathUtil(root);
    }

    // Driver code
    public static void main(String args[]) {
        TreeNode root = PopulateTree.populateTreeForKPathSum();
        printAllPath(root);
    }

}
