package java8features;

import trees.lecture8.PopulateTree;
import trees.lecture8.TreeNode;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

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
public class LeftViewOfTreeWithStream {

    public static void leftView(List<TreeNode> nodesAtcurrentLevel) {
        if (nodesAtcurrentLevel == null || nodesAtcurrentLevel.isEmpty()) return;
        System.out.print(nodesAtcurrentLevel.get(0).val+ "  ");
        List<TreeNode> allChildNodes = new ArrayList<>();

        Predicate<TreeNode> isLeft =  node -> node.left != null;
        Predicate<TreeNode> isRight =  node -> node.right != null;
        nodesAtcurrentLevel.stream().forEach( ech -> {
            if (isLeft.test(ech)) allChildNodes.add(ech.left);
            if (isRight.test(ech)) allChildNodes.add(ech.right);
        });
//        for (TreeNode each : nodesAtcurrentLevel) {
//            if (each.left != null) allChildNodes.add(each.left);
//            if (each.right != null) allChildNodes.add(each.right);
//        }
        leftView(allChildNodes);
    }

    public static void main(String args[]) {
        TreeNode root = PopulateTree.populateTreeForLeftViews();
        List<TreeNode> tnList = new ArrayList<TreeNode>();
        tnList.add(root);
        leftView(tnList);
    }

}
