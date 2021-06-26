package trees.lecture10;

import trees.lecture8.PopulateTree;
import trees.lecture8.TreeNode;

import java.util.ArrayList;
import java.util.List;
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
//BIO Business information office
public class RighttViewOfTree {

    public static void rightView(List<TreeNode> nodesAtcurrentLevel) {
        if (nodesAtcurrentLevel == null || nodesAtcurrentLevel.isEmpty()) return;
        System.out.print(nodesAtcurrentLevel.get(nodesAtcurrentLevel.size() -1).val+ "  ");
        List<TreeNode> allChildNodes = new ArrayList<>();
        for (TreeNode each : nodesAtcurrentLevel) {
            if (each.left != null) allChildNodes.add(each.left);
            if (each.right != null) allChildNodes.add(each.right);
        }
        rightView(allChildNodes);
    }

    public static void main(String args[]) {
        TreeNode root = PopulateTree.populateTreeForRightViews();
        List<TreeNode> tnList = new ArrayList<TreeNode>();
        tnList.add(root);
        rightView(tnList);
    }

}
