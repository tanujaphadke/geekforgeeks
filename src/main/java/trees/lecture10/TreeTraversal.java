package trees.lecture10;

import trees.lecture8.PopulateTree;
import trees.lecture8.TreeNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class TreeTraversal {

    /**
     * Left root right
     *
     * @param node
     * @return
     */
    public static java.util.List<Integer> preOrderWithReturn(TreeNode node) {
        List p = new ArrayList<>();
        if (node == null) {
            return new ArrayList<>();
        }
        List<Integer> preorder = new ArrayList<Integer>();
        preorder.add(node.val);
        List<Integer> l = preOrderWithReturn(node.left);
        preorder.addAll(l);
        List<Integer> r = preOrderWithReturn(node.right);
        preorder.addAll(r);
        return preorder;
    }

    /**
     * Left root right
     *
     * @param node
     * @return
     */
    public static java.util.List<Integer> inOrderWithReturn(TreeNode node) {
        if (node == null) {
            return new ArrayList<>();
        }
        List<Integer> inorder = new ArrayList<Integer>();
        List<Integer> l = inOrderWithReturn(node.left);
        l.add(node.val);
        List<Integer> r = inOrderWithReturn(node.right);
        l.addAll(r);
        return l;
    }

    public static java.util.List<Integer> postorderWithReturn(TreeNode node) {
        if (node == null) {
            return new ArrayList<>();
        }
        List<Integer> postorder = new ArrayList<Integer>();
        List<Integer> l = postorderWithReturn(node.left);
        List<Integer> r = postorderWithReturn(node.right);
        l.addAll(r);
        l.add(node.val);
        return l;
    }

    public java.util.List<Integer> postorderIterative(TreeNode root) {
        List<Integer> postorder = new ArrayList<Integer>();
        TreeNode elem = root;
        Stack<TreeNode> stack = new java.util.Stack<TreeNode>();
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

    public static void print(List<Integer> ans) {
        for (Object each : ans) {
            System.out.print(each);

        }
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
        java.util.List<Integer> ans = new TreeTraversal().postorderIterative(root);
        System.out.println("From Iterative ");
        print(ans);

        root = PopulateTree.populateTreeForTraversal();
        ans = new TreeTraversal().postorderWithReturn(root);
        System.out.println("From Recursive ");
        print(ans);


        System.out.println("Level order traversal  of the tree is: ");

        new TreeTraversal().printLevelOrder(root);
        System.out.println("\nLevel order traversal  of the tree Line by line is: \n");

        new TreeTraversal().printLevelOrderLineByLine(root);

        System.out.println("\nReverse Level order traversal  of the tree: \n");
        new TreeTraversal().printReverseLevelOrder(root);


        System.out.println("\nLevel order ZIG ZAG traversal  of the tree is: \n");

        new TreeTraversal().printZigZagOrder(root);

        printPathHeler();

    }


    // This is for practice.This may not work
    // https://www.geeksforgeeks.org/given-a-binary-tree-print-all-root-to-leaf-paths/
    public List<List<TreeNode>> printAllRootToleafPathFromAnyRoot(TreeNode node) {
        if (node == null) {
            List<List<TreeNode>> allpaths = new ArrayList<List<TreeNode>>();
            return allpaths;
        }
        // We are not back tracking we addd nodes as desired.
        List<List<TreeNode>> lpaths = printAllRootToleafPathFromAnyRoot(node.left);
        List<List<TreeNode>> rpaths = printAllRootToleafPathFromAnyRoot(node.right);
        List<TreeNode> lpath = new ArrayList<>();
        lpath.add(0, node);
        List<TreeNode> rpath = new ArrayList<>();
        rpath.add(0, node);
        List<List<TreeNode>> allpath = new ArrayList<>();
        allpath.add(lpath);
        allpath.add(rpath);
        return allpath;

    }

    /*
     * 10 8 2 3 5 2
     */
    public static void printPathHeler() {
        TreeNode root = new TreeNode(10);
        root.left = new TreeNode(8);
        root.right = new TreeNode(2);
        root.left.left = new TreeNode(3);
        root.left.right = new TreeNode(5);
        root.right.left = new TreeNode(2);
        //printAllRootToLeafPaths(root, new ArrayList());
    }

    // https://www.geeksforgeeks.org/given-a-binary-tree-print-all-root-to-leaf-paths/
    //TAN
//    public static void printAllRootToLeafPaths(TreeNode node, List<TreeNode> inpath) {
//        if (node == null)
//            return;
//        inpath.add(node);
//        printAllRootToLeafPaths(node.left, inpath);
//        printAllRootToLeafPaths(node.right, inpath);
//        if (node.left == null && node.right == null) {
//            // print inpath
//            inpath.stream().forEach(n -> System.out.print(n.data));
//            System.out.println();
//            return;
//        }
//
//    }

    public void printZigZagOrder2(TreeNode root) {
        List<TreeNode> ll = new ArrayList<TreeNode>();
        ll.add(root);
        zigZagOrderHelper(ll, 1);
    }

    public void zigZagOrderHelper(List<TreeNode> list, int level) {
        if (list == null || list.isEmpty()) {
            return;
        }
        List<TreeNode> ll = new ArrayList<>();
        for (TreeNode each : list) {
            TreeNode left = each.left;
            TreeNode right = each.right;
            ll.add(left);
            ll.add(right);
            zigZagOrderHelper(ll, level + 1);
            print(ll, level);
        }
    }

    public void print(List<TreeNode> list, int level) {
        if (level % 2 == 0) {
            for (int i = 0; i < list.size(); i++) {
                System.out.print(list.get(i));
            }
        } else {
            for (int i = list.size(); i >= 0; i--) {
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
    public void printZigZagOrder(TreeNode root) {
        List<TreeNode> ll = new ArrayList<TreeNode>();
        ll.add(root);
        List<TreeNode> llnodes = zigZagOrder(ll, 1);
        ll.addAll(llnodes);
        for (TreeNode each : ll) {

            System.out.print(each.val + " ");
        }

    }

    private List<TreeNode> zigZagOrder(List<TreeNode> nodeList, int level) {
        if (nodeList == null || nodeList.isEmpty())
            return new ArrayList<TreeNode>();
        List<TreeNode> ll = new ArrayList<TreeNode>();
        if (level % 2 == 0) {
            // always pop from the back of a list
            for (int i = nodeList.size() - 1; i > -1; i--) {
                TreeNode temp = nodeList.get(i);
                if (temp.left != null)
                    ll.add(temp.left);
                if (temp.right != null)
                    ll.add(temp.right);
            }
        } else {
            for (int i = nodeList.size() - 1; i > -1; i--) {
                TreeNode temp = nodeList.get(i);
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
    public void printReverseLevelOrder(TreeNode root) {
        List<TreeNode> ll = new ArrayList<TreeNode>();
        ll.add(root);
        List<TreeNode> llnodes = levelOrderReverse(ll);
        ll.addAll(0, llnodes);
        for (TreeNode each : ll) {

            System.out.print(each.val + " ");
        }

    }

    private List<TreeNode> levelOrderReverse(List<TreeNode> nodeList) {
        if (nodeList == null || nodeList.isEmpty())
            return new ArrayList<TreeNode>();
        List<TreeNode> ll = new ArrayList<TreeNode>();
        for (TreeNode node : nodeList) {
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
    public void printLevelOrder(TreeNode root) {
        List<TreeNode> ll = new ArrayList<TreeNode>();
        ll.add(root);
        List<TreeNode> llnodes = levelOrder(ll);
        ll.addAll(llnodes);
        for (TreeNode each : ll) {

            System.out.print(each.val + " ");
        }

    }

    @Deprecated
    //This does not distinguish when each level has ended.
    private List<TreeNode> levelOrder(List<TreeNode> nodeList) {
        if (nodeList == null || nodeList.isEmpty())
            return new ArrayList<TreeNode>();
        List<TreeNode> ll = new ArrayList<TreeNode>();
        for (TreeNode node : nodeList) {
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
    public void printLevelOrderLineByLine(TreeNode root) {
        List<TreeNode> ll = new ArrayList<TreeNode>();
        ll.add(root);
        ll.add(null);
        List<TreeNode> llnodes = levelOrderLineByLine(ll);
        ll.addAll(llnodes);
        for (TreeNode each : ll) {
            if (each == null)
                System.out.println();
            else
                System.out.print(each.val + " ");
        }

    }

    public List<TreeNode> levelOrderLineByLine(List<TreeNode> nodeList) {
        if (nodeList == null || nodeList.isEmpty() || nodeList.get(0) == null)
            return new ArrayList<TreeNode>();
        List<TreeNode> ll = new ArrayList<TreeNode>();
        for (TreeNode node : nodeList) {
            if (node != null && node.left != null)
                ll.add(node.left);
            if (node != null && node.right != null)
                ll.add(node.right);
        }
        ll.add(null);// Add a dummy null for printing per line
        List<TreeNode> nodesAtLevel = levelOrderLineByLine(ll);
        ll.addAll(nodesAtLevel);
        return ll;

    }


}

