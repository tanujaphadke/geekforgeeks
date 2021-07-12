package trees.lecture12;

import trees.lecture8.TreeNode;

public class MaxPathSum {
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
		MaxPathSum bt = new MaxPathSum();
		System.out.println(bt.findMaxSum(root));
	}

}

// 50
// / \
// 40 60
// / \ / \
// 30 45 55 70
//

