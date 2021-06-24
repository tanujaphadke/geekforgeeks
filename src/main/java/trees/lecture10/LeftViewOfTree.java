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
         /         \
       60          70
                    \
                    80

      Should Return 1  10  30  60  80

 */
//BIO Business information office
public class LeftViewOfTree {

    public static void leftView(List<TreeNode> nodesAtcurrentLevel) {
        if (nodesAtcurrentLevel == null || nodesAtcurrentLevel.isEmpty()) return;
        System.out.print(nodesAtcurrentLevel.get(0).val+ "  ");
        List<TreeNode> allChildNodes = new ArrayList<>();
        for (TreeNode each : nodesAtcurrentLevel) {
            if (each.left != null) allChildNodes.add(each.left);
            if (each.right != null) allChildNodes.add(each.right);
        }
        leftView(allChildNodes);
    }

    public static void main(String args[]) {
        TreeNode root = PopulateTree.populateTreeForLeftViews();
        List<TreeNode> tnList = new ArrayList<TreeNode>();
        tnList.add(root);
        leftView(tnList);
    }

}
