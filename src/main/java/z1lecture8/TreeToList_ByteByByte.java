package z1lecture8;
//Convert a Binary Tree to a Circular Doubly Link List

//http://www.geeksforgeeks.org/convert-a-binary-tree-to-a-circular-doubly-link-list/
//https://www.youtube.com/watch?v=Dte6EF1nHNo

 class TLNode {
    int value;
    
    public TLNode (int val){
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
//  	   12	15
//  	   /\    /
//  	  25 30  36
public class TreeToList_ByteByByte {
	
	public static void main (String args [] ){
		//New nodes for inorder treewalk 
		TLNode root  = new TLNode(10);
		TLNode n1  = new TLNode(12);
		TLNode n2  = new TLNode(15);
		TLNode n3  = new TLNode(25);
		TLNode n4  = new TLNode(30);
		TLNode n5  = new TLNode(36);
		//This is not a BST just a Binary tree
		root.left= n1; root.right= n2; n1.left=n3;n1.right=n4; n2.left=n5;
		
		treeToList(root);
	}
	private static TLNode concatenate(TLNode a, TLNode b) {
	    if (a == null) return b;
	    if (b == null) return a;

	    TLNode aEnd = a.left;
	    TLNode bEnd = b.left;

	    a.left = bEnd;
	    bEnd.right = a;
	    aEnd.right = b;
	    b.left = aEnd;
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
	
	
}
