package trees.lecture8;

public class IsGivenTreeBST {

	// Findout if a given tree is a BST{}
	public boolean isBST(TreeNode root) {
		if (root == null || (root.left == null && root.right == null))
			return true;
		if ((root.left == null || root.left.val <= root.val) && (root.right == null || root.val <= root.right.val)){
			return isBST( root.left) && isBST( root.right) ;
		}else{
			return false;
		}
		 
	}



}
