package trees.lecture10;

import trees.lecture8.PopulateTree;
import trees.lecture8.TreeNode;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeMap;

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

      Should Return 30 10 1 20 50 70

 */
public class TopViewOfTree {

    public static void topView(Map<TreeNode, Integer> nodesAtcurrentLevelDistanceMap,
                               Map<Integer, TreeNode> distanceTreeNodeMap) {
        if (nodesAtcurrentLevelDistanceMap == null ||
                nodesAtcurrentLevelDistanceMap.isEmpty()) return;

        Map<TreeNode, Integer> childNodesDistanceMap = new LinkedHashMap<>();
        for (Map.Entry<TreeNode, Integer> each : nodesAtcurrentLevelDistanceMap.entrySet()) {
            TreeNode leftChild = each.getKey().left;
            if (leftChild != null) {
                Integer distanceOfLeftChildfromRoot = each.getValue() + (-1);
                distanceTreeNodeMap.putIfAbsent(distanceOfLeftChildfromRoot, leftChild);
                childNodesDistanceMap.put(leftChild,distanceOfLeftChildfromRoot);
            }
            TreeNode rightChild = each.getKey().right;
            if (rightChild != null) {
                Integer distanceOfRighttChildfromRoot = each.getValue() + (1);
                distanceTreeNodeMap.putIfAbsent(distanceOfRighttChildfromRoot, rightChild);
                childNodesDistanceMap.put(rightChild,distanceOfRighttChildfromRoot);

            }
        }
        topView(childNodesDistanceMap,distanceTreeNodeMap);
    }

    public static void main(String args[]) {
        TreeNode root = PopulateTree.populateTreeForTopViews();

        Map<Integer, TreeNode> distanceTreeMap = new TreeMap<>();
        distanceTreeMap.put(0, root);//Root is at distance 0

        Map<TreeNode, Integer> nodesAtcurrentLevelDistanceMap = new LinkedHashMap<>();
        nodesAtcurrentLevelDistanceMap.put(root, 0);
        topView(nodesAtcurrentLevelDistanceMap, distanceTreeMap);

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

      Should Return 30 60 40  20 50 70 80

 */
        for (Map.Entry<Integer, TreeNode> eachEntry : distanceTreeMap.entrySet()){
            System.out.print(eachEntry.getValue().val + "   " );
        }
    }

}
