package z1lecture8;


import java.util.LinkedList;
import java.util.List;

//http://www.geeksforgeeks.org/maximum-element-two-nodes-bst/
//Input : arr[] = { 18, 36, 9, 6, 12, 10, 1, 8 },
//a = 1,
//b = 10.
//Output : 12

public class Maximumelementbst2nodes {
    private static TreeNode root;

    public static void main(String args[]) {
        int arr[] = {18, 36, 9, 6, 12, 10, 1, 8};
        TreeNode nodes[] = {new TreeNode(36), new TreeNode(9),
                new TreeNode(6), new TreeNode(12), new TreeNode(10),
                new TreeNode(1), new TreeNode(8)};
        root = new TreeNode(18);
        for (TreeNode node : nodes) {
            insert(root, node);
        }
        System.out.println("IS BAST " + isBST(root));
        List<TreeNode> p2nodes = findPathFromRoot(root, new TreeNode(1));
        print(p2nodes);
        p2nodes = findPathFromRoot(root, new TreeNode(10));
        print(p2nodes);
        p2nodes = findPathBetween(root, new TreeNode(1), new TreeNode(10));
        print(p2nodes);

        //New nodes for inorder treewalk
        root = new TreeNode(10);
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
        System.out.println();
        DBLNode nd = inorderLeft(root);
        System.out.println(nd);

    }

    //http://www.geeksforgeeks.org/write-an-efficient-c-function-to-convert-a-tree-into-its-mirror-tree/
    //Mirror a Tree
    public static TreeNode mirrorTree(TreeNode node) {
        if (node == null) {
            return null;
        }
        TreeNode left = mirrorTree(node.left);
        TreeNode right = mirrorTree(node.right);
        node.left = right;
        node.right = left;
        return node;


    }

    // Findout if a given tree is a BST{}
    public static boolean isBST(TreeNode root) {
        if (root == null || (root.left == null && root.right == null))
            return true;
        if ((root.left == null || root.left.val <= root.val) && (root.right == null || root.val <= root.right.val)) {
            return isBST(root.left) && isBST(root.right);
        } else {
            return false;
        }
    }

    //Get only the left subtree of the ROOT
    public static void getList(TreeNode treeRoot) {
        DBLNode rightMostNodeOfLeftList = inorderLeft(treeRoot);
        DBLNode leftMostNodeOfRightList = inorderRight(treeRoot);

    }


    public static DBLNode inorderLeft(TreeNode treeRoot) {
        if (treeRoot == null) return null;
        DBLNode leftNode = inorderLeft(treeRoot.left);
        DBLNode rootNode = new DBLNode(treeRoot.val); //copy the current node value into linked list
        DBLNode rightNode = null;

        if (!treeRoot.equals(root))
            rightNode = inorderLeft(treeRoot.right);

        rootNode.prev = leftNode;
        rootNode.next = rightNode;

        if (leftNode != null) leftNode.next = rootNode;
        if (rightNode != null) rightNode.prev = rootNode;

        if (rightNode != null)
            return rightNode;
        else return rootNode;

    }

    public static DBLNode inorderRight(TreeNode treeRoot) {
        if (treeRoot == null) return null;

        DBLNode leftNode = null;
        if (!treeRoot.equals(root))
            leftNode = inorderRight(treeRoot.left);

        DBLNode rootNode = new DBLNode(treeRoot.val); //copy the current node value into linked list

        DBLNode rightNode = inorderRight(treeRoot.right);

        rootNode.prev = leftNode;
        rootNode.next = rightNode;

        if (leftNode != null) leftNode.next = rootNode;
        if (rightNode != null) rightNode.prev = rootNode;

        if (leftNode != null)
            return leftNode;
        else return leftNode;

    }


    public static void inorder(TreeNode root) {
        TreeNode node = root;
        if (node == null) return;
        inorder(root.left);
        System.out.print(root.val + " :  ");
        inorder(root.right);

    }

    public static void print(List<TreeNode> p2nodes) {
        System.out.println();
        for (TreeNode node : p2nodes) {
            System.out.print(node.val + " : ");
        }
    }

    private static TreeNode low = new TreeNode(1);
    private static TreeNode high = new TreeNode(10);

    public static List<TreeNode> findPathBetween2(TreeNode root, TreeNode valueToFind) {
        List<TreeNode> path = new LinkedList<TreeNode>();
        TreeNode curent = root;
        if (valueToFind == null || curent == null) return path;
        if (valueToFind != null && (valueToFind.val == curent.val)) {
            path.add(valueToFind);
            return path;
        }
        if (isValueLeftOfCurrent(valueToFind, curent)) {
            List<TreeNode> p = findPathBetween2(curent.left, valueToFind);
            if (p.isEmpty()) return path;
            addToPath(path, curent, p);
            findHigh(path, curent);
            return path;
        } else if (isValueRightOfCurrent(valueToFind, curent)) {
            List<TreeNode> p = findPathBetween2(curent.right, valueToFind);
            if (p.isEmpty()) return path;
            addToPath(path, curent, p);
            findHigh(path, curent);
            return path;
        }
        return path;
    }


    private static void findHigh(List<TreeNode> path, TreeNode curent) {
        if (high.val == curent.val) {
            path.add(high);
        } else if (isValueLeftOfCurrent(high, curent)) {
            List<TreeNode> ph = findPathBetween2(curent.left, high);
            if (ph.isEmpty()) return;
            addToPathRev(path, high, ph);
        } else if (isValueRightOfCurrent(high, curent)) {
            // high val is on the right of the BST
            List<TreeNode> ph = findPathBetween2(curent.right, high);
            if (ph.isEmpty()) return;
            addToPathRev(path, high, ph);
        }
    }


