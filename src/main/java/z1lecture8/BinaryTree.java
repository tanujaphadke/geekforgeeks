package z1lecture8;

public class BinaryTree {
	// Given a binary tree, find the sum of the maximum path sum.

	public int findMaxSum(TreeNode node) {
		if (node == null)
			return 0; // Or throw Error not a valid tree
		int sumL = 0;
		int sumR = 0;
		if (node.left == null & node.right == null)
			return node.val;
		TreeNode nodeL = node.left;
		TreeNode nodeR = node.right;
		sumL = node.val + findMaxSum(nodeL);
		sumR = node.val + findMaxSum(nodeR);
		return Math.max(sumL, sumR);
	}

	/* Question: Find if tree is balanced tree.

	 * This is helper method
	 */
	public boolean isTreeBalanced(TreeNode node){
		int height = heightModified(node);
		return height == Integer.MIN_VALUE ? false : true;

	}



	 /*
	 * The problem is that here we have to return height we cannot return true false,
	 * Because suppose we found that difference between left subtree height and right
	 * subtree heignt were > 1 in the first try itself then we return false.
	 * But if it did not satisfy this conidtion in that case we cannot return true
	 * becasue it is possible that further way up the tree gets disbalanced.
	 * So we must return height of the tree for calculation of future heights in the reurssion
	 * We cannot return two values from reursion . So if the subtree is not balanced then we will return
	 * a negative infinity value and keep bubbling it up.
	 */
	public int heightModified (TreeNode node){
		if (node == null ) return -1;
		int lHeight = heightModified(node.left) ;
		int rHeight = heightModified(node.right) ;
		if (lHeight == Integer.MIN_VALUE || rHeight == Integer.MIN_VALUE){
			return Integer.MIN_VALUE;
		}
		if (Math.abs(rHeight-lHeight) >=1){
			return rHeight > lHeight ? rHeight+1 : lHeight+1;
		}else {
			return Integer.MIN_VALUE; //-infinity
		}
	}







	public static void main(String args[]) {
		TreeNode root = new TreeNode(50);
		TreeNode node1 = new TreeNode(40);
		TreeNode node2 = new TreeNode(60);
		TreeNode node3 = new TreeNode(30);
		TreeNode node4 = new TreeNode(45);
		TreeNode node5 = new TreeNode(55);
		TreeNode node6 = new TreeNode(70);
		root.left = node1;
		root.right = node2;
		node1.left = node3;
		node1.right = node4;
		node2.left = node5;
		node2.right = node6;
		BinaryTree bt = new BinaryTree();
		System.out.println(bt.findMaxSum(root));
	}

}

// 50
// / \
// 40 60
// / \ / \
// 30 45 55 70
//

