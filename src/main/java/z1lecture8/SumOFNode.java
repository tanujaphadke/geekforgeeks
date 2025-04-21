package z1lecture8;

public class SumOFNode {
	
	public void  postOrder(TreeNode node){
		
		if (node == null ) return ;
		postOrder(node.left);
		postOrder(node.right);
		System.out.println(visit(node));
		
	}

	public static void main(String args[]) {
		TreeNode root = new TreeNode(11);
		TreeNode node1 = new TreeNode(5);
		TreeNode node2 = new TreeNode(14);
		TreeNode node3 = new TreeNode(3);
		TreeNode node4 = new TreeNode(12);
		TreeNode node5 = new TreeNode(17);
		TreeNode node6 = new TreeNode(1);
		TreeNode node7 = new TreeNode(2);
		TreeNode node8 = new TreeNode(0);

		root.left=node1;
		root.right = node2;
		node1.left=node3;
		node2.left=node4;
		node2.right= node5;
		node3.left= node6;
		node3.right = node7;
		node6.left = node8;
		
		SumOFNode s = new SumOFNode();
		
		s.postOrder(root);
		System.out.println(s.add(4, 5));

	}
	
	int add(int x, int y){
	    while(y!=0){
	        int carry = (x&y)<< 1;
	        x=x^y;
	        y=carry;
	    }
	    return x;
	}
	
	
	int visit (TreeNode node){
		return node.val +( ( node.right != null ) ? node.right.val : 0 );
	}

}
