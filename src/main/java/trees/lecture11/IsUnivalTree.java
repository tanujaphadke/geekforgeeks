package trees.lecture11;

import trees.lecture8.PopulateTree;
import trees.lecture8.TreeNode;

class UnivalT {
    Integer val;
    boolean isUnival;

    public UnivalT(Integer val, boolean isUnival) {
        this.val = val;
        this.isUnival = isUnival;
    }
}

class UnivalCount {
    Integer val;
    Integer count;

    public UnivalCount(Integer val, Integer count) {
        this.val = val;
        this.count = count;
    }
}

public class IsUnivalTree {

    public static boolean isUnival(TreeNode root) {
        UnivalT ut = traverseForUnival(root);
        return ut.isUnival;
    }

    public static int getUnivalCount(TreeNode root) {
        UnivalCount ut = traverseForUnivalCount(root);
        return ut.count;
    }

    /**
     * Returns a class that will contain true or false depending on if it is a unival tree or not .
     *
     * @param root
     * @return 5

    Input: root of below tree
                                    5
                                  / \
                                 1   5
                                / \   \
                              5   5   5
    Output: 4

     */
    public static UnivalCount traverseForUnivalCount(TreeNode root) {
        if (root == null) {
            return new UnivalCount(null, 0);
        }
        if (root.left == null && root.right == null) {
            return new UnivalCount(root.val, 1);
        }
        UnivalCount leftUnival = traverseForUnivalCount(root.left);

        UnivalCount rightUnival = traverseForUnivalCount(root.right);

        //if both Left and right subtrees are unival then return true
        if ((leftUnival.val == null && rightUnival.val == root.val) ||
                (rightUnival.val == null && leftUnival.val == root.val) ||
                (leftUnival.val == root.val && rightUnival.val == root.val)) {
            return new UnivalCount(root.val, leftUnival.count + rightUnival.count + 1);
        } else {
            return new UnivalCount(root.val, leftUnival.count + rightUnival.count);
        }

    }

    public static void main(String args[]) {
        TreeNode root = PopulateTree.populateTreeForUnivalTree();
        boolean ut = isUnival(root);

        System.out.println("GIven Tree is Unival : " + ut);
        int count = getUnivalCount(root);
        System.out.println("Count of Unival trees  : " + count);

    }


    public static UnivalT traverseForUnival(TreeNode root) {
        if (root == null) {
            return new UnivalT(null, true);
        }
        UnivalT leftUnival = traverseForUnival(root.left);
        UnivalT rightUnival = traverseForUnival(root.right);
        //if both Left and right subtrees are unival then return true
        if (leftUnival.isUnival && rightUnival.isUnival) {
            if (leftUnival.val != null) {
                if (!(root.val == leftUnival.val)) {
                    return new UnivalT(null, false);
                }
            }
            if (rightUnival.val != null) {
                if (!(root.val == rightUnival.val)) {
                    return new UnivalT(null, false);
                }
            }
            return new UnivalT(root.val, true);
        } else {
            return new UnivalT(null, false);
        }
    }
}
