package z1lecture8;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class BinaryTreeIsComplete {

    Map<Node4, List<Node4>> parentChildMap = new HashMap();

    public static boolean isComplete(Map<Node4, Node4[]> parentChildMap , boolean isLeftOnlyGood ) {

        //it is good iff it is the rightmost at any level
        boolean cousionFound =false;
        Map<Node4, Node4[]> pcMap = new LinkedHashMap<>();

        for (Map.Entry<Node4, Node4[]> each : parentChildMap.entrySet()){
            Node4 parent = each.getKey();
            Node4[] leftAndRightChildren = each.getValue();
            if ( !isLeftOnlyGood && (leftAndRightChildren[0] != null || leftAndRightChildren[1] != null) ){
                return false;
            }
            if (leftAndRightChildren[0] == null && leftAndRightChildren[1] != null ) return false;
            if (leftAndRightChildren[0] != null && leftAndRightChildren[1] == null ){
                isLeftOnlyGood = false;
            }


        }

        //if ( Node4.left != null && Node4.right == null) return false; //?
return false;

    }

    public static boolean isComplete(Node4 root) {
        if ( root == null ) return true;
        Node4[] lrChild = new Node4[2];
        if ( root.left == null &&  root.right != null) return false;
        if ( root.left != null ) lrChild[0] = root.left;
        if ( root.right != null ) lrChild[1]= root.right;
        Map<Node4, Node4[]> parentChildMap = new LinkedHashMap<>();
        parentChildMap.put(root,lrChild );
        return isComplete(parentChildMap, true);
    }


        public static void main(String args[]) {
        /* Construct below tree
		          1
		       /     \
		      2       3
		     / \     / \
		    4   5   6   7
		*/

        Node4 root = new Node4(1);
        root.left = new Node4(2);
        root.right = new Node4(3);
        root.left.left = new Node4(4);
        root.left.right = new Node4(5);
        root.right.left = new Node4(6);
        root.right.right = new Node4(7);

    }
}
