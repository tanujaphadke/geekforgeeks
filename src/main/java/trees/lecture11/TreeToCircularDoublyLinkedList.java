package trees.lecture11;
//Convert a Binary Tree to a Circular Doubly Link List

//http://www.geeksforgeeks.org/convert-a-binary-tree-to-a-circular-doubly-link-list/
// https://www.byte-by-byte.com/treetolist/

import trees.lecture8.TreeNode;

class TLNode {
    int value;

    public TLNode(int val) {
        this.value = val;
    }

    @Override
    public String toString() {
        return "TLNode [value=" + value + ", left=" + left + ", right=" + right
                + "]";
    }

    TLNode left;
    TLNode right;
}

// 			 10
// 			/	\
//  	   12	 15
//  	   /\    /
//  	  25 30  36
public class TreeToCircularDoublyLinkedList {

    public static void main(String args[]) {
        //New nodes for inorder treewalk
        TreeNode root = new TreeNode(10);
        TreeNode n1 = new TreeNode(12);
        TreeNode n2 = new TreeNode(15);
        TreeNode n3 = new TreeNode(25);
        TreeNode n4 = new TreeNode(30);
        TreeNode n5 = new TreeNode(36);
        //This is not a BST just a Binary tree
        root.left = n1;
        root.right = n2;
        n1.left = n3;
        n1.right = n4;
        n2.left = n5;

        TreeNode tn = treeToList(root);

    }

    private static TLNode concatenate(TLNode a, TLNode b) {
        if (a == null) return b;
        if (b == null) return a;

//        TLNode aEnd = a.left;
//        TLNode bEnd = b.left;

        a.left = b.left;
        b.left.right = a;
        a.left.right = b;//aEnd
        b.left = a.left;
        return a;
    }

    public static TLNode treeToList(TLNode rootNode) {
        if (rootNode == null) return rootNode;
        TLNode leftList = treeToList(rootNode.left);
        TLNode rightList = treeToList(rootNode.right);
        rootNode.left = rootNode;
        rootNode.right = rootNode;

        rootNode = concatenate(leftList, rootNode);
        rootNode = concatenate(rootNode, rightList);
        return rootNode;
    }

    public static TreeNode treeToList(TreeNode rootTreeNode) {
        // For the root node this is not to the left or right of the parent so both values will be false;
        TreeNode tn = treeToListHelper(rootTreeNode, false, false);
        TreeNode temp = tn;
        while (temp != null) {
            System.out.print(temp.val + "  ");
            temp = temp.right;

        }

        //Traverse till the right end of the returned linkedList to find the tail and in the end create circular list
        while (temp != null) {
            System.out.print(temp.val + "  ");
            temp = temp.right;

        }
        return tn;
    }

    /*
    This method iterates the list 2 times so the time complexity is O(2n) or O(n)
     */
    public static TreeNode treeToListHelper(TreeNode root, Boolean isRootLeftOfParent, Boolean isRootRightOfParent) {
        if (root == null) return null;
        if (root.left == null && root.right == null) return root;

        TreeNode left = (root.left != null) ? treeToListHelper(root.left, true, false) : null;

        if (left != null) {
            left.right = root; //Left's next points to root
            root.left = left; //root's previous should point to the left child
            //Where is the left of left pointing to ?
        }

        TreeNode right = (root.right != null) ? treeToListHelper(root.right, false, true) : null;

        if (right != null) {
            root.right = right; //Roots next should point to the right child.
            right.left = root;  //Rights previous should point to root;
            // where is the right of right pointing to ?
        }

        //return the right most child because now when the root of the caller is visited it should start
        //pointing its previous pointer to the right most child
        if (isRootLeftOfParent) {
            if (right != null) {
                return right; //Because the rightmost element will be the last element in inorder.
                // And in the returned stack the caller/ root has to be merged with this last element
            } else return root;
        } else if (isRootRightOfParent) {

            if (left != null) {
                return left;//Because the leftmost element will be the first element in inorder.
                // And in the returned stack the caller/ root has to be merged with this first element of the right subtree
            } else {
                return root;
            }
        } else {
            // Meaning left and right both are not true
            //Now your have reached the root of the given input tree
            //In this case use previous pointer or left pointer and reach the head of the list.

            TreeNode returnThis = root; //Traverse backward to return the head
            while (returnThis.left != null) {
                returnThis = returnThis.left;
            }

            TreeNode tail = root; //Traverse the remaining half to reach the tail

            while (tail.right != null) {
                tail = tail.right;
            }
            //Create a circulat DBL List
            tail.right = returnThis;
            returnThis.left = right;
            return returnThis;//Thsi is the head .
        }
    }

    /*
    This version is same as above treeToListHelper but, at each step it creates a circular list.
    This iterates O(n) times instead of O(2n)
     */
    public static TreeNode treeToListHelper2(TreeNode root, Boolean isRootLeftOfParent, Boolean isRootRightOfParent) {
        if (root == null) return null;
        if (root.left == null && root.right == null) return root;

        TreeNode left = (root.left != null) ? treeToListHelper2(root.left, true, false) : null;

        if (left != null) {
            left.right = root; //Left's next points to root
            root.left = left; //root's previous should point to the left child
            //Where is the left of left pointing to ?
        }

        TreeNode right = (root.right != null) ? treeToListHelper2(root.right, false, true) : null;

        if (right != null) {
            root.right = right; //Roots next should point to the right child.
            right.left = root;  //Rights previous should point to root;
            // where is the right of right pointing to ?
        }

        //Here make it circular. See above comment  for left of left and right of right. POint them now
        left.left = left != null ? right : null ;
        right.right = right != null ? left : null ;

        //return the right most child because now when the root of the caller is visited it should start
        //pointing its previous pointer to the right most child
        if (isRootLeftOfParent) {
            if (right != null) {
                return right; //Because the rightmost element will be the last element in inorder.
                // And in the returned stack the caller/ root has to be merged with this last element
            } else return root;
        } else if (isRootRightOfParent) {

            if (left != null) {
                return left;//Because the leftmost element will be the first element in inorder.
                // And in the returned stack the caller/ root has to be merged with this first element of the right subtree
            } else {
                return root;
            }
        } else {
            // Meaning left and right both are not true
            //In this case use previous pointer or left pointer and reach the head of the list.

            TreeNode head1 = left != null ? left.right: root; //Since the last node will now point to head in curcular manner
            TreeNode  tail1 = right !=null ? right.left : root; //The begining of the right list points to the tail in circular manner
            //The pointers for root are already manipulated above
            head1.left = tail1 ;
            tail1.right = head1; // Make the list circular
            return head1;

        }
    }

}
