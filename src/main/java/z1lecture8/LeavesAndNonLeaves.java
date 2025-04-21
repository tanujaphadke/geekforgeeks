package z1lecture8;


/*
Given the root to a binary tree root, return a list of two numbers where the first number is the
number of leaves in the tree and the second number is the number of internal (non-leaf) nodes.


 */
public class LeavesAndNonLeaves {
//                       3
//    	     5		                2
//        0		1

    public int [] printTreeNodesA ( TreeNode2 node){
        if (node == null ) return new int[]{0, 0};
        if ( node.left == null & node.right == null ) return new int[]{1, 0};
        int [] count =  new int[]{0, 1} ; //visit this node
        int [] leavesAndInternall = new int[2];         int [] leavesAndInternalR = new int[2];

        if ( node.left != null ) {
            leavesAndInternall = printTreeNodesA(node.left);
            count[0] = count[0] + leavesAndInternall[0];
        }
        if ( node.right != null ) {
            leavesAndInternalR  = printTreeNodesA(node.right);
            count[0] = count[0] + leavesAndInternalR[0];
        }
        count[1 ]= count[1] + leavesAndInternall[1] + leavesAndInternalR[1];
        return count;
    }

    public int[] solve(TreeNode2 root) {
        int leavesAndInternal[] = printTreeNodesA(root);
        leavesAndInternal[1] = leavesAndInternal[1] + 1;
        return leavesAndInternal;
    }


        //Count of all nodes
    public int printTreeNodes ( TreeNode2 node){
        if (node == null ) return 0;
        if ( node.left == null & node.right == null ) return 1;
        int count =  1 ; //visit this node
        if ( node.left != null ) {
            count = count + printTreeNodes(node.left);
        }
        if ( node.right != null ) {
            count = count + printTreeNodes(node.right);
        }
        return count;
    }


    public static void main ( String args[]){
        TreeNode2 root = new TreeNode2(3);
        TreeNode2 rootLeft = new TreeNode2(5);
        TreeNode2 rootRight = new TreeNode2(2);
        TreeNode2 left5 = new TreeNode2(0);
        TreeNode2 right5 = new TreeNode2(1);
        root.left = rootLeft;
        root.right = rootRight;
        rootLeft.left = left5;
        rootLeft.right = right5;

        LeavesAndNonLeaves l = new LeavesAndNonLeaves();
        int[] ans = l.solve(root);
        System.out.println(ans[0] + " " + ans[1]);

    }
}


class TreeNode2 {
    int val;
    TreeNode2 left;
    TreeNode2 right;

    int color; //0 = black
    TreeNode2 parent; //We do not need this
    int d;
    @Override
    public String toString() {
        return "{\"val\":" + val + ", \"left\":" + left + ", \"right\":" + right
                + "}";
    }

    TreeNode2(int x) {
        val = x;
        color = 1;
    }
}
