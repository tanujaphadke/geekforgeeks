package sjsu.week5;

import java.util.Objects;

class TreeNode {
    public int val;
    public TreeNode left;
    public TreeNode right;

    @Override
    public String toString() {
        return val + " ";
    }

    public TreeNode(int x) {
        val = x;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TreeNode)) return false;
        TreeNode treeNode = (TreeNode) o;
        return val == treeNode.val && left.equals(treeNode.left) && right.equals(treeNode.right) ;
    }

    @Override
    public int hashCode() {
        return Objects.hash(val, left, right);
    }
}


public class BST {

/*                     15
                    /     \
                 6          18
                / \         / \
              3     7      17  20
           /    \     \
          2      4     13
                      /
                     9
*/
    public static TreeNode populateTree(){
        TreeNode two = new TreeNode(2);
        TreeNode four = new TreeNode(4);
        TreeNode three = new TreeNode(3); three.left = two; three.right = four;
        TreeNode nine = new TreeNode(9);
        TreeNode thirteen = new TreeNode(13); thirteen.left = nine;
        TreeNode seven = new TreeNode(7);   seven.right = thirteen;
        TreeNode six = new TreeNode(6); six.left = three; six.right = seven;
        TreeNode seventeen = new TreeNode(17);
        TreeNode twenty = new TreeNode(20);
        TreeNode eighteen = new TreeNode(18); eighteen.left = seventeen; eighteen.right = twenty;
        TreeNode fifteen = new TreeNode(15);fifteen.left= six; fifteen.right = eighteen;
        return fifteen;
    }
    public static void main ( String args[]) {
        //populate the BST
        TreeNode root = populateTree();
        TreeNode found13 = bstSearch(root, 13);
        TreeNode found20 = bstSearch(root, 20);
        TreeNode found99 = bstSearch(root, 99);
        TreeNode foundNil = bstSearch(root, null);

        System.out.println(found13);
        System.out.println(found20);
        System.out.println(found99);
    }

    //TODO: Write a method to search for a node in a tree rooted at TreeNode root.
    public  static TreeNode bstSearch(TreeNode root, Integer key){


        //TODO: Change the return statement to return the found node or null otherwise
        return null;
    }


}