    // Find path from root to a node.
    // findPathBetween will be called as findPathBetween(root, low,high)
    public static List<TreeNode> findPathBetween(TreeNode root, TreeNode firstValue, TreeNode secondValue) {
        List<TreeNode> path = new LinkedList<TreeNode>();
        TreeNode curent = root;
        if (firstValue == null || curent == null) return path;
        if (firstValue != null && (firstValue.val == curent.val)) {
            path.add(firstValue);
            return path;
        }
        if (isValueLeftOfCurrent(firstValue, curent)) {
            List<TreeNode> p = findPathBetween(curent.left, firstValue, secondValue);
            if (!p.isEmpty()) {
                addToPath(path, curent, p);
                if (secondValue != null && (secondValue.val == curent.val)) {
                    path.add(secondValue);
                } else if (secondValue != null && isValueLeftOfCurrent(secondValue, curent)) {
                    List<TreeNode> ph = findPathBetween(curent.left, secondValue, null);
                    if (!ph.isEmpty()) {
                        addToPath(path, secondValue, ph);
                    }
                } else if (secondValue != null && isValueRightOfCurrent(secondValue, curent)) {
                    // high val is on the right of the BST
                    List<TreeNode> ph = findPathBetween(curent.right, secondValue, null);
                    if (!ph.isEmpty()) {
                        addToPath(path, secondValue, ph);
                    }
                }
            }
            return path;
        } else if (isValueRightOfCurrent(firstValue, curent)) {
            List<TreeNode> p = findPathBetween(curent.right, firstValue, null);
            if (!p.isEmpty()) {
                addToPath(path, curent, p);
                if (secondValue != null && (secondValue.val == curent.val)) {
                    path.add(secondValue);
                } else if (secondValue != null && isValueLeftOfCurrent(secondValue, curent)) {
                    List<TreeNode> ph = findPathBetween(curent.left, secondValue, null);
                    if (!ph.isEmpty()) {
                        addToPath(path, secondValue, ph);
                    }
                } else if (secondValue != null && isValueRightOfCurrent(secondValue, curent)) {
                    // high val is on the right of the BST
                    List<TreeNode> ph = findPathBetween(curent.right, secondValue, null);
                    if (!ph.isEmpty()) {
                        addToPath(path, secondValue, ph);
                    }
                }
            }
            return path;
        }
        return path;
    }

    private static boolean isValueRightOfCurrent(TreeNode secondValue,
                                                 TreeNode curent) {
        return secondValue.val > curent.val && curent.right != null;
    }

    private static boolean isValueLeftOfCurrent(TreeNode firstValue,
                                                TreeNode curent) {
        return firstValue.val < curent.val && curent.left != null;
    }


    private static List<TreeNode> getEmptyArrayIfError(TreeNode firstValue, List<TreeNode> path, TreeNode parent) {
        List<TreeNode> ep = new LinkedList<TreeNode>();
        if (firstValue == null || parent == null)
            return ep; // Empty Path. Throw Exception Error case.
        return path;
    }

    private static void addToPath(List<TreeNode> path, TreeNode parent, List<TreeNode> p) {
        path.addAll(p);
        path.add(parent);
    }

    private static void addToPathRev(List<TreeNode> path, TreeNode parent,
                                     List<TreeNode> p) {
        path.add(parent);
        path.addAll(p);
    }

    // Find path from root to a node.
    public static List<TreeNode> findPathFromRoot(TreeNode root, TreeNode n) {
        List<TreeNode> path = new LinkedList<TreeNode>();
        TreeNode node = root;

        if (n == null || root == null)
            return null; // Throw Exception Error case.
        if (n.val == node.val) { // This will happen only once. assuming
            // sidtinct nodes in BST
            // path = path + fnode();
            path.add(n);
            return path;
        }
        if (isValueLeftOfCurrent(n, node)) {
            List<TreeNode> p = findPathFromRoot(node.left, n);
            if (!p.isEmpty()) {
                path.add(node);
                path.addAll(p);
            }
            return path;

        } else if (isValueRightOfCurrent(n, node)) {
            List<TreeNode> p = findPathFromRoot(node.right, n);
            if (!p.isEmpty()) {
                path.add(node);
                path.addAll(p);
            }
            return path;
        } else {
            return path; // not found path will be empty if there is no path
            // from root to that node
        }
    }

    public static TreeNode findNode(TreeNode root, TreeNode n) {
        if (n == null || root == null)
            return null;
        if (n.val == root.val) {
            return n;
        }
        TreeNode node = root;
        if (n.val < node.val && node.left == null) {
            return findNode(node.left, n);

        } else if (n.val > node.val && node.right == null) {
            return findNode(node.right, n);
        } else {
            return null; // not found
        }
    }

    public TreeNode findMax(TreeNode low, TreeNode high) {
        // if (low.val < root.val ){
        // root.left;
        // }else{
        //
        // }
        //
        return null;
    }

    public static void insert(TreeNode root, TreeNode n) {
        if (root == null) {
            root = n;
            return;
        }
        TreeNode node = root;
        if (n.val < node.val && node.left == null) {
            node.left = n;
            return;
        } else if (n.val > node.val && node.right == null) {
            node.right = n;
            return;
        } else if (isValueLeftOfCurrent(n, node)) {
            insert(node.left, n);
        } else if (isValueRightOfCurrent(n, node)) {
            insert(node.right, n);

        }

    }
}
