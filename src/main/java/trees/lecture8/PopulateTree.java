package trees.lecture8;

public class PopulateTree {

    static class TreeNode1 {
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

    public static TreeNode populateTreeForTraversal() {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root. left.left = new TreeNode(4);
        root. left.right = new TreeNode(5);
        return root;

    }

    public static TreeNode get3NodeTree() {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        return root;
    }

    public static TreeNode populateTree() {
        TreeNode root;
        root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);

        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        // root.right.left = new TreeNode(6);
        // root.right.right = new TreeNode(7);
        //
        // root.left.left.left = new TreeNode(8);
        // root.left.left.right = new TreeNode(9);
        root.left.right.left = new TreeNode(10);
        // root.left.right.right = new TreeNode(11);
        //
        // root.right.left.left = new TreeNode(12);
        // root.right.left.right = new TreeNode(13);
        // root.right.right.left = new TreeNode(14);
        // root.right.right.right = new TreeNode(15);
        root.left.right.left.left = new TreeNode(40);
        return root;
    }

    public static TreeNode populateWithParent() {
        TreeNode root;
        root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);

        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        // root.right.left = new TreeNode(6);
        // root.right.right = new TreeNode(7);
        //
        // root.left.left.left = new TreeNode(8);
        // root.left.left.right = new TreeNode(9);
        root.left.right.left = new TreeNode(10);
        // root.left.right.right = new TreeNode(11);
        //
        // root.right.left.left = new TreeNode(12);
        // root.right.left.right = new TreeNode(13);
        // root.right.right.left = new TreeNode(14);
        // root.right.right.right = new TreeNode(15);
        root.left.right.left.left = new TreeNode(40);
        return root;
    }

    public static TreeNode populateTreeForIsHeightBalanced() {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        root.right.right = new TreeNode(6);
        root.left.left.left = new TreeNode(7);
        root.left.left.left.left = new TreeNode(17);

        return root;
    }

 /* Let us create following BST
            50
          /   \
        30      70
        / \   / \
       20 40 60 80 */

    public static TreeNode populateBST() {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(30);
        root.right = new TreeNode(70);
        root.left.left = new TreeNode(20);
        root.left.right = new TreeNode(40);
        root.right.left = new TreeNode(60);
        root.right.right = new TreeNode(80);
        return root;
    }
/*
             1
          /   \
        10      20
       / \       \
     30   40      50
         /         \
       60          70
                    \
                    80

      Should Return 1  10  30  60  80

 */

    public static TreeNode populateTreeForLeftViews() {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(10);
        root.right = new TreeNode(20);
        root.left.left = new TreeNode(30);
        root.left.right = new TreeNode(40);
        root.right.right = new TreeNode(50);

        root.left.right.right = new TreeNode(60);
        root.right.right.right = new TreeNode(70);
        root.right.right.right.right = new TreeNode(80);
        return root;
    }

/*
             1
          /   \
        10      20
       / \       \
     30   40      50
    /     /  \
   80   60   70
    \
    90

      Should Return 1  20  50  70  90

 */

    public static TreeNode populateTreeForRightViews() {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(10);
        root.right = new TreeNode(20);
        root.left.left = new TreeNode(30);
        root.left.right = new TreeNode(40);
        root.right.right = new TreeNode(50);

        root.left.left.left = new TreeNode(80);
        root.left.right.left = new TreeNode(60);
        root.left.right.right = new TreeNode(70);
        root.left.left.left.right = new TreeNode(90);
        return root;
    }


    /*
             1
          /   \
        10      20
       / \       \
     30   40      50
         /         \
       60          70
                    \
                    80

      Should Return 1  10  30  60  80

 */

    public static TreeNode populateTreeForBottomViews() {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(10);
        root.right = new TreeNode(20);
        root.left.left = new TreeNode(30);
        root.left.right = new TreeNode(40);
        root.right.right = new TreeNode(50);

        root.left.right.right = new TreeNode(60);
        root.right.right.right = new TreeNode(70);
        root.right.right.right.right = new TreeNode(80);
        return root;
    }


    /*
             1
          /   \
        10      20
       / \       \
     30   40      50
         /         \
       60          70
                    \
                    80

      Should Return 30 10 1 20 50 70 80

 */

    public static TreeNode populateTreeForTopViews() {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(10);
        root.right = new TreeNode(20);
        root.left.left = new TreeNode(30);
        root.left.right = new TreeNode(40);
        root.right.right = new TreeNode(50);

        root.left.right.right = new TreeNode(60);
        root.right.right.right = new TreeNode(70);
        root.right.right.right.right = new TreeNode(80);
        return root;
    }
/*
Input: root of below tree
              5
             / \
            1   5
           / \   \
          5   5   5
Output: 4

 */

    public static TreeNode populateTreeForUnivalTree() {
        TreeNode root = new TreeNode(5);
        root.left = new TreeNode(1);
        root.right = new TreeNode(5);
        root.left.left = new TreeNode(5);
        root.left.right = new TreeNode(5);
        root.right.right = new TreeNode(5);

        return root;
    }

}
