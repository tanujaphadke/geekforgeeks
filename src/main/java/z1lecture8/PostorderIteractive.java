package z1lecture8;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

// class TreeNode {
//	int val;
//	TreeNode left;
//	TreeNode right;
//
//	int color; //0 = black
//	TreeNode parent; //We do not need this
//	int d;
//	@Override
//	public String toString() {
//		return "{\"val\":" + val + ", \"left\":" + left + ", \"right\":" + right
//				+ "}";
//	}
//
//	TreeNode(int x) {
//		val = x;
//		color = 1;
//	}
//}

public class PostorderIteractive {
	public List<Integer> postorderTraversal(TreeNode root) {
		List<Integer> postorder = new ArrayList<Integer>();
		TreeNode elem = root;
		Stack<TreeNode> stack = new Stack<TreeNode>();
		while (true) {
			if (elem != null) {
				stack.push(elem);
				elem = elem.left;
			} else {// elem == null
				TreeNode current = stack.peek();
				if (current.right != null) {
					elem = current.right;
				} else { // current.right == null
					TreeNode popped = stack.pop(); // pop the current element
					postorder.add(popped.val);
					// Since we have arrived here from the leftmost we are doen
					// with the left so
					// we should see if there is any right or not
					elem = stack.peek().right;
					while (elem == popped) {
						popped = stack.pop();
						postorder.add(popped.val);
						if (!stack.empty())
							elem = stack.peek().right;

					}

				}
			}
			if (stack.empty()) {
				break;
			}
		}
		return postorder;
	}

	public static void main(String args[]) {
		TreeNode root = new TreeNode(1);
		TreeNode rt = new TreeNode(2);
		root.right = rt;
		root.left = null;
		TreeNode lt = new TreeNode(3);
		lt.right = null;
		lt.left = null;
		rt.left = lt;

		List<Integer> ans = new PostorderIteractive().postorderTraversal(root);
		for (int each : ans) {
			System.out.print(each);
		}
	}

}