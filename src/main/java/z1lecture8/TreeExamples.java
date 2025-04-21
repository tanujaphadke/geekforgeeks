package z1lecture8;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TreeExamples {

	public static void main(String args[]) {
		TreeExamples treeExamples = new TreeExamples();
		TreeNode1 root;
		root = new TreeNode1(1);
		root.left = new TreeNode1(2);
		root.right = new TreeNode1(3);

		root.left.left = new TreeNode1(4);
		root.left.right = new TreeNode1(5);
		// root.right.left = new TreeNode(6);
		// root.right.right = new TreeNode(7);
		//
		// root.left.left.left = new TreeNode(8);
		// root.left.left.right = new TreeNode(9);
		root.left.right.left = new TreeNode1(10);
		// root.left.right.right = new TreeNode(11);
		//
		// root.right.left.left = new TreeNode(12);
		// root.right.left.right = new TreeNode(13);
		// root.right.right.left = new TreeNode(14);
		// root.right.right.right = new TreeNode(15);
		root.left.right.left.left = new TreeNode1(40);

		System.out.println("Height of tree is : " + treeExamples.findHeight(root));
		System.out.println("size of the tree is: " + treeExamples.sizeOfTree(root));
		System.out.println("Max of the tree is: " + treeExamples.findMaxOfTree(root).data);
		System.out.println("Level order traversal  of the tree is: ");
		treeExamples.printLevelOrder(root);
		System.out.println("\nLevel order traversal  of the tree Line by line is: \n");
		treeExamples.printLevelOrderLineByLine(root);

		System.out.println("\nReverse Level order traversal  of the tree: \n");
		treeExamples.printReverseLevelOrder(root);

		System.out
				.println("\nLevel order traversal  of the tree Line by line is: \n" + treeExamples.sumOfAllNodes(root));

		System.out.println("\nLevel order ZIG ZAG traversal  of the tree is: \n");
		treeExamples.printZigZagOrder(root);

		System.out.println("\nIs the given tree BST : " + treeExamples.isBST(root));

		System.out.println(treeExamples.isbalanced(root));

		TreeNode1 nodeToInsert = new TreeNode1(23);
		TreeNode1 ans = treeExamples.insertInTree(root, nodeToInsert);
		System.out.println("Inserted node in tree " + ans);

		printPathHeler();

	}

	public boolean isbalanced(TreeNode1 node) {
		int heightdiff = findHeightDiff(node);
		if (heightdiff == Integer.MAX_VALUE)
			return false;
		return true;
	}

	public int findHeightDiff(TreeNode1 node) {
		if (node == null) {
			return -1;
		}
		int lh = findHeightDiff(node.left) + 1;
		int rh = findHeightDiff(node.right) + 1;
		int diff = Math.abs(lh - rh);
		if (diff > 1) {
			System.out.println("Returning " + Integer.MAX_VALUE + " from " + node.data);
			return Integer.MAX_VALUE;
		}
		return diff;

	}

	/**
	 * Find all paths in a B-tree
	 * http://www.geeksforgeeks.org/print-k-sum-paths-binary-tree/
	 */
	public Map<Integer, List<List<TreeNode1>>> findPaths(TreeNode1 root, int level) {
		Map<Integer, List<List<TreeNode1>>> hashMap = new HashMap<Integer, List<List<TreeNode1>>>();

		if (root == null) {
			List<List<TreeNode1>> list = new ArrayList<List<TreeNode1>>();
			hashMap.put(level, list);
			return hashMap;
		}
		Map<Integer, List<List<TreeNode1>>> lHashMap = findPaths(root.left, level + 1);

		List<List<TreeNode1>> lTempPath = hashMap.get(level + 1);
		List<TreeNode1> l = new ArrayList<TreeNode1>();
		l.add(root);
		lTempPath.add(l);

		List<List<TreeNode1>> rTempPath = hashMap.get(level + 1);
		List<TreeNode1> r = new ArrayList<TreeNode1>();
		r.add(root);
		rTempPath.add(r);

		return null;
	}

	// This is for practice.This may not work
	// https://www.geeksforgeeks.org/given-a-binary-tree-print-all-root-to-leaf-paths/
	public List<List<TreeNode1>> printAllRootToleafPathFromAnyRoot(TreeNode1 node) {
		if (node == null) {
			List<List<TreeNode1>> allpaths = new ArrayList<List<TreeNode1>>();
			return allpaths;
		}
		// We are not back tracking we addd nodes as desired.
		List<List<TreeNode1>> lpaths = printAllRootToleafPathFromAnyRoot(node.left);
		List<List<TreeNode1>> rpaths = printAllRootToleafPathFromAnyRoot(node.right);
		List<TreeNode1> lpath = new ArrayList<>();
		lpath.add(0, node);
		List<TreeNode1> rpath = new ArrayList<>();
		rpath.add(0, node);
		List<List<TreeNode1>> allpath = new ArrayList<>();
		allpath.add(lpath);
		allpath.add(rpath);
		return allpath;

	}

	/*
	 * 10 8 2 3 5 2
	 */
	public static void printPathHeler() {
		TreeNode1 root = new TreeNode1(10);
		root.left = new TreeNode1(8);
		root.right = new TreeNode1(2);
		root.left.left = new TreeNode1(3);
		root.left.right = new TreeNode1(5);
		root.right.left = new TreeNode1(2);
		printAllRootToLeafPaths(root, new ArrayList());
	}

	// https://www.geeksforgeeks.org/given-a-binary-tree-print-all-root-to-leaf-paths/
	public static void printAllRootToLeafPaths(TreeNode1 node, List<TreeNode1> inpath) {
		if (node == null)
			return;
		inpath.add(node);
		printAllRootToLeafPaths(node.left, inpath);
		printAllRootToLeafPaths(node.right, inpath);
		if (node.left == null && node.right == null) {
			// print inpath
			inpath.stream().forEach(n -> System.out.print(n.data));
			System.out.println();
			return;
		}

	}

	/**
	 * Find all K-sum paths in a B-tree
	 * http://www.geeksforgeeks.org/print-k-sum-paths-binary-tree/
	 */

	public void kSumPaths(TreeNode1 root) {

	}

	/**
	 * Given a sorted array write an algo to find if trees are balances. Balanced if
	 * height of two subtrees never differ by more than one Cracking the coding
	 * interview Chapter4 ques 4.4
	 */
	@Deprecated
	public boolean isBalanced(TreeNode1 node) {
		if (node == null)
			return true;

		int lheight = findHeight(node.left);
		int rheight = findHeight(node.right);
		if (lheight - rheight <= 0)
			return true;
		else
			return false;
	}

	public int findHeight(TreeNode1 node) {
		if (node == null) {
			return -1;
		}
		int lh = findHeight(node.left) + 1;
		int rh = findHeight(node.right) + 1;
		return Math.max(lh, rh);
	}

	/**
	 * 15 minutes to compleete
	 * http://www.geeksforgeeks.org/write-c-code-to-determine
	 * -if-two-trees-are-identical/
	 * 
	 * @param root1
	 * @param root2
	 * @return
	 */
	public boolean areIdenticalTrees(TreeNode1 root1, TreeNode1 root2) {
		if (root1 == null && root2 == null) {
			return true;
		}
		if ((root1 == null || root2 == null) || (root1.data != root2.data)) {
			return false;
		}
		boolean leftIdentical = areIdenticalTrees(root1.left, root2.left);
		boolean rightIdentical = areIdenticalTrees(root1.right, root2.right);

		return leftIdentical && rightIdentical;
	}

	/**
	 * Starttime 1:25 Size of a tree is the number of elements present in the tree.
	 * Size of the below tree is 5. http://www.geeksforgeeks.org/write-a-c-
	 * program-to-calculate-size-of-a-tree/ end time 1:38
	 */
	public int sizeOfTree(TreeNode1 root) {
		if (root == null) {
			return 0;
		}
		int leftCount = sizeOfTree(root.left);
		int rightCount = sizeOfTree(root.right);
		return leftCount + rightCount + 1;

	}

	/**
	 * Finds the sum of all the nodes
	 * 
	 * @param node
	 * @return
	 */
	public int sumOfAllNodes(TreeNode1 node) {
		if (node == null)
			return 0;
		int leftSum = sumOfAllNodes(node.left);
		int rightSum = sumOfAllNodes(node.right);
		return node.data + leftSum + rightSum;

	}

	/**
	 * Starttime 1:55 end time 11:59 The max will be the right most of the right
	 * tree https://ide.geeksforgeeks.org/aSdcEKGjXG
	 * 
	 * @param root
	 * @return
	 */
	public TreeNode1 findMaxOfBinaryTree(TreeNode1 root) {
		if (root == null)
			return null;
		if (root.right == null)
			return root;
		return findMaxOfBinaryTree(root.right);

	}

	/**
	 * This finds max of any given tree
	 * 
	 * @param root
	 * @return
	 */
	public TreeNode1 findMaxOfTree(TreeNode1 root) {
		if (root == null)
			return null;
		TreeNode1 leftMax = findMaxOfTree(root.left);
		TreeNode1 rightMax = findMaxOfTree(root.right);
		TreeNode1 max = null;
		if (leftMax != null && rightMax == null) {
			max = leftMax;
		} else if (leftMax == null && rightMax != null) {
			max = rightMax;
		} else if (leftMax == null && rightMax == null) {
			max = null;
		} else if (leftMax.data >= rightMax.data) {
			max = leftMax;
		} else {
			max = rightMax;
		}
		if (max == null)
			return root;
		else if (max.data >= root.data) {
			return max;
		}
		return root;

	}

	// Thsi is correct and small and simple . Use this one
	public TreeNode1 findmaxOfATree(TreeNode1 node) {
		if (node == null) {
			return null;
		}
		TreeNode1 lmax = findmaxOfATree(node.left);
		TreeNode1 rmax = findmaxOfATree(node.right);
		// here find max of leftmax right max and root;
		TreeNode1 max = null;
		if (lmax == null && rmax == null)
			max = node;
		if (lmax == null && rmax != null)
			max = (node.data > rmax.data) ? node : rmax;
		if (rmax == null && lmax != null)
			max = (node.data > lmax.data) ? node : lmax;
		return max;

	}

	public void printZigZagOrder2(TreeNode1 root) {
		List<TreeNode1> ll = new ArrayList<TreeNode1>();
		ll.add(root);
		zigZagOrderHelper(ll, 1);
	}

	public void 		zigZagOrderHelper(List<TreeNode1> list, int level) {
		if ( list == null || list.isEmpty() ) {
			return;
		}
		List<TreeNode1> ll = new ArrayList<>();
		for ( TreeNode1 each : list ) {
			TreeNode1 left =each.left;
			TreeNode1 right = each.right;
			ll.add(left); ll.add(right);
			zigZagOrderHelper(ll, level + 1);
			print(ll,level);
		}
	}

	public void print(List<TreeNode1> list, int level) {
		if (level %2 == 0 ) {
			for ( int i = 0; i < list.size() ; i ++) {
				System.out.print(list.get(i));
			}
		}else {
			for ( int i = list.size(); i >=0 ; i --) {
				System.out.print(list.get(i));
			}
		}
		System.out.println();
	}
	
	/**
	 * Prints level order zig-zag traersal of any tree
	 * http://www.geeksforgeeks.org/zigzag-tree-traversal/
	 * 
	 * @param root
	 */
	public void printZigZagOrder(TreeNode1 root) {
		List<TreeNode1> ll = new ArrayList<TreeNode1>();
		ll.add(root);
		List<TreeNode1> llnodes = zigZagOrder(ll, 1);
		ll.addAll(llnodes);
		for (TreeNode1 each : ll) {

			System.out.print(each.data + " ");
		}

	}

	private List<TreeNode1> zigZagOrder(List<TreeNode1> nodeList, int level) {
		if (nodeList == null || nodeList.isEmpty())
			return new ArrayList<TreeNode1>();
		List<TreeNode1> ll = new ArrayList<TreeNode1>();
		if (level % 2 == 0) {
			// always pop from the back of a list
			for (int i = nodeList.size() - 1; i > -1; i--) {
				TreeNode1 temp = nodeList.get(i);
				if (temp.left != null)
					ll.add(temp.left);
				if (temp.right != null)
					ll.add(temp.right);
			}
		} else {
			for (int i = nodeList.size() - 1; i > -1; i--) {
				TreeNode1 temp = nodeList.get(i);
				if (temp.right != null)
					ll.add(temp.right);
				if (temp.left != null)
					ll.add(temp.left);
			}
		}
		List nodesAtLevel = zigZagOrder(ll, level + 1);
		ll.addAll(nodesAtLevel);
		return ll;

	}

	/**
	 * Prints level order BFS traersal of any tree in reverse order that is the leaf
	 * level is printed first then the levels before that and in the end the root
	 * node is printed. By default add inserts at the end of list Specify index 0 as
	 * the first parameter that way the nodes will always be inserted at the
	 * begining.
	 * 
	 * @param root
	 */
	public void printReverseLevelOrder(TreeNode1 root) {
		List<TreeNode1> ll = new ArrayList<TreeNode1>();
		ll.add(root);
		List<TreeNode1> llnodes = levelOrderReverse(ll);
		ll.addAll(0, llnodes);
		for (TreeNode1 each : ll) {

			System.out.print(each.data + " ");
		}

	}

	private List<TreeNode1> levelOrderReverse(List<TreeNode1> nodeList) {
		if (nodeList == null || nodeList.isEmpty())
			return new ArrayList<TreeNode1>();
		List<TreeNode1> ll = new ArrayList<TreeNode1>();
		for (TreeNode1 node : nodeList) {
			if (node.left != null)
				ll.add(node.left);
			if (node.right != null)
				ll.add(node.right);
		}
		List nodesAtLevel = levelOrderReverse(ll);
		ll.addAll(0, nodesAtLevel);
		return ll;

	}

	/**
	 * Prints level order BFS traersal of any tree
	 * 
	 * @param root
	 */
	public void printLevelOrder(TreeNode1 root) {
		List<TreeNode1> ll = new ArrayList<TreeNode1>();
		ll.add(root);
		List<TreeNode1> llnodes = levelOrder(ll);
		ll.addAll(llnodes);
		for (TreeNode1 each : ll) {

			System.out.print(each.data + " ");
		}

	}

	private List<TreeNode1> levelOrder(List<TreeNode1> nodeList) {
		if (nodeList == null || nodeList.isEmpty())
			return new ArrayList<TreeNode1>();
		List<TreeNode1> ll = new ArrayList<TreeNode1>();
		for (TreeNode1 node : nodeList) {
			if (node.left != null)
				ll.add(node.left);
			if (node.right != null)
				ll.add(node.right);
		}
		List nodesAtLevel = levelOrder(ll);
		ll.addAll(nodesAtLevel);
		return ll;

	}

	/**
	 * Prints level order BFS traversal of any tree each level is printed in a
	 * seperate line
	 * 
	 * @param root
	 */
	public void printLevelOrderLineByLine(TreeNode1 root) {
		List<TreeNode1> ll = new ArrayList<TreeNode1>();
		ll.add(root);
		ll.add(null);
		List<TreeNode1> llnodes = levelOrderLineByLine(ll);
		ll.addAll(llnodes);
		for (TreeNode1 each : ll) {
			if (each == null)
				System.out.println();
			else
				System.out.print(each.data + " ");
		}

	}

	public List<TreeNode1> levelOrderLineByLine(List<TreeNode1> nodeList) {
		if (nodeList == null || nodeList.isEmpty() || nodeList.get(0) == null)
			return new ArrayList<TreeNode1>();
		List<TreeNode1> ll = new ArrayList<TreeNode1>();
		for (TreeNode1 node : nodeList) {
			if (node != null && node.left != null)
				ll.add(node.left);
			if (node != null && node.right != null)
				ll.add(node.right);
		}
		ll.add(null);// Add a dummy null for printing per line
		List<TreeNode1> nodesAtLevel = levelOrderLineByLine(ll);
		ll.addAll(nodesAtLevel);
		return ll;

	}

	private void levelOrder(TreeNode1 lRoot, TreeNode1 rRoot) {
		if (lRoot != null && rRoot != null) {
			System.out.println(lRoot.data);
			System.out.println(rRoot.data);
		}

	}

	/**
	 * Standard Tree procedures root can be a global varaible
	 */
	/**
	 * Inserts a node into a tree with root Since there is no parent pointer needed
	 * except for root ?
	 * 
	 * @param root
	 * @param node
	 */
	/**
	 * Inserts a node into a tree with root Since there is no parent pointer needed
	 * except for root ?
	 * 
	 * @param root
	 * @param node
	 */
	public TreeNode1 insertInTree(TreeNode1 root, TreeNode1 node) {
		if (root == null) {
			root = node;
			return root;
		} else {
			TreeNode1 ret;
			if (root.data > node.data) {
				ret = insertInTree(root.left, node);
				if (root.left == null)
					root.left = ret;

			} else {
				ret = insertInTree(root.right, node);
				if (root.right == null) {
					root.right = ret;
				}
			}
			return root;
		}

	}

	@Deprecated
	public TreeNode1 insertTree(TreeNode1 root, TreeNode1 node) {
		if (root == null) {
			root = node;
			return root;
		} else {
			TreeNode1 ret;
			if (root.data > node.data) {
				ret = insertTree(root.left, node);
				// The parent of the node is not already set
				// so we should not set parents of anything again
				// instead of doig this check you could also
				// return root.left here then set the parent and left pointers
				// again irrespective which would override the already exisitng
				// pointers
				// to the same value. This works but null check is more elegant
				// way
				// same applies fpr the check on insertTree(root.right, node)
				// section also
				if (node.parent == null) {
					ret.parent = root;
					root.left = ret;
				}
			} else {
				ret = insertTree(root.right, node);
				if (node.parent == null) {
					ret.parent = root;
					root.right = ret;
				}
			}
			return ret;
		}

	}

	/**
	 * returns true if the tree is BST
	 * 
	 * @param root
	 * @return
	 */
	public boolean isBST(TreeNode1 root) {
		if (root == null)
			return true;
		boolean isLeftBST = isBST(root.left);
		boolean isrightBST = isBST(root.right);
		boolean isCurrentBST = true;
		if (root.left != null) {
			isCurrentBST = root.data >= root.left.data;
		}
		if (root.right != null) {
			isCurrentBST = isCurrentBST && root.data <= root.right.data;
		}
		return isLeftBST & isrightBST && isCurrentBST;

	}

	/**
	 * returns true if the tree is BST
	 * 
	 * @param root
	 * @return
	 */
	public boolean isBST2(TreeNode1 root) {
		if (root == null || (root.left == null && root.right == null))
			return true;
		boolean isLeftBST = isBST(root.left);
		boolean isrightBST = isBST(root.right);
		boolean isCurrentBST = true;
		if (root.left != null) {
			isCurrentBST = root.data >= root.left.data;
		}
		if (root.right != null) {
			isCurrentBST = isCurrentBST && root.data <= root.right.data;
		}
		return isLeftBST & isrightBST && isCurrentBST;

	}

}

class TreeNode1 {
	int data;
	TreeNode1 parent;
	TreeNode1 left;
	TreeNode1 right;

	public TreeNode1(int data) {
		super();
		this.data = data;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return data + " ";
	}

}