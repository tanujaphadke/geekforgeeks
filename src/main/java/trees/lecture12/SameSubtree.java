package trees.lecture12;

import trees.lecture8.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 Given two non-empty binary trees s and t, check whether tree t has
 exactly the same structure and node
 values with a subtree of s. A subtree of s is a tree
 consists of a node in s and all of this node's descendants.
 The tree s could also be considered as a subtree of itself.


 */
public class SameSubtree {

    //Return true if t is a subtree of s
    public boolean isSubTree(TreeNode t, TreeNode s){

        List<TreeNode> foundRootNodes = sameNode(t, s );
        if (foundRootNodes == null || foundRootNodes.isEmpty()) return false;
        boolean anySubtreeFound = false;
        for (TreeNode each : foundRootNodes){
            anySubtreeFound = anySubtreeFound || areTreesSame(t, each); //Since t root note of Tree t is found in all the foundRootNodes.
        }
        return anySubtreeFound;
    }

    public boolean areTreesSame(TreeNode t, TreeNode ss){
        if (t == null && ss == null ) return true;
        if (t == null || ss == null ) return false;
        boolean isSame = false;
        if (t.val == ss.val) {
            isSame = true;
            boolean isLeftSame = areTreesSame(t.left,ss.left);
            boolean isRighttSame = areTreesSame(t.right,ss.right);
            return isSame && isLeftSame && isRighttSame ;
        }else return false;
    }

    //List<TreeNode> Returns a list of all the nodes in the rootOfTreeToSearchIn which is s tree
     List<TreeNode> sameNode(TreeNode someNode, TreeNode rootOfTreeToSearchIn){
        List<TreeNode> foundNodes  = new ArrayList<>();
        if ( someNode == null || rootOfTreeToSearchIn == null ) return new ArrayList<>() ;
        if (someNode.val == rootOfTreeToSearchIn.val) {
            foundNodes.add(rootOfTreeToSearchIn);
        }
         foundNodes.addAll(sameNode(someNode, rootOfTreeToSearchIn.left));
         foundNodes.addAll(sameNode(someNode,rootOfTreeToSearchIn.right));
        return foundNodes;
    }

    public static void main (String args [] ){
        TreeNode rootS = new TreeNode(3);
        TreeNode left = new TreeNode(4);
        TreeNode right = new TreeNode(5);
        TreeNode lleft = new TreeNode(1);
        TreeNode rleft = new TreeNode(2);
        rootS.left = left; rootS.right = right;
        left.left = lleft; left.right = rleft;


        TreeNode rootT = new TreeNode(4);
        TreeNode lTleft = new TreeNode(1);
        TreeNode rTleft = new TreeNode(2);
        rootT.left = lTleft; rootT.right = rTleft;
        SameSubtree ss = new SameSubtree();
        System.out.println(ss.isSubTree(rootT,rootS));
    }






    /**
     * Definition for a binary tree node.
     * public class TreeNode {
     *     int val;
     *     TreeNode left;
     *     TreeNode right;
     *     TreeNode(int x) { val = x; }
     * }
     */
//Leetcode solution .
    public class Solution {
        public boolean isSubtree(TreeNode s, TreeNode t) {
            return traverse(s,t);
        }
        public boolean equals(TreeNode x,TreeNode y)
        {
            if(x==null && y==null)
                return true;
            if(x==null || y==null)
                return false;
            return x.val==y.val && equals(x.left,y.left) && equals(x.right,y.right);
        }
        public boolean traverse(TreeNode s,TreeNode t)
        {
            return  s!=null && ( equals(s,t) || traverse(s.left,t) || traverse(s.right,t));
        }
    }

}
